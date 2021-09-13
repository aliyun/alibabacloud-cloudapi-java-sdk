package com.aliyuncs.cloudapi.model.v20160714;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.cloudapi.transform.v20160714.CreateInstanceResponseUnmarshaller;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 * @Author: wuling
 * @Date: 2020/1/8 下午3:34
 */
public class CreateInstanceResponse extends AcsResponse {

    private String requestId;

    private String instanceId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public CreateInstanceResponse getInstance(UnmarshallerContext context) {
        return CreateInstanceResponseUnmarshaller.unmarshall(this, context);
    }
}
