package com.aliyuncs.cloudapi.model.v20160714;

import java.util.List;

import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.cloudapi.openapi.sdk.entity.Tag;

/**
 * @author auto create
 */
public class DescribePluginsRequest extends RpcAcsRequest<DescribePluginsResponse> {

    public DescribePluginsRequest() {
        super("CloudAPI", "2016-07-14", "DescribePlugins", "apigateway");
    }

    private String pluginId;
    private String pluginType;
    private String pluginName;
    private Integer pageNumber;
    private Integer pageSize;
    private List<Tag> tags;

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

    public String getPluginId() {
        return pluginId;
    }

    public void setPluginId(String pluginId) {
        this.pluginId = pluginId;
        putQueryParameter("PluginId",pluginId);
    }

    public String getPluginType() {
        return pluginType;
    }

    public void setPluginType(String pluginType) {
        this.pluginType = pluginType;
        putQueryParameter("PluginType",pluginType);
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
        putQueryParameter("PluginName",pluginName);
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        putQueryParameter("PageNumber",pageNumber);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        putQueryParameter("PageSize",pageSize);
    }

    @Override
    public Class<DescribePluginsResponse> getResponseClass() {
        return DescribePluginsResponse.class;
    }
}
