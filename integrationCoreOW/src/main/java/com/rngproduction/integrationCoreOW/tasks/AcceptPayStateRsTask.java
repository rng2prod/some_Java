package com.rngproduction.integrationCoreOW.tasks;

import com.rngproduction.integrationCoreOW.configuration.RsTag;
import com.rngproduction.integrationCoreOW.ctx.ApiContext;
import com.rngproduction.integrationCoreOW.models.apiModels.responses.AcceptPayRsApi.AcceptPayRsApiModel;
import com.rngproduction.integrationCoreOW.models.errorModels.ErrorResponse;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExtRequest;
import com.rngproduction.integrationCoreOW.models.extModels.responses.AcceptPayRsExt.AcceptPayRsExtModel;
import com.rngproduction.integrationCoreOW.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.LinkedHashMap;

/**
 * @author e.karyagin
 */

public class AcceptPayStateRsTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptPayStateRsTask.class);

    private ApiContext apiContext;
    private Utils utils;

    public AcceptPayStateRsTask(ApiContext ctx, Utils utils) {
        this.apiContext = ctx;
        this.utils = utils;
    }

    @Override
    public void run() {
        HttpHeaders headers = utils.getHeaders();
        ExtRequest rqExtModel = apiContext.getExtRequest();
        String url = apiContext.getUrl();

        HttpEntity<Object> requestBody = new HttpEntity<>(rqExtModel, headers);
        RestTemplate restTemplate = utils.getRestTemplate();
        ResponseEntity<Object> response = null;
        LinkedHashMap<?, ?> responseBody;

        try {
            LOGGER.info("AcceptPay | send status document");
            response = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestBody,
                    Object.class);
            responseBody = (LinkedHashMap<?, ?>) response.getBody();
            if (responseBody == null) {
                apiContext.setErrorResponse(utils.setErrorRs(HttpStatus.NOT_FOUND));
                return;
            }
            AcceptPayRsApiModel acceptPayRsApiModel = new AcceptPayRsApiModel();
            acceptPayRsApiModel.setResponse(responseBody);

            ErrorResponse errorResponse = utils.checkErrorsRs(acceptPayRsApiModel, RsTag.GETDOCUMENTSRS);
            DeferredResult<Object> res = apiContext.getDeferredResult();

            if (errorResponse != null) {
                res.setErrorResult(errorResponse);
            } else {
                AcceptPayRsExtModel rsExtModel = utils.acceptPayRsExtModelParse(acceptPayRsApiModel);
                if (rsExtModel != null && !rsExtModel.getDocuments().isEmpty())
                    res.setResult(new ResponseEntity<>(rsExtModel, HttpStatus.OK));
            }
        } catch (Exception e) {
            LOGGER.error("Error | " + e.getMessage());
            ErrorResponse errorResponse = utils.setErrorRs((HttpStatus) (response != null ? response.getStatusCode() : HttpStatus.SERVICE_UNAVAILABLE));
            apiContext.setErrorResponse(errorResponse);
        }
    }
}
