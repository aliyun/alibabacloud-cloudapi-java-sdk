package com.aliyuncs.cloudapi.model.v20160714;

import java.util.List;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.cloudapi.transform.v20160714.ListTagResourcesResponseUnmarshaller;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 *
 */
public class ListTagResourcesResponse extends AcsResponse {

    private String requestId;

    private String nextToken;

    private List<TagResource> tagResources;

    public static class TagResource{
        private String tagKey;
        private String tagValue;
        private String resourceId;
        private String resourceType;

        public String getTagKey() {
            return tagKey;
        }

        public void setTagKey(String tagKey) {
            this.tagKey = tagKey;
        }

        public String getTagValue() {
            return tagValue;
        }

        public void setTagValue(String tagValue) {
            this.tagValue = tagValue;
        }

        public String getResourceId() {
            return resourceId;
        }

        public void setResourceId(String resourceId) {
            this.resourceId = resourceId;
        }

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<TagResource> getTagResources() {
        return tagResources;
    }

    public void setTagResources(List<TagResource> tagResources) {
        this.tagResources = tagResources;
    }

    public String getNextToken() {
        return nextToken;
    }

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    @Override
    public AcsResponse getInstance(UnmarshallerContext unmarshallerContext) throws ClientException {
        return ListTagResourcesResponseUnmarshaller.unmarshall(this, unmarshallerContext);
    }
}
