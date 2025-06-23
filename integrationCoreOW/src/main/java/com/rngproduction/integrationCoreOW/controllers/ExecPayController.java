package com.rngproduction.integrationCoreOW.controllers;

import com.rngproduction.integrationCoreOW.configuration.TypeRq;
import com.rngproduction.integrationCoreOW.ctx.ApiContext;
import com.rngproduction.integrationCoreOW.models.apiModels.requests.ExecPayRqApi.ExecPayRqApiModel;
import com.rngproduction.integrationCoreOW.models.apiModels.requests.ExecPayRqPutApi.ExecPayRqPutApiModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.ExecPayRsExt.ExecPayRsExtModel;
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
@RequestMapping("/api/v1/ExecPay")
@Tag(name = "ExecPay", description = "Позволяет производить обмен по типу 6")
public class ExecPayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecPayController.class);

    @Autowired
    HTTPService httpService;
    @Autowired
    Utils utils;

    /**
     * Запрос документов по типу 6
     *
     * @param rqModel модель тела запроса, входные данные
     * @return список документов, по которым еще не было ответа, либо модель ошибки
     */
    @SecurityRequirement(name = "ApiKey")
    @Operation(
            summary = "Запрос документов по типу 6",
            description = "Запрос документов по типу 6"
    )
    @PostMapping(path = "/GetDocumentsRq", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public DeferredResult<Object> getDocumentsRq(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input params model", required = true, content =
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = ExecPayRqApiModel.class)))
            @RequestBody ExecPayRqApiModel rqModel) {
        LOGGER.info("call api/ExecPay | GetDocumentsRq");

        ApiContext ctx = utils.requestContextBuilder(rqModel, TypeRq.EXECPAY_GETDOCUMENTSRQ);
        httpService.sendRqExecPayRequest(ctx);
        ExecPayRsExtModel rsExt = utils.createExecPayRsExtModel(ctx);
        DeferredResult<Object> deferredResult = ctx.getDeferredResult();
        if (ctx.getErrorResponse() != null) {
            deferredResult.setErrorResult(ctx.getErrorResponse());
        }
        deferredResult.setResult(rsExt);
        return deferredResult;
    }

    /**
     * Отправка ответа на запрос документов по типу 6
     *
     * @param rqPutModel модель тела запроса, входные данные
     * @return ответ на отправку инфорации по запросу, либо сообщение об ошибке
     */
    @SecurityRequirement(name = "ApiKey")
    @Operation(
            summary = "Передача результата запроса информации по типу 6",
            description = "Отправка ответа на запрос информации о переводе по типу 6"
    )
    @PostMapping(path = "/PutDocumentsRq", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public DeferredResult<Object> putDocumentsRq(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input params model", required = true, content =
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = ExecPayRqPutApiModel.class)))
            @RequestBody ExecPayRqPutApiModel rqPutModel) {
        LOGGER.info("call api/ExecPay | PutDocumentsRq");

        ApiContext ctx = utils.requestContextBuilder(rqPutModel, TypeRq.EXECPAY_PUTDOCUMENTSRQ);
        httpService.sendRqExecPayPutRequest(ctx);
        DeferredResult<Object> deferredResult = ctx.getDeferredResult();
        if (ctx.getErrorResponse() != null) {
            deferredResult.setErrorResult(ctx.getErrorResponse());
        }
        return deferredResult;
    }
}
