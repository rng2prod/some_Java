package com.rngproduction.ctReqService.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "CTList")
public class CTList {

    @JacksonXmlProperty(localName = "transfer")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CT> ctList = new ArrayList<>();

    public List<CT> getCTList() {
        return ctList;
    }
}
