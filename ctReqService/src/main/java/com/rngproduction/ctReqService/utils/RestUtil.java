package com.rngproduction.ctReqService.utils;

import com.rngproduction.ctReqService.config.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Component
public class RestUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestUtil.class);

    @Autowired
    Property property;

    public <E> E sendRequest(String url, String request, Class<E> clazz) {

        Long TIMEOUTCONN = property.getTimeoutConn();
        Long TIMEOUTREQUEST = property.getTimeoutRequest();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(TIMEOUTCONN));
        factory.setConnectionRequestTimeout((int) TimeUnit.SECONDS.toMillis(TIMEOUTREQUEST));

        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> entity = new HttpEntity<>(request, headers);

        E result = null;
        try {
            ResponseEntity<E> response;
            response = restTemplate.exchange(url, HttpMethod.POST, entity, clazz);

            if (response.getStatusCode() == HttpStatus.OK) {
                result = response.getBody();
            } else {
                LOGGER.warn("Error get response from URL: " + url + "\n" + "error: "
                        + response.getStatusCode() + " " + response.getStatusCodeValue());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }
}
