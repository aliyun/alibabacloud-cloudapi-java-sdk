package com.aliyuncs.cloudapi.transform.v20160714;

import com.aliyuncs.cloudapi.model.v20160714.ResetAppCodeResponse;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 */
public class ResetAppCodeResponseUnmarshaller {

    public static ResetAppCodeResponse unmarshall(ResetAppCodeResponse resetAppCodeResponse, UnmarshallerContext context){
        resetAppCodeResponse.setRequestId(context.stringValue("ResetAppCodeResponse.RequestId"));
        return resetAppCodeResponse;
    }
}
