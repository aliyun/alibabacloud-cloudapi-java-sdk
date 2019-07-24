package com.aliyuncs.cloudapi.transform.v20160714;

import com.aliyuncs.cloudapi.model.v20160714.UntagResourcesResponse;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 * @Author: wangyu
 * @Description:
 * @Date: Created in 下午7:11 2019/6/24
 */
public class UntagResourcesResponseUnmarshaller {

    public static UntagResourcesResponse unmarshall(UntagResourcesResponse UntagResourcesResponse, UnmarshallerContext context) {
        UntagResourcesResponse.setRequestId(context.stringValue("UntagResourcesSystemTagsResponse.RequestId"));
        return UntagResourcesResponse;
    }
}
