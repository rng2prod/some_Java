package com.rngproduction.integrationCoreOW.models.errorModels;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.Objects;

public class ErrorExtRsModel_Message {

    @JacksonXmlText
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorExtRsModel_Message message1)) return false;
        return Objects.equals(getMessage(), message1.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }

    @Override
    public String toString() {
        return "ErrorExtRsModel_Message{" +
                "message='" + message + '\'' +
                '}';
    }
}
