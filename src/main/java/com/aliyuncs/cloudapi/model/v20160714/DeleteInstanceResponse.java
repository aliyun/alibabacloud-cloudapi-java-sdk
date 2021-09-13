package com.aliyuncs.cloudapi.model.v20160714;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.cloudapi.transform.v20160714.DeleteInstanceResponseUnmarshaller;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 * @Author: wuling
 * @Date: 2020/1/8 下午3:34
 */
public class DeleteInstanceResponse extends AcsResponse {

    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public DeleteInstanceResponse getInstance(UnmarshallerContext context) {
        return DeleteInstanceResponseUnmarshaller.unmarshall(this, context);
    }
}
