package com.aliyuncs.cloudapi.model.v20160714;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.cloudapi.transform.v20160714.SetGroupAuthAppCodeResponseUnmarshaller;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 */
public class SetGroupAuthAppCodeResponse extends AcsResponse {

    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public AcsResponse getInstance(UnmarshallerContext context) throws ClientException{
        return SetGroupAuthAppCodeResponseUnmarshaller.unmarshall(this, context);
    }
}
