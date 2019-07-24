package com.aliyuncs.cloudapi.model.v20160714;

import com.aliyuncs.RpcAcsRequest;

/**
 */
public class SetGroupAuthAppCodeRequest extends RpcAcsRequest<SetGroupAuthAppCodeResponse> {

    public SetGroupAuthAppCodeRequest() {
        super("CloudAPI", "2016-07-14", "SetGroupAuthAppCode", "apigateway");
    }

    private String groupId;

    private String authAppCode;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
        putQueryParameter("GroupId", groupId);
    }

    public String getAuthAppCode() {
        return authAppCode;
    }

    public void setAuthAppCode(String authAppCode) {
        this.authAppCode = authAppCode;
        putQueryParameter("AuthAppCode", authAppCode);
    }

    @Override
    public Class<SetGroupAuthAppCodeResponse> getResponseClass() {
        return SetGroupAuthAppCodeResponse.class;
    }
}
