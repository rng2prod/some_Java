package com.rngproduction.integrationCoreOW.models.extModels.responses.PayDocAcceptRsPutExt;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.rngproduction.integrationCoreOW.models.extModels.responses.ExtResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JacksonXmlRootElement(localName = "/* hidden string */")
public class PayDocAcceptRsPutExtModel implements ExtResponse {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "/* hidden string */")
    private List<PayDocAcceptRsPutExtModel_ReceptionResult> receptionResults = new ArrayList<>();

    public void addReceptionResult(PayDocAcceptRsPutExtModel_ReceptionResult res) {
        receptionResults.add(res);
    }

    public List<PayDocAcceptRsPutExtModel_ReceptionResult> getReceptionResults() {
        return receptionResults;
    }

    public void setReceptionResults(List<PayDocAcceptRsPutExtModel_ReceptionResult> receptionResults) {
        this.receptionResults = receptionResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayDocAcceptRsPutExtModel that)) return false;
        return Objects.equals(getReceptionResults(), that.getReceptionResults());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReceptionResults());
    }

    @Override
    public String toString() {
        return "PayDocAcceptRsPutExtModel{" +
                "receptionResults=" + receptionResults +
                '}';
    }
}
