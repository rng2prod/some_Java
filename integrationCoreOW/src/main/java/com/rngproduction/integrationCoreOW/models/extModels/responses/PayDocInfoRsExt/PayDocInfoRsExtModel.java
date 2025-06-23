package com.rngproduction.integrationCoreOW.models.extModels.responses.PayDocInfoRsExt;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.rngproduction.integrationCoreOW.models.extModels.responses.ExtResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JacksonXmlRootElement(localName = "/* hidden string */")
public class PayDocInfoRsExtModel implements ExtResponse {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "/* hidden string */")
    private List<PayDocInfoRsExtModel_Document> documents = new ArrayList<>();

    public void addDocument(PayDocInfoRsExtModel_Document doc) {
        documents.add(doc);
    }

    public List<PayDocInfoRsExtModel_Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<PayDocInfoRsExtModel_Document> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayDocInfoRsExtModel that)) return false;
        return Objects.equals(getDocuments(), that.getDocuments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocuments());
    }

    @Override
    public String toString() {
        return "PayDocInfoRsExtModel{" +
                "documents=" + documents +
                '}';
    }
}
