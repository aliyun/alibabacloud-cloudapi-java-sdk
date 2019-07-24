package com.aliyuncs.cloudapi.model.v20160714;

import java.util.List;

import com.aliyuncs.RpcAcsRequest;

/**
 * @Author: wangyu
 * @Description:
 * @Date: Created in 下午5:54 2019/6/24
 */
public class TagResourcesRequest extends RpcAcsRequest<TagResourcesResponse> {

    public TagResourcesRequest() {
        super("CloudAPI", "2016-07-14", "TagResources", "apigateway");
    }

    private List<String> resourceIds;
    private String resourceType;
    private List<Tag> tags;

    public List<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
        if (resourceIds != null) {
            for (int i = 0; i < resourceIds.size(); i++) {
                putQueryParameter("ResourceId." + (i + 1) , resourceIds.get(i));
            }
        }
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
        putQueryParameter("ResourceType", resourceType);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
        if (tags != null) {
            for (int i = 0; i < tags.size(); i++) {
                putQueryParameter("Tag." + (i + 1) + ".Value" , tags.get(i).getValue());
                putQueryParameter("Tag." + (i + 1) + ".Key" , tags.get(i).getKey());
            }
        }
    }

    @Override
    public Class<TagResourcesResponse> getResponseClass() {
        return TagResourcesResponse.class;
    }

    public static class Tag {

        private String value;

        private String key;

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
