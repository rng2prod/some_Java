package com.rngproduction.ctReqService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:system.properties", encoding = "UTF-8")
public class Property {

    @Value("${urlAPI}")
    private String urlCT;

    @Value("${requestCTService}")
    private String requestCTService;

    @Value("${TIMEOUTCONN}")
    private Long TIMEOUTCONN;

    @Value("${TIMEOUTREQUEST}")
    private Long TIMEOUTREQUEST;

    @Value("${fileCatalogPath}")
    private String fileCatalogPath;

    @Value("${MailHost}")
    private String MailHost;

    @Value("${MailPort}")
    private String MailPort;

    @Value("${MailReceivers}")
    private String MailReceivers;

    @Value("${MailSender}")
    private String MailSender;

    @Value("${MailSubject}")
    private String MailSubject;

    @Value("${authMailServer}")
    private String authMailServer;

    @Value("${authUserName}")
    private String username;

    @Value("${authUserPass}")
    private String userpass;


    public String getUrlCT() {
        return urlCT;
    }

    public String getRequestCTService() {
        return requestCTService;
    }

    public Long getTimeoutConn() {
        return TIMEOUTCONN;
    }

    public Long getTimeoutRequest() {
        return TIMEOUTREQUEST;
    }

    public String getFileCatalogPath() {
        return fileCatalogPath;
    }

    public String getMailHost() {
        return MailHost;
    }

    public String getMailPort() {
        return MailPort;
    }

    public String getMailReceivers() {
        return MailReceivers;
    }

    public String getMailSender() {
        return MailSender;
    }

    public String getMailSubject() {
        return MailSubject;
    }

    public boolean authMailServer() {
        return authMailServer.equals("true");
    }

    public String getUsername() {
        return username;
    }

    public String getUserpass() {
        return userpass;
    }
}
