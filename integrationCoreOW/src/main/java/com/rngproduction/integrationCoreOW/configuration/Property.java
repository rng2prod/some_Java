package com.rngproduction.integrationCoreOW.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author e.karyagin
 */

@Configuration
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class Property {

    @Value("${ASYNCRQTIMEOUT}")
    private Long ASYNCRQTIMEOUT;
    @Value("${TASKSCHEDULERDURATION}")
    private long taskSchedulerDuration;
    @Value("${TASKSCHEDULERINSTANT}")
    private long taskSchedulerInstant;
    @Value("${USEOWEMULATE}")
    private Boolean useEmulate;
    @Value("${OWURL}")
    private String owURL;
    @Value("${OWEMULATEURL}")
    private String owEmulateURL;
    @Value("${STATECORRECT}")
    private String stateCorrect;
    @Value("${STATECORRECTPUTRS}")
    private String stateCorrectPutRS;

    private final String EMULATE_PAYDOCACCEPT_GETDOCUMENTSRS;
    private final String EMULATE_PAYDOCACCEPT_PUTDOCUMENTSRS;
    private final String EMULATE_PAYDOCINFO_GETDOCUMENTSRS;
    private final String EMULATE_PAYDOCINFO_PUTDOCUMENTSRS;
    private final String EMULATE_ACCEPTPAY_GETDOCUMENTSRS;
    private final String EMULATE_ACCEPTPAY_PUTDOCUMENTSRS;
    private final String EMULATE_EXECPAY_GETDOCUMENTSRS;
    private final String EMULATE_EXECPAY_PUTDOCUMENTSRS;

    public Property() {
        EMULATE_PAYDOCACCEPT_GETDOCUMENTSRS = "/owEmulate/PayDocAccept/GetDocumentsRs";
        EMULATE_PAYDOCACCEPT_PUTDOCUMENTSRS = "/owEmulate/PayDocAccept/PutDocumentsRs";
        EMULATE_PAYDOCINFO_GETDOCUMENTSRS = "/owEmulate/PayDocInfo/GetDocumentsRs";
        EMULATE_PAYDOCINFO_PUTDOCUMENTSRS = "/owEmulate/PayDocInfo/PutDocumentsRs";
        EMULATE_ACCEPTPAY_GETDOCUMENTSRS = "/owEmulate/AcceptPay/GetDocumentsRs";
        EMULATE_ACCEPTPAY_PUTDOCUMENTSRS = "/owEmulate/AcceptPay/PutDocumentsRs";
        EMULATE_EXECPAY_GETDOCUMENTSRS = "/owEmulate/ExecPay/GetDocumentsRs";
        EMULATE_EXECPAY_PUTDOCUMENTSRS = "/owEmulate/ExecPay/PutDocumentsRs";
    }

    public Long getASYNCRQTIMEOUT() {
        return ASYNCRQTIMEOUT;
    }

    public long getTaskSchedulerDuration() {
        return taskSchedulerDuration;
    }

    public long getTaskSchedulerInstant() {
        return taskSchedulerInstant;
    }

    public Boolean getUseEmulate() {
        return useEmulate;
    }

    public String getOwURL() {
        return owURL;
    }

    public String getOwEmulateURL() {
        return owEmulateURL;
    }

    public String getStateCorrect() {
        return stateCorrect;
    }

    public String getStateCorrectPutRS() {
        return stateCorrectPutRS;
    }

    public String getEMULATE_PAYDOCACCEPT_GETDOCUMENTSRS() {
        return EMULATE_PAYDOCACCEPT_GETDOCUMENTSRS;
    }

    public String getEMULATE_PAYDOCACCEPT_PUTDOCUMENTSRS() {
        return EMULATE_PAYDOCACCEPT_PUTDOCUMENTSRS;
    }

    public String getEMULATE_PAYDOCINFO_GETDOCUMENTSRS() {
        return EMULATE_PAYDOCINFO_GETDOCUMENTSRS;
    }

    public String getEMULATE_PAYDOCINFO_PUTDOCUMENTSRS() {
        return EMULATE_PAYDOCINFO_PUTDOCUMENTSRS;
    }

    public String getEMULATE_ACCEPTPAY_GETDOCUMENTSRS() {
        return EMULATE_ACCEPTPAY_GETDOCUMENTSRS;
    }

    public String getEMULATE_ACCEPTPAY_PUTDOCUMENTSRS() {
        return EMULATE_ACCEPTPAY_PUTDOCUMENTSRS;
    }

    public String getEMULATE_EXECPAY_GETDOCUMENTSRS() {
        return EMULATE_EXECPAY_GETDOCUMENTSRS;
    }

    public String getEMULATE_EXECPAY_PUTDOCUMENTSRS() {
        return EMULATE_EXECPAY_PUTDOCUMENTSRS;
    }
}
