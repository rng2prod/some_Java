package com.rngproduction.integrationCoreOW.models.errorModels;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorExtRsModel_ReceptionResult {

    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private String ref;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "/* hidden string */")
    private List<ErrorExtRsModel_Error> errors = new ArrayList<>();

    public void addError(ErrorExtRsModel_Error err) {
        errors.add(err);
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public List<ErrorExtRsModel_Error> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorExtRsModel_Error> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorExtRsModel_ReceptionResult that)) return false;
        return Objects.equals(getRef(), that.getRef()) && Objects.equals(getErrors(), that.getErrors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRef(), getErrors());
    }

    @Override
    public String toString() {
        return "ErrorExtRsModel_ReceptionResult{" +
                "ref='" + ref + '\'' +
                ", errors=" + errors +
                '}';
    }
}