package com.aliyuncs.cloudapi.transform.v20160714;

import java.util.ArrayList;
import java.util.List;

import com.aliyuncs.cloudapi.model.v20160714.ListTagResourcesResponse;
import com.aliyuncs.cloudapi.model.v20160714.ListTagResourcesResponse.TagResource;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 * @Author: wangyu
 * @Description:
 * @Date: Created in 下午5:34 2019/6/24
 */
public class ListTagResourcesResponseUnmarshaller {

    public static ListTagResourcesResponse unmarshall(ListTagResourcesResponse listTagResourcesResponse, UnmarshallerContext context) {
        listTagResourcesResponse.setRequestId(context.stringValue("ListTagResourcesResponse.RequestId"));
        listTagResourcesResponse.setNextToken(context.stringValue("ListTagResourcesResponse.NextToken"));

        List<TagResource> list = new ArrayList<TagResource>();
        for(int i = 0; i < context.lengthValue("ListTagResourcesResponse.TagResources.Length"); i++){
            TagResource tagResource = new TagResource();
            tagResource.setResourceId("ListTagResourcesResponse.TagResources["+ i +"].ResourceId");
            tagResource.setResourceType("ListTagResourcesResponse.TagResources["+ i +"].ResourceType");
            tagResource.setTagKey("ListTagResourcesResponse.TagResources["+ i +"].TagKey");
            tagResource.setTagValue("ListTagResourcesResponse.TagResources["+ i +"].TagValue");
            list.add(tagResource);
        }

        listTagResourcesResponse.setTagResources(list);
        return listTagResourcesResponse;
    }
}
