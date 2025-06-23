package com.rngproduction.integrationCoreOW.tasks;

import com.rngproduction.integrationCoreOW.configuration.RsTag;
import com.rngproduction.integrationCoreOW.ctx.ApiContext;
import com.rngproduction.integrationCoreOW.models.apiModels.responses.PayDocInfoRsPutApi.PayDocInfoRsPutApiModel;
import com.rngproduction.integrationCoreOW.models.errorModels.ErrorResponse;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExtRequest;
import com.rngproduction.integrationCoreOW.models.extModels.responses.PayDocInfoRsPutExt.PayDocInfoRsPutExtModel;
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

public class PayDocInfoStateRsTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayDocInfoStateRsTask.class);

    private ApiContext apiContext;
    private Utils utils;

    public PayDocInfoStateRsTask(ApiContext apiContext, Utils utils) {
        this.apiContext = apiContext;
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
            LOGGER.info("PayDocInfo | send status document");
            response = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestBody,
                    Object.class);
            responseBody = (LinkedHashMap<?, ?>) response.getBody();
            if (responseBody == null) {
                apiContext.setErrorResponse(utils.setErrorRs(HttpStatus.NOT_FOUND));
                return;
            }
            PayDocInfoRsPutApiModel payDocInfoRsPutApiModel = new PayDocInfoRsPutApiModel();
            payDocInfoRsPutApiModel.setResponse(responseBody);

            ErrorResponse errorResponse = utils.checkErrorsRs(payDocInfoRsPutApiModel, RsTag.PUTDOCUMENTSRS);
            DeferredResult<Object> res = apiContext.getDeferredResult();

            if (errorResponse != null) {
                res.setErrorResult(errorResponse);
            } else {
                PayDocInfoRsPutExtModel rsPutExtModel = utils.payDocInfoRsPutExtModelParse(payDocInfoRsPutApiModel);
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
