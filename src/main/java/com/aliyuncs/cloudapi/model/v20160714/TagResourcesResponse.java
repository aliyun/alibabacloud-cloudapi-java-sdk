package com.aliyuncs.cloudapi.model.v20160714;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.cloudapi.transform.v20160714.TagResourcesResponseUnmarshaller;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 * @Author: wangyu
 * @Description:
 * @Date: Created in 下午5:55 2019/6/24
 */
public class TagResourcesResponse extends AcsResponse {

    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public AcsResponse getInstance(UnmarshallerContext unmarshallerContext) throws ClientException {
        return TagResourcesResponseUnmarshaller.unmarshall(this, unmarshallerContext);
    }
}
