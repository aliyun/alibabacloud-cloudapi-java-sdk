package com.aliyuncs.cloudapi.transform.v20160714;

import com.aliyuncs.cloudapi.model.v20160714.TagResourcesResponse;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 * @Author: wangyu
 * @Description:
 * @Date: Created in 下午5:56 2019/6/24
 */
public class TagResourcesResponseUnmarshaller {

    public static TagResourcesResponse unmarshall(TagResourcesResponse response, UnmarshallerContext context) {
        response.setRequestId(context.stringValue("TagResorucesResponse.RequestId"));
        return response;
    }
}
