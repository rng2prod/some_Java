package com.rngproduction.integrationCoreOW.models.extModels.responses.PayDocAcceptRsExt;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.rngproduction.integrationCoreOW.models.extModels.responses.ExtResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JacksonXmlRootElement(localName = "/* hidden string */")
public class PayDocAcceptRsExtModel implements ExtResponse {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "/* hidden string */")
    private List<PayDocAcceptRsExtModel_Document> documents = new ArrayList<>();


    public void addDocument(PayDocAcceptRsExtModel_Document doc) {
        documents.add(doc);
    }

    public List<PayDocAcceptRsExtModel_Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<PayDocAcceptRsExtModel_Document> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayDocAcceptRsExtModel that)) return false;
        return Objects.equals(getDocuments(), that.getDocuments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocuments());
    }

    @Override
    public String toString() {
        return "PayDocAcceptRsExtModel{" +
                "documents=" + documents +
                '}';
    }
}