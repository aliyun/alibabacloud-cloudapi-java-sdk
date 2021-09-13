package com.aliyuncs.cloudapi.model.v20160714;

import com.aliyuncs.RpcAcsRequest;

/**
 * @Author: wuling
 * @Date: 2020/1/8 下午3:33
 */
public class CreateInstanceRequest extends RpcAcsRequest<CreateInstanceResponse> {

    public CreateInstanceRequest() {
        super("CloudAPI", "2016-07-14", "CreateInstance", "apigateway");
    }

    private String instanceName;

    private String chargeType;

    private String instanceSpec;

    private String zoneId;

    private String httpsPolicy;

    private Integer duration;

    private String pricingCycle;

    private Boolean autoPay;

    private String token;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
        putQueryParameter("InstanceName", instanceName);
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String ChargeType) {
        this.chargeType = chargeType;
        putQueryParameter("ChargeType", ChargeType);
    }

    public String getInstanceSpec() {
        return instanceSpec;
    }

    public void setInstanceSpec(String instanceSpec) {
        this.instanceSpec = instanceSpec;
        putQueryParameter("InstanceSpec", instanceSpec);
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
        putQueryParameter("ZoneId", zoneId);
    }

    public String getHttpsPolicy() {
        return httpsPolicy;
    }

    public void setHttpsPolicy(String httpsPolicy) {
        this.httpsPolicy = httpsPolicy;
        putQueryParameter("HttpsPolicy", httpsPolicy);
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
        putQueryParameter("Duration", duration);
    }

    public String getPricingCycle() {
        return pricingCycle;
    }

    public void setPricingCycle(String pricingCycle) {
        this.pricingCycle = pricingCycle;
        putQueryParameter("PricingCycle", pricingCycle);
    }

    public Boolean getAutoPay() {
        return autoPay;
    }

    public void setAutoPay(Boolean autoPay) {
        this.autoPay = autoPay;
        putQueryParameter("AutoPay", autoPay);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        putQueryParameter("Token", token);
    }

    @Override
    public Class<CreateInstanceResponse> getResponseClass() {
        return CreateInstanceResponse.class;
    }
}
