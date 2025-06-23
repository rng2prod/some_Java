package com.rngproduction.integrationCoreOW.models.extModels.requests.AcceptPayRqExt;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExtRequest;

import java.util.Objects;

@JacksonXmlRootElement(localName = "/* hidden string */")
public class AcceptPayRqExtModel implements ExtRequest {

    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String soapenc = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String soapenv = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String xsd = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String xsi = "/* hidden string */";

    @JacksonXmlProperty(localName = "/* hidden string */")
    private AcceptPayRqExtModel_Header header = new AcceptPayRqExtModel_Header();
    @JacksonXmlProperty(localName = "/* hidden string */")
    private AcceptPayRqExtModel_Body body = new AcceptPayRqExtModel_Body();

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

    public AcceptPayRqExtModel_Header getHeader() {
        return header;
    }

    public void setHeader(AcceptPayRqExtModel_Header header) {
        this.header = header;
    }

    public AcceptPayRqExtModel_Body getBody() {
        return body;
    }

    public void setBody(AcceptPayRqExtModel_Body body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AcceptPayRqExtModel that)) return false;
        return Objects.equals(getSoapenc(), that.getSoapenc()) && Objects.equals(getSoapenv(), that.getSoapenv()) && Objects.equals(getXsd(), that.getXsd()) && Objects.equals(getXsi(), that.getXsi()) && Objects.equals(getHeader(), that.getHeader()) && Objects.equals(getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSoapenc(), getSoapenv(), getXsd(), getXsi(), getHeader(), getBody());
    }

    @Override
    public String toString() {
        return "AcceptPayRqExtModel{" +
                "soapenc='" + soapenc + '\'' +
                ", soapenv='" + soapenv + '\'' +
                ", xsd='" + xsd + '\'' +
                ", xsi='" + xsi + '\'' +
                ", header=" + header +
                ", body=" + body +
                '}';
    }
}
