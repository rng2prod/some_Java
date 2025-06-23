package com.rngproduction.integrationCoreOW.models.errorModels;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.Objects;

public class ErrorExtRsModel_Code {

    @JacksonXmlText
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorExtRsModel_Code that)) return false;
        return Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }

    @Override
    public String toString() {
        return "ErrorExtRsModel_Code{" +
                "code='" + code + '\'' +
                '}';
    }
}
