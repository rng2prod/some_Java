package com.rngproduction.integrationCoreOW.controllers;

import com.rngproduction.integrationCoreOW.configuration.TypeRq;
import com.rngproduction.integrationCoreOW.ctx.ApiContext;
import com.rngproduction.integrationCoreOW.models.apiModels.requests.PayDocInfoRqApi.PayDocInfoRqApiModel;
import com.rngproduction.integrationCoreOW.models.apiModels.requests.PayDocInfoRqPutApi.PayDocInfoRqPutApiModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.PayDocInfoRsExt.PayDocInfoRsExtModel;
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
@RequestMapping("/api/v1/PayDocInfo")
@Tag(name = "PayDocInfo", description = "Позволяет производить обмен по типу 2")
public class PayDocInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayDocInfoController.class);

    @Autowired
    HTTPService httpService;
    @Autowired
    Utils utils;

    /**
     * Запрос документов по типу 2
     *
     * @param rqModel модель тела запроса, входные данные
     * @return список документов, по которым еще не было ответа, либо модель ошибки
     */
    @SecurityRequirement(name = "ApiKey")
    @Operation(
            summary = "Запрос документов по типу 2",
            description = "Запрос документов по типу 2"
    )
    @PostMapping(path = "/GetDocumentsRq", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public DeferredResult<Object> getDocumentsRq(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input params model", required = true, content =
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = PayDocInfoRqApiModel.class)))
            @RequestBody PayDocInfoRqApiModel rqModel) {
        LOGGER.info("call api/PayDocInfo | GetDocumentsRq");

        ApiContext ctx = utils.requestContextBuilder(rqModel, TypeRq.PAYDOCINFO_GETDOCUMENTSRQ);
        httpService.sendRqPayDocInfoRequest(ctx);
        PayDocInfoRsExtModel rsExt = utils.createPayDocInfoRsExtModel(ctx);
        DeferredResult<Object> deferredResult = ctx.getDeferredResult();
        if (ctx.getErrorResponse() != null) {
            deferredResult.setErrorResult(ctx.getErrorResponse());
        }
        deferredResult.setResult(rsExt);
        return deferredResult;
    }

    /**
     * Передача результата получения информации по типу 2
     *
     * @param rqPutModel модель тела запроса, входные данные
     * @return ответ на запрос передачи результата получения информации, либо модель ошибки
     */
    @SecurityRequirement(name = "ApiKey")
    @Operation(
            summary = "Передача результата получения информации по типу 2",
            description = "Передача результата получения информации по типу 2"
    )
    @PostMapping(path = "/PutDocumentsRq", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public DeferredResult<Object> putDocumentsRq(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input params model", required = true, content =
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = PayDocInfoRqPutApiModel.class)))
            @RequestBody PayDocInfoRqPutApiModel rqPutModel) {
        LOGGER.info("call api/PayDocInfo | PutDocumentsRq");

        ApiContext ctx = utils.requestContextBuilder(rqPutModel, TypeRq.PAYDOCINFO_PUTDOCUMENTSRQ);
        httpService.sendRqPayDocInfoPutRequest(ctx);
        DeferredResult<Object> deferredResult = ctx.getDeferredResult();
        if (ctx.getErrorResponse() != null) {
            deferredResult.setErrorResult(ctx.getErrorResponse());
        }
        return deferredResult;
    }
}
