package com.rngproduction.integrationCoreOW.ctx;

import com.rngproduction.integrationCoreOW.configuration.TypeRq;
import com.rngproduction.integrationCoreOW.models.apiModels.requests.ApiRequest;
import com.rngproduction.integrationCoreOW.models.apiModels.responses.ApiResponse;
import com.rngproduction.integrationCoreOW.models.errorModels.ErrorResponse;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExtRequest;
import com.rngproduction.integrationCoreOW.models.extModels.responses.ExtResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Objects;

/**
 * @author e.karyagin
 */

@Component
@Scope("prototype")
public class ApiContext {

    private ApiRequest apiRequest;
    /**
     * запрос из внешнией системы к нашей api
     */

    private ApiResponse apiResponse;
    /**
     * ответ из внешней системы к нашей api
     */

    private ExtRequest extRequest;
    /**
     * запрос из нашего сервиса к другим системам
     */

    private ExtResponse extResponse;
    /**
     * ответ из нашего сервиса в другие системы
     */

    private ErrorResponse errorResponse;
    /**
     * ответ с ошибками
     */

    private String url;
    /**
     * URL для запросов
     */

    private TypeRq typeRequest;
    /**
     * тип запроса
     */

    private DeferredResult<Object> deferredResult;

    /**
     * результат обработки запроса
     */


    public ApiRequest getApiRequest() {
        return apiRequest;
    }

    public void setApiRequest(ApiRequest apiRequest) {
        this.apiRequest = apiRequest;
    }

    public ApiResponse getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    public ExtRequest getExtRequest() {
        return extRequest;
    }

    public void setExtRequest(ExtRequest extRequest) {
        this.extRequest = extRequest;
    }

    public ExtResponse getExtResponse() {
        return extResponse;
    }

    public void setExtResponse(ExtResponse extResponse) {
        this.extResponse = extResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TypeRq getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(TypeRq typeRequest) {
        this.typeRequest = typeRequest;
    }

    public DeferredResult<Object> getDeferredResult() {
        return deferredResult;
    }

    public void setDeferredResult(DeferredResult<Object> deferredResult) {
        this.deferredResult = deferredResult;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiContext that)) return false;
        return Objects.equals(getApiRequest(), that.getApiRequest()) && Objects.equals(getApiResponse(), that.getApiResponse()) && Objects.equals(getExtRequest(), that.getExtRequest()) && Objects.equals(getExtResponse(), that.getExtResponse()) && Objects.equals(getErrorResponse(), that.getErrorResponse()) && Objects.equals(getUrl(), that.getUrl()) && getTypeRequest() == that.getTypeRequest() && Objects.equals(getDeferredResult(), that.getDeferredResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApiRequest(), getApiResponse(), getExtRequest(), getExtResponse(), getErrorResponse(), getUrl(), getTypeRequest(), getDeferredResult());
    }

    @Override
    public String toString() {
        return "ApiContext{" +
                "apiRequest=" + apiRequest +
                ", apiResponse=" + apiResponse +
                ", extRequest=" + extRequest +
                ", extResponse=" + extResponse +
                ", errorResponse=" + errorResponse +
                ", url='" + url + '\'' +
                ", typeRequest=" + typeRequest +
                ", deferredResult=" + deferredResult +
                '}';
    }


    public static final class RequestContextBuilder {
        private ApiRequest apiRequest;
        private ApiResponse apiResponse;
        private ExtRequest extRequest;
        private ExtResponse extResponse;
        private TypeRq typeRequest;
        private DeferredResult<Object> result;

        private RequestContextBuilder() {
        }

        public static RequestContextBuilder aRequestContext() {
            return new RequestContextBuilder();
        }

        public RequestContextBuilder apiRequest(ApiRequest apiRequest) {
            this.apiRequest = apiRequest;
            return this;
        }

        public RequestContextBuilder apiResponse(ApiResponse apiResponse) {
            this.apiResponse = apiResponse;
            return this;
        }

        public RequestContextBuilder extRequest(ExtRequest extRequest) {
            this.extRequest = extRequest;
            return this;
        }

        public RequestContextBuilder extResponse(ExtResponse extResponse) {
            this.extResponse = extResponse;
            return this;
        }

        public RequestContextBuilder typeRequest(TypeRq typeRequest) {
            this.typeRequest = typeRequest;
            return this;
        }

        public RequestContextBuilder result(DeferredResult<Object> result) {
            this.result = result;
            return this;
        }

        public ApiContext build() {
            ApiContext apiRequestContext = new ApiContext();
            apiRequestContext.setApiRequest(apiRequest);
            apiRequestContext.setApiResponse(apiResponse);
            apiRequestContext.setExtRequest(extRequest);
            apiRequestContext.setExtResponse(extResponse);
            apiRequestContext.setTypeRequest(typeRequest);
            apiRequestContext.setDeferredResult(result);
            return apiRequestContext;
        }
    }
}