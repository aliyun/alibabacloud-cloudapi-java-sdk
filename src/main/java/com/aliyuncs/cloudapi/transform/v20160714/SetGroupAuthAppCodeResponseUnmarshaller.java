package com.aliyuncs.cloudapi.transform.v20160714;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.cloudapi.model.v20160714.SetGroupAuthAppCodeResponse;
import com.aliyuncs.transform.UnmarshallerContext;
/**
 */
public class SetGroupAuthAppCodeResponseUnmarshaller {

    public static AcsResponse unmarshall(SetGroupAuthAppCodeResponse setGroupAuthAppCodeResponse, UnmarshallerContext context) {
        setGroupAuthAppCodeResponse.setRequestId(context.stringValue("SetGroupAuthAppCode.RequestId"));
        return setGroupAuthAppCodeResponse;
    }
}
