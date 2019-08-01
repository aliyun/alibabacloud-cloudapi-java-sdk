package com.aliyuncs.cloudapi.model.v20160714;

import java.util.List;

import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.cloudapi.openapi.sdk.entity.Tag;

/**
 * @author auto create
 *
 */
public class CreatePluginRequest extends RpcAcsRequest<CreatePluginResponse> {

    public CreatePluginRequest() {
        super("CloudAPI", "2016-07-14", "CreatePlugin", "apigateway");
    }

    private String pluginName;
    private String pluginType;
    private String pluginData;
    private String description;
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

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
        putQueryParameter("PluginName",pluginName);
    }

    public String getPluginType() {
        return pluginType;
    }

    public void setPluginType(String pluginType) {
        this.pluginType = pluginType;
        putQueryParameter("PluginType",pluginType);
    }

    public String getPluginData() {
        return pluginData;
    }

    public void setPluginData(String pluginData) {
        this.pluginData = pluginData;
        putQueryParameter("PluginData",pluginData);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        putQueryParameter("Description",description);
    }

    @Override
    public Class<CreatePluginResponse> getResponseClass() {
        return CreatePluginResponse.class;
    }
}
