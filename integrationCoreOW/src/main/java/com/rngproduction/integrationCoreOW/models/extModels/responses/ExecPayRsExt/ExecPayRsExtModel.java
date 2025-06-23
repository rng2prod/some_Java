package com.rngproduction.integrationCoreOW.models.extModels.responses.ExecPayRsExt;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.rngproduction.integrationCoreOW.models.extModels.responses.ExtResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JacksonXmlRootElement(localName = "/* hidden string */")
public class ExecPayRsExtModel implements ExtResponse {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "/* hidden string */")
    private List<ExecPayRsExtModel_Document> documents = new ArrayList<>();

    public void addDocument(ExecPayRsExtModel_Document doc) {
        documents.add(doc);
    }

    public List<ExecPayRsExtModel_Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ExecPayRsExtModel_Document> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExecPayRsExtModel that)) return false;
        return Objects.equals(getDocuments(), that.getDocuments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocuments());
    }

    @Override
    public String toString() {
        return "ExecPayRsExtModel{" +
                "documents=" + documents +
                '}';
    }
}
