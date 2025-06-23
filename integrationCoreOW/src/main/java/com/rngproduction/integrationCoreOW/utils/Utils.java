package com.rngproduction.integrationCoreOW.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rngproduction.integrationCoreOW.configuration.Property;
import com.rngproduction.integrationCoreOW.configuration.RsTag;
import com.rngproduction.integrationCoreOW.configuration.TypeRq;
import com.rngproduction.integrationCoreOW.ctx.ApiContext;
import com.rngproduction.integrationCoreOW.models.apiModels.requests.ApiRequest;
import com.rngproduction.integrationCoreOW.models.apiModels.responses.AcceptPayRsApi.AcceptPayRsApiBlobModel;
import com.rngproduction.integrationCoreOW.models.apiModels.responses.ApiResponse;
import com.rngproduction.integrationCoreOW.models.apiModels.responses.ExecPayRsApi.ExecPayRsApiBlobModel;
import com.rngproduction.integrationCoreOW.models.apiModels.responses.PayDocAcceptRsApi.PayDocAcceptRsApiBlobModel;
import com.rngproduction.integrationCoreOW.models.apiModels.responses.PayDocInfoRsApi.PayDocInfoRsApiBlobModel;
import com.rngproduction.integrationCoreOW.models.errorModels.*;
import com.rngproduction.integrationCoreOW.models.extModels.requests.AcceptPayRqExt.AcceptPayRqExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.requests.AcceptPayRqPutExt.AcceptPayRqPutExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExecPayRqExt.ExecPayRqExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExecPayRqPutExt.ExecPayRqPutExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExtRequest;
import com.rngproduction.integrationCoreOW.models.extModels.requests.PayDocAcceptRqExt.PayDocAcceptRqExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.requests.PayDocAcceptRqPutExt.PayDocAcceptRqPutExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.requests.PayDocInfoRqExt.PayDocInfoRqExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.requests.PayDocInfoRqPutExt.PayDocInfoRqPutExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.AcceptPayRsExt.AcceptPayRsExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.AcceptPayRsPutExt.AcceptPayRsPutExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.ExecPayRsExt.ExecPayRsExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.ExecPayRsPutExt.ExecPayRsPutExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.PayDocAcceptRsExt.PayDocAcceptRsExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.PayDocAcceptRsPutExt.PayDocAcceptRsPutExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.PayDocInfoRsExt.PayDocInfoRsExtModel;
import com.rngproduction.integrationCoreOW.models.extModels.responses.PayDocInfoRsPutExt.PayDocInfoRsPutExtModel;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author e.karyagin
 */

@Component
public class Utils {

    @Autowired
    Property property;

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    public ExtRequest createPayDocAcceptRqExtModel(ApiContext ctx) {
        Map<String, String> params = getParamMapApiRequest(ctx);
        /*
         * hidden code part
         */

        PayDocAcceptRqExtModel payDocAcceptRqExtModel = new PayDocAcceptRqExtModel();

        /*
         * hidden code part
         */

        ctx.setExtRequest(payDocAcceptRqExtModel);
        return payDocAcceptRqExtModel;
    }

    public void createPayDocAcceptRqPutExtModel(ApiContext ctx) throws JsonProcessingException, UnsupportedEncodingException {
        Map<String, String> params = getParamMapApiRequest(ctx);
        /*
         * hidden code part
         */

        PayDocAcceptRqPutExtModel payDocAcceptRqPutExtModel = new PayDocAcceptRqPutExtModel();

        /*
         * hidden code part
         */

        PayDocAcceptRqPutExtBlobModel_AcceptResponse acceptResponse = new PayDocAcceptRqPutExtBlobModel_AcceptResponse();

        /*
         * hidden code part
         */

        if (params.get("resultCode") == null) {
            resultCode.setResultCode("2");
            rejectionReason.setRejectionReason("5");
        } else {
            switch (params.get("resultCode")) {
                case "OK" -> {
                    resultCode.setResultCode("1");
                    acceptResponse.setRejectionReason(null);
                }
                case "ACC_NOT_FOUND" -> {
                    resultCode.setResultCode("2");
                    rejectionReason.setRejectionReason("1");
                }
                case "NOT_AVAILABLE" -> {
                    resultCode.setResultCode("2");
                    rejectionReason.setRejectionReason("3");
                }
                case "ACC_NOT_CORRECT" -> {
                    resultCode.setResultCode("2");
                    rejectionReason.setRejectionReason("4");
                }
                default -> {
                    resultCode.setResultCode("2");
                    rejectionReason.setRejectionReason("5");
                }
            }
        }

        String blob = mapperWrite(acceptResponse);
        if (blob.isEmpty()) return;
        String encodedBlob = encodeBase(blob);
        blobModel.setBlob(encodedBlob);

        ctx.setExtRequest(payDocAcceptRqPutExtModel);
    }

    public ExtRequest createPayDocInfoRqExtModel(ApiContext ctx) {
        Map<String, String> params = getParamMapApiRequest(ctx);
        /*
         * hidden code part
         */

        PayDocInfoRqExtModel payDocInfoRqExtModel = new PayDocInfoRqExtModel();

        /*
         * hidden code part
         */

        ctx.setExtRequest(payDocInfoRqExtModel);
        return payDocInfoRqExtModel;
    }

    public void createPayDocInfoRqPutExtModel(ApiContext ctx) throws JsonProcessingException, UnsupportedEncodingException {
        Map<String, String> params = getParamMapApiRequest(ctx);
        /*
         * hidden code part
         */

        PayDocInfoRqPutExtModel payDocInfoRqPutExtModel = new PayDocInfoRqPutExtModel();

        /*
         * hidden code part
         */

        PayDocInfoRqPutExtBlobModel_InfoResponse infoResponse = new PayDocInfoRqPutExtBlobModel_InfoResponse();

        /*
         * hidden code part
         */

        if (params.get("resultCode") == null) {
            resultCode.setResultCode("2");
            rejectionReason.setRejectionReason("5");
        } else {
            switch (params.get("resultCode")) {
                case "OK" -> {
                    resultCode.setResultCode("1");
                    infoResponse.setRejectionReason(null);
                }
                case "ACC_NOT_FOUND" -> {
                    resultCode.setResultCode("2");
                    rejectionReason.setRejectionReason("1");
                }
                case "NOT_AVAILABLE" -> {
                    resultCode.setResultCode("2");
                    rejectionReason.setRejectionReason("3");
                }
                case "ACC_NOT_CORRECT" -> {
                    resultCode.setResultCode("2");
                    rejectionReason.setRejectionReason("4");
                }
                default -> {
                    resultCode.setResultCode("2");
                    rejectionReason.setRejectionReason("5");
                }
            }
        }

        String blob = mapperWrite(infoResponse);
        if (blob.isEmpty()) return;
        String encodedBlob = encodeBase(blob);
        blobModel.setBlob(encodedBlob);

        ctx.setExtRequest(payDocInfoRqPutExtModel);
    }

    public ExtRequest createAcceptPayRqPutExtModel(ApiContext ctx) throws JsonProcessingException, UnsupportedEncodingException {
        Map<String, String> params = getParamMapApiRequest(ctx);
        /*
         * hidden code part
         */

        AcceptPayRqPutExtModel acceptPayRqPutExtModel = new AcceptPayRqPutExtModel();

        /*
         * hidden code part
         */

        AcceptPayRqPutExtBlobModel_AcceptPayRequest acceptPayRequest = new AcceptPayRqPutExtBlobModel_AcceptPayRequest();

        /*
         * hidden code part
         */

        String blob = mapperWrite(acceptPayRequest);
        if (blob.isEmpty()) return null;
        String encodedBlob = encodeBase(blob);
        blobModel.setBlob(encodedBlob);

        ctx.setExtRequest(acceptPayRqPutExtModel);
        return acceptPayRqPutExtModel;
    }

    public void createAcceptPayRqExtModel(ApiContext ctx) {
        Map<String, String> params = getParamMapApiRequest(ctx);
        /*
         * hidden code part
         */

        AcceptPayRqExtModel acceptPayRqExtModel = new AcceptPayRqExtModel();

        /*
         * hidden code part
         */

        ctx.setExtRequest(acceptPayRqExtModel);
    }

    public ExtRequest createExecPayRqExtModel(ApiContext ctx) {
        Map<String, String> params = getParamMapApiRequest(ctx);
        /*
         * hidden code part
         */

        ExecPayRqExtModel execPayRqExtModel = new ExecPayRqExtModel();

        /*
         * hidden code part
         */

        ctx.setExtRequest(execPayRqExtModel);
        return execPayRqExtModel;
    }

    public void createExecPayRqPutExtModel(ApiContext ctx) throws JsonProcessingException, UnsupportedEncodingException {
        Map<String, String> params = getParamMapApiRequest(ctx);
        /*
         * hidden code part
         */

        ExecPayRqPutExtModel execPayRqPutExtModel = new ExecPayRqPutExtModel();

        /*
         * hidden code part
         */

        ExecPayRqPutExtBlobModel_ExecPayResponse execPayResponse = new ExecPayRqPutExtBlobModel_ExecPayResponse();

        /*
         * hidden code part
         */

        String blob = mapperWrite(execPayResponse);
        if (blob.isEmpty()) return;
        String encodedBlob = encodeBase(blob);
        blobModel.setBlob(encodedBlob);

        ctx.setExtRequest(execPayRqPutExtModel);
    }

    public PayDocAcceptRsExtModel createPayDocAcceptRsExtModel(ApiContext ctx) {
        PayDocAcceptRsExtModel rsExtModel;

        ApiResponse apiResponse = ctx.getApiResponse();
        if (apiResponse == null) return null;
        ErrorResponse errorResponse = checkErrorsRs(apiResponse, RsTag.GETDOCUMENTSRS);
        if (errorResponse != null) {
            ctx.setErrorResponse(errorResponse);
            return null;
        }
        rsExtModel = payDocAcceptRsExtModelParse(apiResponse);
        if (rsExtModel == null) {
            ErrorResponse errNoInfo = setErrorRs(HttpStatus.NOT_FOUND);
            ctx.setErrorResponse(errNoInfo);
            return null;
        }
        ctx.setExtResponse(rsExtModel);
        return rsExtModel;
    }

    public PayDocInfoRsExtModel createPayDocInfoRsExtModel(ApiContext ctx) {
        PayDocInfoRsExtModel rsExtModel;

        ApiResponse apiResponse = ctx.getApiResponse();
        if (apiResponse == null) return null;
        ErrorResponse errorResponse = checkErrorsRs(apiResponse, RsTag.GETDOCUMENTSRS);
        if (errorResponse != null) {
            ctx.setErrorResponse(errorResponse);
            return null;
        }
        rsExtModel = payDocInfoRsExtModelParse(apiResponse);
        if (rsExtModel == null) {
            ErrorResponse errNoInfo = setErrorRs(HttpStatus.NOT_FOUND);
            ctx.setErrorResponse(errNoInfo);
            return null;
        }
        ctx.setExtResponse(rsExtModel);
        return rsExtModel;
    }

    public AcceptPayRsPutExtModel createAcceptPayRsExtModel(ApiContext ctx) {
        AcceptPayRsPutExtModel rsExtModel;

        ApiResponse apiResponse = ctx.getApiResponse();
        if (apiResponse == null) return null;
        ErrorResponse errorResponse = checkErrorsRs(apiResponse, RsTag.PUTDOCUMENTSRS);
        if (errorResponse != null) {
            ctx.setErrorResponse(errorResponse);
            return null;
        }
        rsExtModel = acceptPayRsPutExtModelParse(apiResponse);
        ctx.setExtResponse(rsExtModel);
        return rsExtModel;
    }

    public ExecPayRsExtModel createExecPayRsExtModel(ApiContext ctx) {
        ExecPayRsExtModel rsExtModel;

        ApiResponse apiResponse = ctx.getApiResponse();
        if (apiResponse == null) return null;
        ErrorResponse errorResponse = checkErrorsRs(apiResponse, RsTag.GETDOCUMENTSRS);
        if (errorResponse != null) {
            ctx.setErrorResponse(errorResponse);
            return null;
        }
        rsExtModel = execPayRsExtModelParse(apiResponse);
        if (rsExtModel == null) {
            ErrorResponse errNoInfo = setErrorRs(HttpStatus.NOT_FOUND);
            ctx.setErrorResponse(errNoInfo);
            return null;
        }
        ctx.setExtResponse(rsExtModel);
        return rsExtModel;
    }

    private PayDocAcceptRsExtModel payDocAcceptRsExtModelParse(ApiResponse apiResponse) {
        ArrayList<LinkedHashMap<?, ?>> documentList;
        try {
            documentList = getDocumentListFromApiResponse(apiResponse);
        } catch (AssertionError err) {
            LOGGER.error("AssertionError | payDocAcceptRsExtModelParse | " + err.getMessage());
            return null;
        }

        PayDocAcceptRsExtModel rsExtModel = new PayDocAcceptRsExtModel();
        documentList.forEach(document -> {
            LinkedHashMap<?, ?> binaryDocument;
            try {
                binaryDocument = getBinaryDocument(document);
            } catch (AssertionError err) {
                LOGGER.error("AssertionError | payDocAcceptRsExtModelParse | getBinaryDocument | " + err.getMessage());
                return;
            }

            /*
             * hidden code part
             */
            String blob = (String) binaryDocument.get("BLOB");

            PayDocAcceptRsApiBlobModel rsApiBlobModel = null;
            try {
                rsApiBlobModel = mapperRead(decodeBase(blob), PayDocAcceptRsApiBlobModel.class);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("UnsupportedEncodingException | PayDocAcceptRsApiBlobModel | " + e.getMessage());
            } catch (JsonProcessingException e) {
                LOGGER.error("JsonProcessingException | PayDocAcceptRsApiBlobModel | " + e.getMessage());
            }
            if (rsApiBlobModel == null) return;

            /*
             * hidden code part
             */

            PayDocAcceptRsExtModel_Document rsExtModelDoc = new PayDocAcceptRsExtModel_Document();

            /*
             * hidden code part
             */

            rsExtModel.addDocument(rsExtModelDoc);
        });
        return rsExtModel;
    }

    private PayDocInfoRsExtModel payDocInfoRsExtModelParse(ApiResponse apiResponse) {
        ArrayList<LinkedHashMap<?, ?>> documentList;
        try {
            documentList = getDocumentListFromApiResponse(apiResponse);
        } catch (AssertionError err) {
            LOGGER.error("AssertionError | payDocInfoRsExtModelParse | " + err.getMessage());
            return null;
        }

        PayDocInfoRsExtModel rsExtModel = new PayDocInfoRsExtModel();
        documentList.forEach(document -> {
            LinkedHashMap<?, ?> binaryDocument;
            try {
                binaryDocument = getBinaryDocument(document);
            } catch (AssertionError err) {
                LOGGER.error("AssertionError | payDocInfoRsExtModelParse | getBinaryDocument | " + err.getMessage());
                return;
            }

            /*
             * hidden code part
             */
            String blob = (String) binaryDocument.get("BLOB");

            PayDocInfoRsApiBlobModel rsApiBlobModel = null;
            try {
                rsApiBlobModel = mapperRead(decodeBase(blob), PayDocInfoRsApiBlobModel.class);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("UnsupportedEncodingException | PayDocInfoRsApiBlobModel | " + e.getMessage());
            } catch (JsonProcessingException e) {
                LOGGER.error("JsonProcessingException | PayDocInfoRsApiBlobModel | " + e.getMessage());
            }
            if (rsApiBlobModel == null) return;

            /*
             * hidden code part
             */

            PayDocInfoRsExtModel_Document rsExtModelDoc = new PayDocInfoRsExtModel_Document();

            /*
             * hidden code part
             */

            rsExtModel.addDocument(rsExtModelDoc);
        });
        return rsExtModel;
    }

    public AcceptPayRsExtModel acceptPayRsExtModelParse(ApiResponse apiResponse) {
        ArrayList<LinkedHashMap<?, ?>> documentList;
        try {
            documentList = getDocumentListFromApiResponse(apiResponse);
        } catch (AssertionError err) {
            LOGGER.error("AssertionError | acceptPayRsExtModelParse | " + err.getMessage());
            return null;
        }

        AcceptPayRsExtModel rsExtModel = new AcceptPayRsExtModel();
        documentList.forEach(document -> {
            LinkedHashMap<?, ?> binaryDocument;
            try {
                binaryDocument = getBinaryDocument(document);
            } catch (AssertionError err) {
                LOGGER.error("AssertionError | acceptPayRsExtModelParse | getBinaryDocument | " + err.getMessage());
                return;
            }

            /*
             * hidden code part
             */
            String blob = (String) binaryDocument.get("BLOB");

            AcceptPayRsApiBlobModel rsApiBlobModel = null;
            try {
                rsApiBlobModel = mapperRead(decodeBase(blob), AcceptPayRsApiBlobModel.class);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("UnsupportedEncodingException | AcceptPayRsApiBlobModel | " + e.getMessage());
            } catch (JsonProcessingException e) {
                LOGGER.error("JsonProcessingException | AcceptPayRsApiBlobModel | " + e.getMessage());
            }
            if (rsApiBlobModel == null) return;
            AcceptPayRsApiBlobModel_ProcessResult processResult = rsApiBlobModel.getProcessResult();
            if (!Objects.equals(processResult.getCode(), property.getStateCorrect())) return;
            // выход при статусе ответа, отличном от успешного

            AcceptPayRsExtModel_Document rsExtModelDoc = new AcceptPayRsExtModel_Document();

            /*
             * hidden code part
             */

            rsExtModel.addDocument(rsExtModelDoc);
        });
        return rsExtModel;
    }

    public ExecPayRsExtModel execPayRsExtModelParse(ApiResponse apiResponse) {
        ArrayList<LinkedHashMap<?, ?>> documentList;
        try {
            documentList = getDocumentListFromApiResponse(apiResponse);
        } catch (AssertionError err) {
            LOGGER.error("AssertionError | execPayRsExtModelParse | " + err.getMessage());
            return null;
        }

        ExecPayRsExtModel rsExtModel = new ExecPayRsExtModel();
        documentList.forEach(document -> {
            LinkedHashMap<?, ?> binaryDocument;
            try {
                binaryDocument = getBinaryDocument(document);
            } catch (AssertionError err) {
                LOGGER.error("AssertionError | execPayRsExtModelParse | getBinaryDocument | " + err.getMessage());
                return;
            }

            /*
             * hidden code part
             */
            String blob = (String) binaryDocument.get("BLOB");

            ExecPayRsApiBlobModel rsApiBlobModel = null;
            try {
                rsApiBlobModel = mapperRead(decodeBase(blob), ExecPayRsApiBlobModel.class);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("UnsupportedEncodingException | ExecPayRsApiBlobModel | " + e.getMessage());
            } catch (JsonProcessingException e) {
                LOGGER.error("JsonProcessingException | ExecPayRsApiBlobModel | " + e.getMessage());
            }
            if (rsApiBlobModel == null) return;

            /*
             * hidden code part
             */

            ExecPayRsExtModel_Document rsExtModelDoc = new ExecPayRsExtModel_Document();

            /*
             * hidden code part
             */

            rsExtModel.addDocument(rsExtModelDoc);
        });
        return rsExtModel;
    }

    public PayDocAcceptRsPutExtModel payDocAcceptRsPutExtModelParse(ApiResponse apiResponse) {
        ArrayList<LinkedHashMap<?, ?>> resultList;
        try {
            resultList = getResultListFromApiResponse(apiResponse);
        } catch (AssertionError err) {
            LOGGER.error("AssertionError | payDocAcceptRsPutExtModelParse | " + err.getMessage());
            return null;
        }

        PayDocAcceptRsPutExtModel rsPutExtModel = new PayDocAcceptRsPutExtModel();
        resultList.forEach(receptionResult -> {

            /*
             * hidden code part
             */

            if (!Objects.equals(objectStatus, property.getStateCorrectPutRS())) return;
            // выход при статусе ответа, отличном от успешного

            PayDocAcceptRsPutExtModel_ReceptionResult rsPutExtModelRes = new PayDocAcceptRsPutExtModel_ReceptionResult();

            /*
             * hidden code part
             */

            rsPutExtModel.addReceptionResult(rsPutExtModelRes);
        });
        return rsPutExtModel;
    }

    public PayDocInfoRsPutExtModel payDocInfoRsPutExtModelParse(ApiResponse apiResponse) {
        ArrayList<LinkedHashMap<?, ?>> resultList;
        try {
            resultList = getResultListFromApiResponse(apiResponse);
        } catch (AssertionError err) {
            LOGGER.error("AssertionError | payDocInfoRsPutExtModelParse | " + err.getMessage());
            return null;
        }

        PayDocInfoRsPutExtModel rsPutExtModel = new PayDocInfoRsPutExtModel();
        resultList.forEach(receptionResult -> {

            /*
             * hidden code part
             */

            if (!Objects.equals(objectStatus, property.getStateCorrectPutRS())) return;
            // выход при статусе ответа, отличном от успешного

            PayDocInfoRsPutExtModel_ReceptionResult rsPutExtModelRes = new PayDocInfoRsPutExtModel_ReceptionResult();

            /*
             * hidden code part
             */

            rsPutExtModel.addReceptionResult(rsPutExtModelRes);
        });
        return rsPutExtModel;
    }

    public ExecPayRsPutExtModel execPayRsPutExtModelParse(ApiResponse apiResponse) {
        ArrayList<LinkedHashMap<?, ?>> resultList;
        try {
            resultList = getResultListFromApiResponse(apiResponse);
        } catch (AssertionError err) {
            LOGGER.error("AssertionError | execPayRsPutExtModelParse | " + err.getMessage());
            return null;
        }

        ExecPayRsPutExtModel rsPutExtModel = new ExecPayRsPutExtModel();
        resultList.forEach(receptionResult -> {

            /*
             * hidden code part
             */

            if (!Objects.equals(objectStatus, property.getStateCorrectPutRS())) return;
            // выход при статусе ответа, отличном от успешного

            ExecPayRsPutExtModel_ReceptionResult rsPutExtModelRes = new ExecPayRsPutExtModel_ReceptionResult();

            /*
             * hidden code part
             */

            rsPutExtModel.addReceptionResult(rsPutExtModelRes);
        });
        return rsPutExtModel;
    }

    private AcceptPayRsPutExtModel acceptPayRsPutExtModelParse(ApiResponse apiResponse) {
        ArrayList<LinkedHashMap<?, ?>> resultList;
        try {
            resultList = getResultListFromApiResponse(apiResponse);
        } catch (AssertionError err) {
            LOGGER.error("AssertionError | acceptPayRsPutExtModelParse | " + err.getMessage());
            return null;
        }

        AcceptPayRsPutExtModel rsPutExtModel = new AcceptPayRsPutExtModel();
        resultList.forEach(receptionResult -> {

            /*
             * hidden code part
             */

            AcceptPayRsPutExtModel_ReceptionResult rsPutExtModelRes = new AcceptPayRsPutExtModel_ReceptionResult();

            /*
             * hidden code part
             */

            rsPutExtModel.addReceptionResult(rsPutExtModelRes);
        });
        return rsPutExtModel;
    }

    @SuppressWarnings("unchecked")
    public ErrorResponse checkErrorsRs(ApiResponse apiResponse, RsTag rsTag) {
        LinkedHashMap<?, ?> response = apiResponse.getResponse();
        LinkedHashMap<?, ?> body = (LinkedHashMap<?, ?>) response.get(RsTag.BODY.get());
        if (body == null) return setErrorRs(HttpStatus.NOT_FOUND);
        LinkedHashMap<?, ?> rsTagOper = null;
        String tagContainer = null;
        String ref = null;
        if (body.get(RsTag.FAULT.get()) != null) {
            rsTagOper = (LinkedHashMap<?, ?>) body.get(RsTag.FAULT.get());
            tagContainer = RsTag.DETAIL.get();
        } else {
            switch (rsTag) {
                case GETDOCUMENTSRS -> {
                    if (body.get(rsTag.get()) instanceof LinkedHashMap<?, ?>) {
                        rsTagOper = (LinkedHashMap<?, ?>) body.get(rsTag.get());
                        tagContainer = RsTag.ERRORS.get();
                    } else return null;
                }
                case PUTDOCUMENTSRS -> {
                    if (body.get(rsTag.get()) instanceof LinkedHashMap<?, ?> getRsTagOper) {
                        LinkedHashMap<?, ?> receptionResults = (LinkedHashMap<?, ?>) getRsTagOper.get(RsTag.RECEPTIONRESULTS.get());
                        if (receptionResults.get(RsTag.RECEPTIONRESULT.get()) instanceof LinkedHashMap<?, ?>) {
                            rsTagOper = (LinkedHashMap<?, ?>) receptionResults.get(RsTag.RECEPTIONRESULT.get());
                            tagContainer = RsTag.ERRORS.get();
                        } else if (receptionResults.get(RsTag.RECEPTIONRESULT.get()) instanceof ArrayList<?>) {
                            rsTagOper = (LinkedHashMap<?, ?>) ((ArrayList<?>) receptionResults.get(RsTag.RECEPTIONRESULT.get())).get(0);
                            // берем первый элемент, считаем что на данный запрос ответ с множеством ReceptionResult не вероятен
                            tagContainer = RsTag.ERRORS.get();
                        }
                        if (rsTagOper != null) {
                            ref = (String) rsTagOper.get("objectID");
                        }
                    } else return null;
                }
            }
        }

        if (rsTagOper != null && rsTagOper.get(tagContainer) != null) {
            LinkedHashMap<?, ?> errors = (LinkedHashMap<?, ?>) rsTagOper.get(tagContainer);

            LinkedHashMap<?, ?> err;
            ArrayList<LinkedHashMap<?, ?>> errorlist = new ArrayList<>();
            if (errors.get(RsTag.ERROR.get()) instanceof LinkedHashMap<?, ?>) {
                err = (LinkedHashMap<?, ?>) errors.get(RsTag.ERROR.get());
                if (err == null) throw new AssertionError();
                errorlist.add(err);
            } else if (errors.get(RsTag.ERROR.get()) instanceof ArrayList<?>) {
                errorlist = (ArrayList<LinkedHashMap<?, ?>>) errors.get(RsTag.ERROR.get());
                if (errorlist.isEmpty()) throw new AssertionError();
            }

            ErrorExtRsModel errorExtRsModel = new ErrorExtRsModel();
            ErrorExtRsModel_ReceptionResult errorExtRsModelRecRes = errorExtRsModel.getReceptionResult();
            errorExtRsModelRecRes.setRef(ref);
            errorlist.forEach(error -> {
                /*
                 * hidden code part
                 */

                ErrorExtRsModel_Error errExtRsModelError = new ErrorExtRsModel_Error();
                /*
                 * hidden code part
                 */
                errorExtRsModelRecRes.addError(errExtRsModelError);
            });
            return errorExtRsModel;
        } else return null;
    }

    public ErrorResponse setErrorRs(HttpStatus code) {
        try {
            ErrorExtRsModel errorExtRsModel = new ErrorExtRsModel();
            ErrorExtRsModel_ReceptionResult errorExtRsModelRecRes = errorExtRsModel.getReceptionResult();
            ErrorExtRsModel_Error errExtRsModelError = new ErrorExtRsModel_Error();
            ErrorExtRsModel_Code codeModel = errExtRsModelError.getCode();
            ErrorExtRsModel_Message messageModel = errExtRsModelError.getMessage();
            codeModel.setCode(String.valueOf(code.value()));
            switch (code) {
                case BAD_REQUEST -> messageModel.setMessage("400 Bad Request");
                case UNAUTHORIZED -> messageModel.setMessage("401 Unauthorized");
                case PAYMENT_REQUIRED -> messageModel.setMessage("402 Payment Required");
                case FORBIDDEN -> messageModel.setMessage("403 Forbidden");
                case NOT_FOUND -> messageModel.setMessage("404 Not Found");
                case METHOD_NOT_ALLOWED -> messageModel.setMessage("405 Method Not Allowed");
                case NOT_ACCEPTABLE -> messageModel.setMessage("406 Not Acceptable");
                case PROXY_AUTHENTICATION_REQUIRED -> messageModel.setMessage("407 Proxy Authentication Required");
                case REQUEST_TIMEOUT -> messageModel.setMessage("408 Request Timeout");
                case CONFLICT -> messageModel.setMessage("409 Conflict");
                case UNSUPPORTED_MEDIA_TYPE -> messageModel.setMessage("415 Unsupported Media Type");
                case EXPECTATION_FAILED -> messageModel.setMessage("417 Expectation Failed");
                case UNPROCESSABLE_ENTITY -> messageModel.setMessage("422 Unprocessable Entity");
                case LOCKED -> messageModel.setMessage("423 Locked");
                case FAILED_DEPENDENCY -> messageModel.setMessage("424 Failed Dependency");
                case INTERNAL_SERVER_ERROR -> messageModel.setMessage("500 Internal Server Error");
                case SERVICE_UNAVAILABLE -> messageModel.setMessage("503 Service Unavailable");
                case GATEWAY_TIMEOUT -> messageModel.setMessage("504 Gateway Timeout");
                default -> messageModel.setMessage("");
            }
            errorExtRsModelRecRes.addError(errExtRsModelError);
            return errorExtRsModel;
        } catch (Exception e) {
            LOGGER.error("Error create ErrorResponse");
            return null;
        }
    }

    public <T> T mapperRead(String xml, Class<T> clazz) throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(xml, clazz);
    }

    public <T> String mapperWrite(T xml) throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();
        return mapper.writeValueAsString(xml);
    }

    public String decodeBase(String s) throws UnsupportedEncodingException {
        return new String(Base64.decodeBase64(s), StandardCharsets.UTF_8);
    }

    public String encodeBase(String s) {
        return Base64.encodeBase64String(s.getBytes(StandardCharsets.UTF_8));
    }

    public String createUUID() {
        return "uuid_" + UUID.randomUUID();
    }

    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpClient httpClient = HttpClients.createDefault();
        factory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
        return restTemplate;
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.add("Content-Type", "application/xml; charset=utf-8");
        return headers;
    }

    public ApiContext requestContextBuilder(ApiRequest apiRequest, TypeRq typeRequest) {
        DeferredResult<Object> deferredResult = new DeferredResult<>(property.getASYNCRQTIMEOUT());
        Map<String, String> params = apiRequest.getParams();
        deferredResult.onTimeout(() -> {
            LOGGER.error(typeRequest + " | " + HttpStatus.REQUEST_TIMEOUT + (params.get("ref") == null ? "" : (" | ref:" + params.get("ref"))));
            ErrorResponse errorResponse = setErrorRs(HttpStatus.REQUEST_TIMEOUT);
            deferredResult.setErrorResult(errorResponse != null ? errorResponse : ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT));
        });

        return ApiContext.RequestContextBuilder
                .aRequestContext()
                .typeRequest(typeRequest)
                .apiRequest(apiRequest)
                .result(deferredResult)
                .build();
    }

    public Map<String, String> getParamMapApiRequest(ApiContext ctx) {
        ApiRequest apiRequest = ctx.getApiRequest();
        return apiRequest.getParams();
    }

    public String getParamApiRequest(ApiContext ctx, String key) {
        ApiRequest apiRequest = ctx.getApiRequest();
        Map<String, String> paramsMap = apiRequest.getParams();
        return paramsMap.get(key);
    }

    @SuppressWarnings("unchecked")
    private ArrayList<LinkedHashMap<?, ?>> getDocumentListFromApiResponse(ApiResponse apiResponse) {
        LinkedHashMap<?, ?> response = apiResponse.getResponse();
        LinkedHashMap<?, ?> body = (LinkedHashMap<?, ?>) response.get(RsTag.BODY.get());
        if (!(body.get(RsTag.GETDOCUMENTSRS.get()) instanceof LinkedHashMap<?, ?> getDocumentsRs))
            throw new AssertionError();
        if (!(getDocumentsRs.get(RsTag.DOCUMENTS.get()) instanceof LinkedHashMap<?, ?> documents))
            throw new AssertionError();
        LinkedHashMap<?, ?> doc;
        ArrayList<LinkedHashMap<?, ?>> documentList = new ArrayList<>();
        if (documents.get(RsTag.DOCUMENT.get()) instanceof LinkedHashMap<?, ?>) {
            doc = (LinkedHashMap<?, ?>) documents.get(RsTag.DOCUMENT.get());
            if (doc == null) throw new AssertionError();
            documentList.add(doc);
        } else if (documents.get(RsTag.DOCUMENT.get()) instanceof ArrayList<?>) {
            documentList = (ArrayList<LinkedHashMap<?, ?>>) documents.get(RsTag.DOCUMENT.get());
            if (documentList.isEmpty()) throw new AssertionError();
        }
        return documentList;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<LinkedHashMap<?, ?>> getResultListFromApiResponse(ApiResponse apiResponse) {
        LinkedHashMap<?, ?> response = apiResponse.getResponse();
        LinkedHashMap<?, ?> body = (LinkedHashMap<?, ?>) response.get(RsTag.BODY.get());
        if (!(body.get(RsTag.PUTDOCUMENTSRS.get()) instanceof LinkedHashMap<?, ?> putDocumentsRs))
            throw new AssertionError();
        if (!(putDocumentsRs.get(RsTag.RECEPTIONRESULTS.get()) instanceof LinkedHashMap<?, ?> receptionResults))
            throw new AssertionError();
        LinkedHashMap<?, ?> recRes;
        ArrayList<LinkedHashMap<?, ?>> resultList = new ArrayList<>();
        if (receptionResults.get(RsTag.RECEPTIONRESULT.get()) instanceof LinkedHashMap<?, ?>) {
            recRes = (LinkedHashMap<?, ?>) receptionResults.get(RsTag.RECEPTIONRESULT.get());
            if (recRes == null) throw new AssertionError();
            resultList.add(recRes);
        } else if (receptionResults.get(RsTag.RECEPTIONRESULT.get()) instanceof ArrayList<?>) {
            resultList = (ArrayList<LinkedHashMap<?, ?>>) receptionResults.get(RsTag.RECEPTIONRESULT.get());
            if (resultList.isEmpty()) throw new AssertionError();
        }
        return resultList;
    }

    private LinkedHashMap<?, ?> getBinaryDocument(LinkedHashMap<?, ?> document) {
        LinkedHashMap<?, ?> binaryDocument0 = null;
        if (document.get(RsTag.BINARYDOCUMENT.get()) instanceof LinkedHashMap<?, ?>) {
            binaryDocument0 = (LinkedHashMap<?, ?>) document.get(RsTag.BINARYDOCUMENT.get());
        } else if (document.get(RsTag.BINARYDOCUMENT.get()) instanceof ArrayList<?> binaryDocumentList) {
            binaryDocument0 = (LinkedHashMap<?, ?>) binaryDocumentList.get(0);
            // нас интересует первый элемент, содержащий данные по документу
        }
        if (binaryDocument0 == null) throw new AssertionError();
        return binaryDocument0;
    }
}