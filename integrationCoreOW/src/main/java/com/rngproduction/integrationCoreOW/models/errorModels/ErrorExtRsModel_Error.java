package com.rngproduction.integrationCoreOW.models.errorModels;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;

public class ErrorExtRsModel_Error {

    @JacksonXmlProperty(localName = "/* hidden string */")
    private ErrorExtRsModel_Code code = new ErrorExtRsModel_Code();
    @JacksonXmlProperty(localName = "/* hidden string */")
    private ErrorExtRsModel_Message message = new ErrorExtRsModel_Message();

    public ErrorExtRsModel_Code getCode() {
        return code;
    }

    public void setCode(ErrorExtRsModel_Code code) {
        this.code = code;
    }

    public ErrorExtRsModel_Message getMessage() {
        return message;
    }

    public void setMessage(ErrorExtRsModel_Message message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorExtRsModel_Error that)) return false;
        return Objects.equals(getCode(), that.getCode()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage());
    }

    @Override
    public String toString() {
        return "ErrorExtRsModel_Error{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }
}