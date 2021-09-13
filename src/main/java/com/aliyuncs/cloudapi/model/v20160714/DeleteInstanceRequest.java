package com.aliyuncs.cloudapi.model.v20160714;

import com.aliyuncs.RpcAcsRequest;

/**
 * @Author: wuling
 * @Date: 2020/1/8 下午3:33
 */
public class DeleteInstanceRequest extends RpcAcsRequest<DeleteInstanceResponse> {

    public DeleteInstanceRequest() {
        super("CloudAPI", "2016-07-14", "DeleteInstance", "apigateway");
    }

    private String instanceId;

    private String token;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
        putQueryParameter("InstanceId", instanceId);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        putQueryParameter("Token", token);
    }

    @Override
    public Class<DeleteInstanceResponse> getResponseClass() {
        return DeleteInstanceResponse.class;
    }
}
