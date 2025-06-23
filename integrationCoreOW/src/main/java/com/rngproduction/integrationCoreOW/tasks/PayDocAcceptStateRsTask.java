package com.rngproduction.integrationCoreOW.tasks;

import com.rngproduction.integrationCoreOW.configuration.RsTag;
import com.rngproduction.integrationCoreOW.ctx.ApiContext;
import com.rngproduction.integrationCoreOW.models.apiModels.responses.PayDocAcceptRsPutApi.PayDocAcceptRsPutApiModel;
import com.rngproduction.integrationCoreOW.models.errorModels.ErrorResponse;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExtRequest;
import com.rngproduction.integrationCoreOW.models.extModels.responses.PayDocAcceptRsPutExt.PayDocAcceptRsPutExtModel;
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

public class PayDocAcceptStateRsTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayDocAcceptStateRsTask.class);

    private ApiContext apiContext;
    private Utils utils;

    public PayDocAcceptStateRsTask(ApiContext ctx, Utils utils) {
        this.apiContext = ctx;
        this.utils = utils;
    }

    @Override
    public void run() {
        HttpHeaders headers = utils.getHeaders();
        ExtRequest rqPutExtModel = apiContext.getExtRequest();
        String url = apiContext.getUrl();

        HttpEntity<Object> requestBody = new HttpEntity<>(rqPutExtModel, headers);
        RestTemplate restTemplate = utils.getRestTemplate();
        ResponseEntity<Object> response = null;
        LinkedHashMap<?, ?> responseBody;
        try {
            LOGGER.info("PayDocAccept | send status document");
            response = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestBody,
                    Object.class);
            responseBody = (LinkedHashMap<?, ?>) response.getBody();
            if (responseBody == null) {
                apiContext.setErrorResponse(utils.setErrorRs(HttpStatus.NOT_FOUND));
                return;
            }
            PayDocAcceptRsPutApiModel payDocAcceptRsPutApiModel = new PayDocAcceptRsPutApiModel();
            payDocAcceptRsPutApiModel.setResponse(responseBody);

            ErrorResponse errorResponse = utils.checkErrorsRs(payDocAcceptRsPutApiModel, RsTag.PUTDOCUMENTSRS);
            DeferredResult<Object> res = apiContext.getDeferredResult();

            if (errorResponse != null) {
                res.setErrorResult(errorResponse);
            } else {
                PayDocAcceptRsPutExtModel rsPutExtModel = utils.payDocAcceptRsPutExtModelParse(payDocAcceptRsPutApiModel);
                if (rsPutExtModel != null && !rsPutExtModel.getReceptionResults().isEmpty())
                    res.setResult(new ResponseEntity<>(rsPutExtModel, HttpStatus.OK));
            }
        } catch (Exception e) {
            LOGGER.error("Error | " + e.getMessage());
            ErrorResponse errorResponse = utils.setErrorRs((HttpStatus) (response != null ? response.getStatusCode() : HttpStatus.SERVICE_UNAVAILABLE));
            apiContext.setErrorResponse(errorResponse);
        }
    }
}