package com.rngproduction.integrationCoreOW.models.extModels.requests.ExecPayRqPutExt;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.rngproduction.integrationCoreOW.models.extModels.requests.ExtRequest;

import java.util.Objects;

@JacksonXmlRootElement(localName = "/* hidden string */")
public class ExecPayRqPutExtModel implements ExtRequest {

    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String soapenv = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String ns = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String ns4 = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String ns5 = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String wsse = "/* hidden string */";
    @JacksonXmlProperty(localName = "/* hidden string */", isAttribute = true)
    private final String wsu = "/* hidden string */";

    @JacksonXmlProperty(localName = "/* hidden string */")
    private ExecPayRqPutExtModel_Header header = new ExecPayRqPutExtModel_Header();
    @JacksonXmlProperty(localName = "/* hidden string */")
    private ExecPayRqPutExtModel_Body body = new ExecPayRqPutExtModel_Body();

    public String getSoapenv() {
        return soapenv;
    }

    public String getNs() {
        return ns;
    }

    public String getNs4() {
        return ns4;
    }

    public String getNs5() {
        return ns5;
    }

    public String getWsse() {
        return wsse;
    }

    public String getWsu() {
        return wsu;
    }

    public ExecPayRqPutExtModel_Header getHeader() {
        return header;
    }

    public void setHeader(ExecPayRqPutExtModel_Header header) {
        this.header = header;
    }

    public ExecPayRqPutExtModel_Body getBody() {
        return body;
    }

    public void setBody(ExecPayRqPutExtModel_Body body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExecPayRqPutExtModel that)) return false;
        return Objects.equals(getSoapenv(), that.getSoapenv()) && Objects.equals(getNs(), that.getNs()) && Objects.equals(getNs4(), that.getNs4()) && Objects.equals(getNs5(), that.getNs5()) && Objects.equals(getWsse(), that.getWsse()) && Objects.equals(getWsu(), that.getWsu()) && Objects.equals(getHeader(), that.getHeader()) && Objects.equals(getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSoapenv(), getNs(), getNs4(), getNs5(), getWsse(), getWsu(), getHeader(), getBody());
    }

    @Override
    public String toString() {
        return "ExecPayRqPutExtModel{" +
                "soapenv='" + soapenv + '\'' +
                ", ns='" + ns + '\'' +
                ", ns4='" + ns4 + '\'' +
                ", ns5='" + ns5 + '\'' +
                ", wsse='" + wsse + '\'' +
                ", wsu='" + wsu + '\'' +
                ", header=" + header +
                ", body=" + body +
                '}';
    }
}
