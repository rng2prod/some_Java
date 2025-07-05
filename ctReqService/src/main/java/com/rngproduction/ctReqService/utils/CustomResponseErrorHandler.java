package com.rngproduction.ctReqService.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CustomResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus status = clientHttpResponse.getStatusCode();
        return status.is4xxClientError() || status.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        String responseAsString = toString(clientHttpResponse.getBody());
        LOGGER.warn("ResponseBody: {}", responseAsString);
        throw new IOException(responseAsString);
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        String responseAsString = toString(response.getBody());
        LOGGER.warn("URL: {}, HttpMethod: {}, ResponseBody: {}", url, method, responseAsString);
        throw new IOException(responseAsString);
    }

    String toString(InputStream inputStream) {
        Scanner s = new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
