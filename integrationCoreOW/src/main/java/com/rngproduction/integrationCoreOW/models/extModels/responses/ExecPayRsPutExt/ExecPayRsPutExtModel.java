package com.rngproduction.integrationCoreOW.models.extModels.responses.ExecPayRsPutExt;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.rngproduction.integrationCoreOW.models.extModels.responses.ExtResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JacksonXmlRootElement(localName = "/* hidden string */")
public class ExecPayRsPutExtModel implements ExtResponse {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "/* hidden string */")
    private List<ExecPayRsPutExtModel_ReceptionResult> receptionResults = new ArrayList<>();

    public void addReceptionResult(ExecPayRsPutExtModel_ReceptionResult res) {
        receptionResults.add(res);
    }

    public List<ExecPayRsPutExtModel_ReceptionResult> getReceptionResults() {
        return receptionResults;
    }

    public void setReceptionResults(List<ExecPayRsPutExtModel_ReceptionResult> receptionResults) {
        this.receptionResults = receptionResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExecPayRsPutExtModel that)) return false;
        return Objects.equals(getReceptionResults(), that.getReceptionResults());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReceptionResults());
    }

    @Override
    public String toString() {
        return "ExecPayRsPutExtModel{" +
                "receptionResults=" + receptionResults +
                '}';
    }
}
