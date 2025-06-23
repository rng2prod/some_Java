package com.rngproduction.integrationCoreOW.models.apiModels.responses.AcceptPayRsPutApi;

import com.rngproduction.integrationCoreOW.models.apiModels.responses.ApiResponse;

import java.util.LinkedHashMap;
import java.util.Objects;

public class AcceptPayRsPutApiModel implements ApiResponse {

    private LinkedHashMap<?, ?> response;

    @Override
    public LinkedHashMap<?, ?> getResponse() {
        return response;
    }

    public void setResponse(LinkedHashMap<?, ?> response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AcceptPayRsPutApiModel that)) return false;
        return Objects.equals(getResponse(), that.getResponse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResponse());
    }

    @Override
    public String toString() {
        return "AcceptPayRsPutApiModel{" +
                "response=" + response +
                '}';
    }
}
