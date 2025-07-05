package com.rngproduction.ctReqService.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "CTNOTE",
        indexes = {
                @Index(name = "XTS_CREATETIME", columnList = "SYSCREATETIME")
        })

@JacksonXmlRootElement(localName = "transfer")
public class CT {

    @Id
    @Column(name = "ID")
    @JacksonXmlProperty(localName = "id")
    private String id;

    @Column(name = "SYSCREATETIME")
    @JacksonXmlProperty(localName = "sysCreateTime")
    private Date sysCreateTime;

    @Column(name = "AMOUNTTRANS")
    @JacksonXmlProperty(localName = "amountTrans")
    private String amountTrans;

    @Column(name = "INSTACCOUNT")
    @JacksonXmlProperty(localName = "instAccount")
    private String instAccount;

    @Column(name = "INSTADDRESS")
    @JacksonXmlProperty(localName = "instAddress")
    private String instAddress;

    @Column(name = "INSTBANKCOUNTRYCODE")
    @JacksonXmlProperty(localName = "instBankCountryCode")
    private String instBankCountryCode;

    @Column(name = "INSTBANKNAME")
    @JacksonXmlProperty(localName = "instBankName")
    private String instBankName;

    @Column(name = "INSTBANKSWIFT")
    @JacksonXmlProperty(localName = "instBankSwift")
    private String instBankSwift;

    @Column(name = "INSTCOUNTRYCODE")
    @JacksonXmlProperty(localName = "instCountryCode")
    private String instCountryCode;

    @Column(name = "INSTNAME")
    @JacksonXmlProperty(localName = "instName")
    private String instName;

    @Column(name = "INSTPLACE")
    @JacksonXmlProperty(localName = "instPlace")
    private String instPlace;

    @Column(name = "CHARGESTYPE")
    @JacksonXmlProperty(localName = "chargesType")
    private String chargesType;

    @Column(name = "CTCODE")
    @JacksonXmlProperty(localName = "ctCode")
    private String ctCode;

    @Column(name = "CTISOCODE")
    @JacksonXmlProperty(localName = "ctIsoCode")
    private String ctIsoCode;

    @Column(name = "IMEDIAINFO")
    @JacksonXmlProperty(localName = "imediaInfo")
    private String imediaInfo;

    @Column(name = "IMEDIABANKSWIFT")
    @JacksonXmlProperty(localName = "imediaBankSwift")
    private String imediaBankSwift;

    @Column(name = "ORGID")
    @JacksonXmlProperty(localName = "orgId")
    private String orgId;

    @Column(name = "PAYUNTILDATE")
    @JacksonXmlProperty(localName = "payUntilDate")
    private Date payUntilDate;

    @Column(name = "PAYERACCOUNT")
    @JacksonXmlProperty(localName = "payerAccount")
    private String payerAccount;

    @Column(name = "PAYERADDRESS")
    @JacksonXmlProperty(localName = "payerAddress")
    private String payerAddress;

    @Column(name = "PAYERCOUNTRYCODE")
    @JacksonXmlProperty(localName = "payerCountryCode")
    private String payerCountryCode;

    @Column(name = "PAYERINN")
    @JacksonXmlProperty(localName = "payerInn")
    private String payerInn;

    @Column(name = "PAYERNAME")
    @JacksonXmlProperty(localName = "payerName")
    private String payerName;

    @Column(name = "PAYERNAMEINT")
    @JacksonXmlProperty(localName = "payerNameInt")
    private String payerNameInt;

    @Column(name = "PAYERPLACE")
    @JacksonXmlProperty(localName = "payerPlace")
    private String payerPlace;

    @Column(name = "PAYMENTDETAILS")
    @JacksonXmlProperty(localName = "paymentDetails")
    private String paymentDetails;

    @Column(name = "NEEDSEND")
    private Byte needSend = 1;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(Date sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    public String getAmountTrans() {
        return amountTrans;
    }

    public void setAmountTrans(String amountTrans) {
        this.amountTrans = amountTrans;
    }

    public String getInstAccount() {
        return instAccount;
    }

    public void setInstAccount(String instAccount) {
        this.instAccount = instAccount;
    }

    public String getInstAddress() {
        return instAddress;
    }

    public void setInstAddress(String instAddress) {
        this.instAddress = instAddress;
    }

    public String getInstBankCountryCode() {
        return instBankCountryCode;
    }

    public void setInstBankCountryCode(String instBankCountryCode) {
        this.instBankCountryCode = instBankCountryCode;
    }

    public String getInstBankName() {
        return instBankName;
    }

    public void setInstBankName(String instBankName) {
        this.instBankName = instBankName;
    }

    public String getInstBankSwift() {
        return instBankSwift;
    }

    public void setInstBankSwift(String instBankSwift) {
        this.instBankSwift = instBankSwift;
    }

    public String getInstCountryCode() {
        return instCountryCode;
    }

    public void setInstCountryCode(String instCountryCode) {
        this.instCountryCode = instCountryCode;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getInstPlace() {
        return instPlace;
    }

    public void setInstPlace(String instPlace) {
        this.instPlace = instPlace;
    }

    public String getChargesType() {
        return chargesType;
    }

    public void setChargesType(String chargesType) {
        this.chargesType = chargesType;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getCtIsoCode() {
        return ctIsoCode;
    }

    public void setCtIsoCode(String ctIsoCode) {
        this.ctIsoCode = ctIsoCode;
    }

    public String getImediaInfo() {
        return imediaInfo;
    }

    public void setImediaInfo(String imediaInfo) {
        this.imediaInfo = imediaInfo;
    }

    public String getImediaBankSwift() {
        return imediaBankSwift;
    }

    public void setImediaBankSwift(String imediaBankSwift) {
        this.imediaBankSwift = imediaBankSwift;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getPayUntilDate() {
        return payUntilDate;
    }

    public void setPayUntilDate(Date payUntilDate) {
        this.payUntilDate = payUntilDate;
    }

    public String getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }

    public String getPayerAddress() {
        return payerAddress;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public String getPayerCountryCode() {
        return payerCountryCode;
    }

    public void setPayerCountryCode(String payerCountryCode) {
        this.payerCountryCode = payerCountryCode;
    }

    public String getPayerInn() {
        return payerInn;
    }

    public void setPayerInn(String payerInn) {
        this.payerInn = payerInn;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerNameInt() {
        return payerNameInt;
    }

    public void setPayerNameInt(String payerNameInt) {
        this.payerNameInt = payerNameInt;
    }

    public String getPayerPlace() {
        return payerPlace;
    }

    public void setPayerPlace(String payerPlace) {
        this.payerPlace = payerPlace;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Byte getNeedSend() {
        return needSend;
    }

    public void setNeedSend(Byte needSend) {
        this.needSend = needSend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CT ct = (CT) o;
        return Objects.equals(id, ct.id) && Objects.equals(sysCreateTime, ct.sysCreateTime) && Objects.equals(amountTrans, ct.amountTrans) && Objects.equals(instAccount, ct.instAccount) && Objects.equals(instAddress, ct.instAddress) && Objects.equals(instBankCountryCode, ct.instBankCountryCode) && Objects.equals(instBankName, ct.instBankName) && Objects.equals(instBankSwift, ct.instBankSwift) && Objects.equals(instCountryCode, ct.instCountryCode) && Objects.equals(instName, ct.instName) && Objects.equals(instPlace, ct.instPlace) && Objects.equals(chargesType, ct.chargesType) && Objects.equals(ctCode, ct.ctCode) && Objects.equals(ctIsoCode, ct.ctIsoCode) && Objects.equals(imediaInfo, ct.imediaInfo) && Objects.equals(imediaBankSwift, ct.imediaBankSwift) && Objects.equals(orgId, ct.orgId) && Objects.equals(payUntilDate, ct.payUntilDate) && Objects.equals(payerAccount, ct.payerAccount) && Objects.equals(payerAddress, ct.payerAddress) && Objects.equals(payerCountryCode, ct.payerCountryCode) && Objects.equals(payerInn, ct.payerInn) && Objects.equals(payerName, ct.payerName) && Objects.equals(payerNameInt, ct.payerNameInt) && Objects.equals(payerPlace, ct.payerPlace) && Objects.equals(paymentDetails, ct.paymentDetails) && Objects.equals(needSend, ct.needSend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sysCreateTime, amountTrans, instAccount, instAddress, instBankCountryCode, instBankName, instBankSwift, instCountryCode, instName, instPlace, chargesType, ctCode, ctIsoCode, imediaInfo, imediaBankSwift, orgId, payUntilDate, payerAccount, payerAddress, payerCountryCode, payerInn, payerName, payerNameInt, payerPlace, paymentDetails, needSend);
    }

    @Override
    public String toString() {
        return "CT{" +
                "id='" + id + '\'' +
                ", sysCreateTime=" + sysCreateTime +
                ", amountTrans='" + amountTrans + '\'' +
                ", instAccount='" + instAccount + '\'' +
                ", instAddress='" + instAddress + '\'' +
                ", instBankCountryCode='" + instBankCountryCode + '\'' +
                ", instBankName='" + instBankName + '\'' +
                ", instBankSwift='" + instBankSwift + '\'' +
                ", instCountryCode='" + instCountryCode + '\'' +
                ", instName='" + instName + '\'' +
                ", instPlace='" + instPlace + '\'' +
                ", chargesType='" + chargesType + '\'' +
                ", ctCode='" + ctCode + '\'' +
                ", ctIsoCode='" + ctIsoCode + '\'' +
                ", imediaInfo='" + imediaInfo + '\'' +
                ", imediaBankSwift='" + imediaBankSwift + '\'' +
                ", orgId='" + orgId + '\'' +
                ", payUntilDate=" + payUntilDate +
                ", payerAccount='" + payerAccount + '\'' +
                ", payerAddress='" + payerAddress + '\'' +
                ", payerCountryCode='" + payerCountryCode + '\'' +
                ", payerInn='" + payerInn + '\'' +
                ", payerName='" + payerName + '\'' +
                ", payerNameInt='" + payerNameInt + '\'' +
                ", payerPlace='" + payerPlace + '\'' +
                ", paymentDetails='" + paymentDetails + '\'' +
                ", needSend=" + needSend +
                '}';
    }
}
