package com.rngproduction.integrationCoreOW.models.extModels.requests.PayDocInfoRqExt;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExtRequest;

import java.util.Objects;

@JacksonXmlRootElement(localName = "/* hidden string */")
public class PayDocInfoRqExtModel implements ExtRequest {

    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String soapenc = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String soapenv = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String xsd = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String xsi = "/* hidden string */";

    @JacksonXmlProperty(localName = "/* hidden string */")
    private PayDocInfoRqExtModel_Header header = new PayDocInfoRqExtModel_Header();
    @JacksonXmlProperty(localName = "/* hidden string */")
    private PayDocInfoRqExtModel_Body body = new PayDocInfoRqExtModel_Body();

    public String getSoapenc() {
        return soapenc;
    }

    public String getSoapenv() {
        return soapenv;
    }

    public String getXsd() {
        return xsd;
    }

    public String getXsi() {
        return xsi;
    }

    public PayDocInfoRqExtModel_Header getHeader() {
        return header;
    }

    public void setHeader(PayDocInfoRqExtModel_Header header) {
        this.header = header;
    }

    public PayDocInfoRqExtModel_Body getBody() {
        return body;
    }

    public void setBody(PayDocInfoRqExtModel_Body body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayDocInfoRqExtModel that)) return false;
        return Objects.equals(getSoapenc(), that.getSoapenc()) && Objects.equals(getSoapenv(), that.getSoapenv()) && Objects.equals(getXsd(), that.getXsd()) && Objects.equals(getXsi(), that.getXsi()) && Objects.equals(getHeader(), that.getHeader()) && Objects.equals(getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSoapenc(), getSoapenv(), getXsd(), getXsi(), getHeader(), getBody());
    }

    @Override
    public String toString() {
        return "PayDocInfoRqExtModel{" +
                "soapenc='" + soapenc + '\'' +
                ", soapenv='" + soapenv + '\'' +
                ", xsd='" + xsd + '\'' +
                ", xsi='" + xsi + '\'' +
                ", header=" + header +
                ", body=" + body +
                '}';
    }
}
