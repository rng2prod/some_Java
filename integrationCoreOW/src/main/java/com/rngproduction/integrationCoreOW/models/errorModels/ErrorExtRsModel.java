package com.rngproduction.integrationCoreOW.models.errorModels;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Objects;

@JacksonXmlRootElement(localName = "/* hidden string */")
public class ErrorExtRsModel implements ErrorResponse {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "/* hidden string */")
    private ErrorExtRsModel_ReceptionResult receptionResult = new ErrorExtRsModel_ReceptionResult();

    public ErrorExtRsModel_ReceptionResult getReceptionResult() {
        return receptionResult;
    }

    public void setReceptionResult(ErrorExtRsModel_ReceptionResult receptionResult) {
        this.receptionResult = receptionResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorExtRsModel that)) return false;
        return Objects.equals(getReceptionResult(), that.getReceptionResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReceptionResult());
    }

    @Override
    public String toString() {
        return "ErrorExtRsModel{" +
                "receptionResult=" + receptionResult +
                '}';
    }
}