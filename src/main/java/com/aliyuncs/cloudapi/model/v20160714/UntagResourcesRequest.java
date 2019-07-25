package com.aliyuncs.cloudapi.model.v20160714;

import java.util.List;

import com.aliyuncs.RpcAcsRequest;

/**
 *
 */
public class UntagResourcesRequest extends RpcAcsRequest<UntagResourcesResponse> {

    public UntagResourcesRequest() {
        super("CloudAPI", "2016-07-14", "UntagResources", "apigateway");
    }

    private List<String> resourceIds;
    private String resourceType;
    private List<String> tagKeys;
    private Boolean all;

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

    public List<String> getTagKeys() {
        return tagKeys;
    }

    public void setTagKeys(List<String> tagKeys) {
        this.tagKeys = tagKeys;
        if (tagKeys != null) {
            for (int i = 0; i < tagKeys.size(); i++) {
                putQueryParameter("TagKey." + (i + 1) , tagKeys.get(i));
            }
        }
    }

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
        putQueryParameter("All", all);
    }

    @Override
    public Class<UntagResourcesResponse> getResponseClass() {
        return UntagResourcesResponse.class;
    }
}
