package com.rngproduction.integrationCoreOW.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rngproduction.integrationCoreOW.configuration.Property;
import com.rngproduction.integrationCoreOW.ctx.ApiContext;
import com.rngproduction.integrationCoreOW.models.errorModels.ErrorResponse;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExtRequest;
import com.rngproduction.integrationCoreOW.tasks.AcceptPayStateRsTask;
import com.rngproduction.integrationCoreOW.tasks.ExecPayStateRsTask;
import com.rngproduction.integrationCoreOW.tasks.PayDocAcceptStateRsTask;
import com.rngproduction.integrationCoreOW.tasks.PayDocInfoStateRsTask;
import com.rngproduction.integrationCoreOW.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author e.karyagin
 */

@Service
public class HTTPService {

    @Autowired
    Utils utils;
    @Autowired
    Property property;
    @Autowired
    ThreadPoolTaskScheduler taskScheduler;

    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPService.class);

    public void sendRqPayDocAcceptRequest(ApiContext ctx) {

        LOGGER.info("PayDocAccept | sendRqPayDocAcceptRequest");

        String owURL = utils.getParamApiRequest(ctx, "owurl");
        String urlPayDocAcceptGetDocumentsRS = (owURL != null ? owURL : property.getOwURL());
        if (property.getUseEmulate()) {
            urlPayDocAcceptGetDocumentsRS = property.getOwEmulateURL() + property.getEMULATE_PAYDOCACCEPT_GETDOCUMENTSRS();
        }

        ExtRequest rqExtModel = utils.createPayDocAcceptRqExtModel(ctx);

        HttpHeaders headers = utils.getHeaders();
        HttpEntity<?> requestBody = new HttpEntity<>(rqExtModel, headers);
        RestTemplate restTemplate = utils.getRestTemplate();
        LinkedHashMap<?, ?> responseBody;
        ResponseEntity<Object> response = null;
        try {
            response = restTemplate.exchange(urlPayDocAcceptGetDocumentsRS,
                    HttpMethod.POST,
                    requestBody,
                    Object.class);

            responseBody = (LinkedHashMap<?, ?>) response.getBody();
            PayDocAcceptRsApiModel payDocAcceptRsApiModel = new PayDocAcceptRsApiModel();
            payDocAcceptRsApiModel.setResponse(responseBody);
            ctx.setApiResponse(payDocAcceptRsApiModel);
        } catch (RestClientException e) {
            LOGGER.error("Error | " + e.getMessage());
            ErrorResponse errorResponse = utils.setErrorRs((HttpStatus) (response != null ? response.getStatusCode() : HttpStatus.SERVICE_UNAVAILABLE));
            ctx.setErrorResponse(errorResponse);
        }
    }

    public void sendRqPayDocAcceptPutRequest(ApiContext ctx) {

        LOGGER.info("PayDocAccept | sendRqPayDocAcceptPutRequest");

        String owURL = utils.getParamApiRequest(ctx, "owurl");
        String urlPayDocAcceptPutDocumentsRS = (owURL != null ? owURL : property.getOwURL());
        if (property.getUseEmulate()) {
            urlPayDocAcceptPutDocumentsRS = property.getOwEmulateURL() + property.getEMULATE_PAYDOCACCEPT_PUTDOCUMENTSRS();
        }
        ctx.setUrl(urlPayDocAcceptPutDocumentsRS);
        try {
            utils.createPayDocAcceptRqPutExtModel(ctx);
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            LOGGER.error("Error | createPayDocAcceptRqPutExtModel | " + e.getMessage());
            ctx.setErrorResponse(utils.setErrorRs(HttpStatus.BAD_REQUEST));
            return;
        }
        if (ctx.getExtRequest() == null) {
            ctx.setErrorResponse(utils.setErrorRs(HttpStatus.NOT_FOUND));
            return;
        }
        sendRequestInTask(ctx, PayDocAcceptStateRsTask.class);
    }

    public void sendRqPayDocInfoRequest(ApiContext ctx) {

        LOGGER.info("PayDocInfo | sendRqPayDocInfoRequest");

        String owURL = utils.getParamApiRequest(ctx, "owurl");
        String urlPayDocInfoGetDocumentsRS = (owURL != null ? owURL : property.getOwURL());
        if (property.getUseEmulate()) {
            urlPayDocInfoGetDocumentsRS = property.getOwEmulateURL() + property.getEMULATE_PAYDOCINFO_GETDOCUMENTSRS();
        }

        ExtRequest rqExtModel = utils.createPayDocInfoRqExtModel(ctx);
        HttpHeaders headers = utils.getHeaders();
        HttpEntity<?> requestBody = new HttpEntity<>(rqExtModel, headers);
        RestTemplate restTemplate = utils.getRestTemplate();
        LinkedHashMap<?, ?> responseBody;
        ResponseEntity<Object> response = null;
        try {
            response = restTemplate.exchange(urlPayDocInfoGetDocumentsRS,
                    HttpMethod.POST,
                    requestBody,
                    Object.class);

            responseBody = (LinkedHashMap<?, ?>) response.getBody();
            PayDocInfoRsApiModel payDocInfoRsApiModel = new PayDocInfoRsApiModel();
            payDocInfoRsApiModel.setResponse(responseBody);
            ctx.setApiResponse(payDocInfoRsApiModel);
        } catch (RestClientException e) {
            LOGGER.error("Error | " + e.getMessage());
            ErrorResponse errorResponse = utils.setErrorRs((HttpStatus) (response != null ? response.getStatusCode() : HttpStatus.SERVICE_UNAVAILABLE));
            ctx.setErrorResponse(errorResponse);
        }
    }

    public void sendRqPayDocInfoPutRequest(ApiContext ctx) {

        LOGGER.info("PayDocInfo | sendRqPayDocInfoPutRequest");

        String owURL = utils.getParamApiRequest(ctx, "owurl");
        String urlPayDocInfoPutDocumentsRS = (owURL != null ? owURL : property.getOwURL());
        if (property.getUseEmulate()) {
            urlPayDocInfoPutDocumentsRS = property.getOwEmulateURL() + property.getEMULATE_PAYDOCINFO_PUTDOCUMENTSRS();
        }
        ctx.setUrl(urlPayDocInfoPutDocumentsRS);
        try {
            utils.createPayDocInfoRqPutExtModel(ctx);
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            LOGGER.error("Error | createPayDocInfoRqPutExtModel | " + e.getMessage());
            ctx.setErrorResponse(utils.setErrorRs(HttpStatus.BAD_REQUEST));
            return;
        }
        if (ctx.getExtRequest() == null) {
            ctx.setErrorResponse(utils.setErrorRs(HttpStatus.NOT_FOUND));
            return;
        }
        sendRequestInTask(ctx, PayDocInfoStateRsTask.class);
    }

    public void sendRqAcceptPayRequest(ApiContext ctx) {

        LOGGER.info("AcceptPay | sendRqAcceptPayRequest");

        String owURL = utils.getParamApiRequest(ctx, "owurl");
        String urlAcceptPayGetDocumentsRS = (owURL != null ? owURL : property.getOwURL());
        if (property.getUseEmulate()) {
            urlAcceptPayGetDocumentsRS = property.getOwEmulateURL() + property.getEMULATE_ACCEPTPAY_GETDOCUMENTSRS();
        }
        ctx.setUrl(urlAcceptPayGetDocumentsRS);
        utils.createAcceptPayRqExtModel(ctx);
        if (ctx.getExtRequest() == null) {
            ctx.setErrorResponse(utils.setErrorRs(HttpStatus.NOT_FOUND));
            return;
        }
        sendRequestInTask(ctx, AcceptPayStateRsTask.class);
    }

    public void sendRqAcceptPayPutRequest(ApiContext ctx) {

        LOGGER.info("AcceptPay | sendRqAcceptPayPutRequest");

        String owURL = utils.getParamApiRequest(ctx, "owurl");
        String urlAcceptPayPutDocumentsRS = (owURL != null ? owURL : property.getOwURL());
        if (property.getUseEmulate()) {
            urlAcceptPayPutDocumentsRS = property.getOwEmulateURL() + property.getEMULATE_ACCEPTPAY_PUTDOCUMENTSRS();
        }

        ExtRequest rqExtModel;
        try {
            rqExtModel = utils.createAcceptPayRqPutExtModel(ctx);
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            LOGGER.error("Error | createAcceptPayRqPutExtModel | " + e.getMessage());
            ctx.setErrorResponse(utils.setErrorRs(HttpStatus.BAD_REQUEST));
            return;
        }

        HttpHeaders headers = utils.getHeaders();
        HttpEntity<?> requestBody = new HttpEntity<>(rqExtModel, headers);
        RestTemplate restTemplate = utils.getRestTemplate();
        LinkedHashMap<?, ?> responseBody;
        ResponseEntity<Object> response = null;
        try {
            response = restTemplate.exchange(urlAcceptPayPutDocumentsRS,
                    HttpMethod.POST,
                    requestBody,
                    Object.class);

            responseBody = (LinkedHashMap<?, ?>) response.getBody();
            AcceptPayRsPutApiModel acceptPayRsPutApiModel = new AcceptPayRsPutApiModel();
            acceptPayRsPutApiModel.setResponse(responseBody);
            ctx.setApiResponse(acceptPayRsPutApiModel);
        } catch (RestClientException e) {
            LOGGER.error("Error | " + e.getMessage());
            ErrorResponse errorResponse = utils.setErrorRs((HttpStatus) (response != null ? response.getStatusCode() : HttpStatus.SERVICE_UNAVAILABLE));
            ctx.setErrorResponse(errorResponse);
        }
    }

    public void sendRqExecPayRequest(ApiContext ctx) {

        LOGGER.info("ExecPay | sendRqExecPayRequest");

        String owURL = utils.getParamApiRequest(ctx, "owurl");
        String urlExecPayGetDocumentsRS = (owURL != null ? owURL : property.getOwURL());
        if (property.getUseEmulate()) {
            urlExecPayGetDocumentsRS = property.getOwEmulateURL() + property.getEMULATE_EXECPAY_GETDOCUMENTSRS();
        }

        ExtRequest rqExtModel = utils.createExecPayRqExtModel(ctx);

        HttpHeaders headers = utils.getHeaders();
        HttpEntity<?> requestBody = new HttpEntity<>(rqExtModel, headers);
        RestTemplate restTemplate = utils.getRestTemplate();
        LinkedHashMap<?, ?> responseBody;
        ResponseEntity<Object> response = null;
        try {
            response = restTemplate.exchange(urlExecPayGetDocumentsRS,
                    HttpMethod.POST,
                    requestBody,
                    Object.class);

            responseBody = (LinkedHashMap<?, ?>) response.getBody();
            ExecPayRsApiModel execPayRsApiModel = new ExecPayRsApiModel();
            execPayRsApiModel.setResponse(responseBody);
            ctx.setApiResponse(execPayRsApiModel);
        } catch (RestClientException e) {
            LOGGER.error("Error | " + e.getMessage());
            ErrorResponse errorResponse = utils.setErrorRs((HttpStatus) (response != null ? response.getStatusCode() : HttpStatus.SERVICE_UNAVAILABLE));
            ctx.setErrorResponse(errorResponse);
        }
    }

    public void sendRqExecPayPutRequest(ApiContext ctx) {

        LOGGER.info("ExecPay | sendRqExecPayPutRequest");

        String owURL = utils.getParamApiRequest(ctx, "owurl");
        String urlExecPayPutDocumentsRS = (owURL != null ? owURL : property.getOwURL());
        if (property.getUseEmulate()) {
            urlExecPayPutDocumentsRS = property.getOwEmulateURL() + property.getEMULATE_EXECPAY_PUTDOCUMENTSRS();
        }
        ctx.setUrl(urlExecPayPutDocumentsRS);
        try {
            utils.createExecPayRqPutExtModel(ctx);
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            LOGGER.error("Error | createExecPayRqPutExtModel | " + e.getMessage());
            ctx.setErrorResponse(utils.setErrorRs(HttpStatus.BAD_REQUEST));
            return;
        }
        if (ctx.getExtRequest() == null) {
            ctx.setErrorResponse(utils.setErrorRs(HttpStatus.NOT_FOUND));
            return;
        }
        sendRequestInTask(ctx, ExecPayStateRsTask.class);
    }

    /**
     * Запуск задачи в пуле потоков.
     * При получении результата прекращаем попытки запросов.
     *
     * @param apiContext контекст запроса-ответа
     * @param clazz      позволяет запускать различные реализации
     */
    private <T> void sendRequestInTask(ApiContext apiContext, Class<T> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor(ApiContext.class, Utils.class);
            ScheduledFuture<?> scheduler = taskScheduler.scheduleAtFixedRate(
                    (Runnable) constructor.newInstance(apiContext, utils),
                    Instant.ofEpochSecond(property.getTaskSchedulerInstant()),
                    Duration.ofMillis(property.getTaskSchedulerDuration()));
            DeferredResult<?> deferredResultInTask = apiContext.getDeferredResult();
            deferredResultInTask.onCompletion(() -> scheduler.cancel(true));
        } catch (Exception e) {
            LOGGER.error("Error | sendRequestInTask | " + e.getMessage());
        }
    }
}