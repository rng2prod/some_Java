package com.rngproduction.integrationCoreOW.controllers;

import com.rngproduction.integrationCoreOW.configuration.TypeRq;
import com.rngproduction.integrationCoreOW.ctx.ApiContext;
import com.rngproduction.integrationCoreOW.models.apiModels.requests.AcceptPayRqApi.AcceptPayRqApiModel;
import com.rngproduction.integrationCoreOW.models.apiModels.requests.AcceptPayRqPutApi.AcceptPayRqPutApiModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.AcceptPayRsPutExt.AcceptPayRsPutExtModel;
import com.rngproduction.integrationCoreOW.services.HTTPService;
import com.rngproduction.integrationCoreOW.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author e.karyagin
 */

@RestController
@RequestMapping("/api/v1/AcceptPay")
@Tag(name = "AcceptPay", description = "Позволяет производить обмен информацией при смене статуса документа")
public class AcceptPayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptPayController.class);

    @Autowired
    HTTPService httpService;
    @Autowired
    Utils utils;

    /**
     * Информирование о приеме к исполнению документа, также при смене статуса документа
     *
     * @param rqPutModel модель тела запроса, входные данные
     * @return сообщение о принятии документа в обработку, либо сообщение об ошибке
     */
    @SecurityRequirement(name = "ApiKey")
    @Operation(
            summary = "Отправка информации при смена статуса документа",
            description = "Информирование о приеме к исполнению документа, также при смене статуса документа"
    )
    @PostMapping(path = "/PutDocumentsRq", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public DeferredResult<Object> putDocumentsRq(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input params model", required = true, content =
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = AcceptPayRqPutApiModel.class)))
            @RequestBody AcceptPayRqPutApiModel rqPutModel) {
        LOGGER.info("call api/AcceptPay | PutDocumentsRq");

        ApiContext ctx = utils.requestContextBuilder(rqPutModel, TypeRq.ACCEPTPAY_PUTDOCUMENTSRQ);
        httpService.sendRqAcceptPayPutRequest(ctx);

        AcceptPayRsPutExtModel rsExt = utils.createAcceptPayRsExtModel(ctx);
        DeferredResult<Object> deferredResult = ctx.getDeferredResult();
        if (ctx.getErrorResponse() != null) {
            deferredResult.setErrorResult(ctx.getErrorResponse());
        }
        deferredResult.setResult(rsExt);
        return deferredResult;
    }

    /**
     * Запрос результата информирования о приеме к исполнению документа
     *
     * @param rqModel модель тела запроса, входные данные
     * @return ответ на запрос результата информирования о приеме к исполнению документа, либо модель ошибки
     */
    @SecurityRequirement(name = "ApiKey")
    @Operation(
            summary = "Запрос результата отправки информации при смена статуса документа",
            description = "Запрос результата информирования о приеме к исполнению документа, либо при смене статуса документа"
    )
    @PostMapping(path = "/GetDocumentsRq", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public DeferredResult<Object> getDocumentsRq(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input params model", required = true, content =
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = AcceptPayRqApiModel.class)))
            @RequestBody AcceptPayRqApiModel rqModel) {
        LOGGER.info("call api/AcceptPay | GetDocumentsRq");

        ApiContext ctx = utils.requestContextBuilder(rqModel, TypeRq.ACCEPTPAY_GETDOCUMENTSRQ);
        httpService.sendRqAcceptPayRequest(ctx);
        DeferredResult<Object> deferredResult = ctx.getDeferredResult();
        if (ctx.getErrorResponse() != null) {
            deferredResult.setErrorResult(ctx.getErrorResponse());
        }
        return deferredResult;
    }
}