package com.rngproduction.integrationCoreOW.configuration;

public enum RsTag {

    BODY("Body"),
    GETDOCUMENTSRS("GetDocumentsRs"),
    PUTDOCUMENTSRS("PutDocumentsRs"),
    DOCUMENTS("Documents"),
    DOCUMENT("Document"),
    RECEPTIONRESULTS("ReceptionResults"),
    RECEPTIONRESULT("ReceptionResult"),
    BINARYDOCUMENT("BinaryDocument"),
    FAULT("Fault"),
    DETAIL("detail"),
    ERRORS("Errors"),
    ERROR("Error");

    private final String value;

    RsTag(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
