package com.aliyuncs.cloudapi.model.v20160714;

import com.aliyuncs.RpcAcsRequest;

/**
 * @author auto create
 * @version
 */
public class ResetAppCodeRequest extends RpcAcsRequest<ResetAppCodeResponse> {

    public ResetAppCodeRequest() {
        super("CloudAPI", "2016-07-14", "ResetAppCode", "apigateway");
    }

    private String appCode;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
        putQueryParameter("AppCode", appCode);
    }

    @Override
    public Class<ResetAppCodeResponse> getResponseClass() {
        return ResetAppCodeResponse.class;
    }
}
