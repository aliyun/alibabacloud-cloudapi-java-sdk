package com.aliyuncs.cloudapi.model.v20160714;

import java.util.List;

import com.aliyuncs.RpcAcsRequest;

/**
 * @Author: wangyu
 * @Description:
 * @Date: Created in 下午4:23 2019/6/24
 */
public class ListTagResourcesRequest extends RpcAcsRequest<ListTagResourcesResponse> {

    public ListTagResourcesRequest() {
        super("CloudAPI", "2016-07-14", "ListTagResources","apigateway");
    }

    private List<String> resourceIds;

    private String resourceType;

    private List<Tag> tags;

    private String nextToken;

    private String scope;

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

    public String getNextToken() {
        return nextToken;
    }

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
        putQueryParameter("NextToken", nextToken);
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        putQueryParameter("Scope", scope);
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

    @Override
    public Class<ListTagResourcesResponse> getResponseClass() {
        return ListTagResourcesResponse.class;
    }
}
