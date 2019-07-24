package com.aliyuncs.cloudapi.openapi.sdk.builder;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.cloudapi.model.v20160714.*;
import com.aliyuncs.cloudapi.openapi.sdk.common.OperationContext;
import com.aliyuncs.cloudapi.openapi.sdk.common.OperationLogger;
import com.aliyuncs.cloudapi.openapi.sdk.common.OperationLoggerFactory;
import com.aliyuncs.cloudapi.openapi.sdk.constant.PluginType;
import com.aliyuncs.exceptions.ClientException;

import java.util.ArrayList;
import java.util.List;

/**
 * 插件管理器
 * @author ailan.gl 2018-12-16
 */
public class PluginBuilder {
	private static OperationLogger LOGGER = OperationLoggerFactory.getLogger("Plugin");

	private IAcsClient client;
	private String pluginId;
	private DescribePluginsResponse.PluginAttribute attribute;

	public PluginBuilder(IAcsClient client) {
		this.client = client;
		this.attribute = new DescribePluginsResponse.PluginAttribute();
	}

	public PluginBuilder(IAcsClient client, String id) {
		this(client);
		this.pluginId = id;
	}

	public PluginBuilder type(PluginType type) {
		this.attribute.setPluginType(type.openapiValue());
		return this;
	}

	public PluginBuilder data(String data) {
		this.attribute.setPluginData(data);
		return this;
	}

	public PluginBuilder name(String name) {
		this.attribute.setPluginName(name);
		return this;
	}

	public PluginBuilder description(String description) {
		this.attribute.setDescription(description);
		return this;
	}

	public DescribePluginsResponse.PluginAttribute getAttribute() {
		return this.attribute;
	}

	public PluginBuilder describe() throws ClientException {
		DescribePluginsRequest request = new DescribePluginsRequest();
		request.setPluginId(pluginId);

		OperationContext c = LOGGER.begin("DescribePlugins", pluginId);
		try {
			DescribePluginsResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "result:" + response.getTotalCount());

			if (response.getTotalCount() != 1) {
				throw new RuntimeException("Unexpected Response Data Count:" + response.getTotalCount());
			}
			this.attribute = response.getPlugins().get(0);
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public PluginBuilder create() throws ClientException {
		CreatePluginRequest request = new CreatePluginRequest();
		request.setDescription("create by integration");
		request.setPluginData(attribute.getPluginData());
		request.setPluginType(attribute.getPluginType());
		request.setPluginName(attribute.getPluginName());
		request.setDescription(attribute.getDescription());

		String msg = String.format("%s[%s] \n%s", attribute.getPluginName(), attribute.getPluginType(), attribute.getPluginData());
		OperationContext c = LOGGER.begin("CreatePlugin", msg);
		try {
			CreatePluginResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok:" + response.getPluginId());
			this.pluginId = response.getPluginId();
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public PluginBuilder modify() throws ClientException {
		ModifyPluginRequest request = new ModifyPluginRequest();
		request.setPluginId(pluginId);
		request.setDescription(attribute.getDescription());
		request.setPluginName(attribute.getPluginName());
		request.setPluginData(attribute.getPluginData());

		String msg = String.format("%s -> %s[%s] \n%s", pluginId, attribute.getPluginName(),
			attribute.getPluginType(), attribute.getPluginData());
		OperationContext c = LOGGER.begin("ModifyPlugin", msg);
		try {
			ModifyPluginResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public PluginBuilder delete() throws ClientException {
		DeletePluginRequest request = new DeletePluginRequest();
		request.setPluginId(pluginId);
		OperationContext c = LOGGER.begin("DeletePlugin", pluginId);
		try {
			DeletePluginResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public PluginBuilder attach(String stageName, String apiId) throws ClientException {
		AttachPluginRequest request = new AttachPluginRequest();
		request.setPluginId(pluginId);
		request.setStageName(stageName);
		request.setApiId(apiId);

		String msg = String.format("%s -> %s/%s", pluginId, apiId, stageName);
		OperationContext c = LOGGER.begin("AttachPlugin", msg);
		try {
			AttachPluginResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public PluginBuilder detach(String stageName, String apiId) throws ClientException {
		DetachPluginRequest request = new DetachPluginRequest();
		request.setApiId(apiId);
		request.setStageName(stageName);
		request.setPluginId(pluginId);

		String msg = String.format("%s -> %s/%s", pluginId, apiId, stageName);
		OperationContext c = LOGGER.begin("DetachPlugin", msg);
		try {
			DetachPluginResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	/**
	 * TODO
	 * @return
	 * @throws ClientException
	 */
	public List<DescribePluginApisResponse.ApiPluginSummary> listAllApis() throws ClientException {
		DescribePluginApisRequest request = new DescribePluginApisRequest();
		request.setPluginId(pluginId);

		int pageNumber = 0;
		List<DescribePluginApisResponse.ApiPluginSummary> result = new ArrayList<DescribePluginApisResponse.ApiPluginSummary>();
		while (true) {
			request.setPageNumber(pageNumber);
			String msg = pluginId + " page:" + pageNumber;
			OperationContext c = LOGGER.begin("DescribePluginApis", msg);
			try {
				DescribePluginApisResponse response = client.getAcsResponse(request);
				c.success(response.getRequestId(), response.getPageNumber() + "/" + response.getTotalCount());
				for (DescribePluginApisResponse.ApiPluginSummary a: response.getApiPluginSummaries()) {
					result.add(a);
				}
				pageNumber++;
				if ((pageNumber * response.getPageSize()) >= response.getTotalCount()) {
					break;

				}
			} catch (ClientException ex) {
				c.failed(ex);
				throw ex;
			}
		}
		return result;
	}

	public static List<PluginBuilder> describePlugins(IAcsClient client, PluginType type, String name)
		throws ClientException {
		DescribePluginsRequest request = new DescribePluginsRequest();
		request.setPluginType(type.openapiValue());
		request.setPluginName(name);

		OperationContext c = LOGGER.begin("DescribePlugin", name + "<" + type.openapiValue() + ">");
		try {
			DescribePluginsResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "count:" + response.getTotalCount());

			List<PluginBuilder> result = new ArrayList<PluginBuilder>();
			for (DescribePluginsResponse.PluginAttribute a: response.getPlugins()) {
				PluginBuilder plugin = new PluginBuilder(client, a.getPluginId());
				plugin.attribute = a;
				result.add(plugin);
			}
			return result;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	//
	///**
	// * 创建插件
	// * @param data
	// * @param type
	// * @param name
	// * @return
	// * @throws ClientException
	// */
	//public String createPlugin(String name, String type, String data) throws ClientException {
	//	DescribePluginsRequest describePluginsRequest = new DescribePluginsRequest();
	//	describePluginsRequest.setPluginName(name);
	//	DescribePluginsResponse response = client.getAcsResponse(describePluginsRequest);
	//	if (response.getTotalCount() > 0) {
	//		return response.getPlugins().get(0).getPluginId();
	//	}
	//
	//
	//}
	//
	//
	///**
	// * 获取plugin
	// * @param name
	// * @return
	// */
	//public DescribePluginsResponse.PluginAttribute getPluginByName(String name) throws ClientException {
	//	DescribePluginsRequest request = new DescribePluginsRequest();
	//	request.setPluginName(name);
	//
	//	DescribePluginsResponse response = client.getAcsResponse(request);
	//	if (response.getTotalCount() == 0) {
	//		return null;
	//	} else if (response.getTotalCount() == 1) {
	//		return response.getPlugins().get(0);
	//	} else {
	//		LOGGER.error("Plugin more than 1 size=" + response.getTotalCount());
	//		return response.getPlugins().get(0);
	//		// throw new RuntimeException("Plugin name is duplicated:" + name);
	//	}
	//}
	//
	///**
	// * listPlugins
	// * @param name
	// * @param pluginType
	// * @param pluginId
	// * @return
	// * @throws ClientException
	// */
	//public Map<String, DescribePluginsResponse.PluginAttribute> listPlugins(String name, String pluginType, String pluginId) throws
	//	ClientException {
	//	Map<String, DescribePluginsResponse.PluginAttribute> plugins = new HashMap<String, DescribePluginsResponse.PluginAttribute>();
	//	int pageNumber = 0;
	//	while (true) {
	//		DescribePluginsRequest request = new DescribePluginsRequest();
	//		request.setPageNumber(pageNumber);
	//		request.setPageSize(3);
	//		DescribePluginsResponse response = client.getAcsResponse(request);
	//		LOGGER.warn("<<<OpenAPI>>> listPlugins page={} got {} results", pageNumber, response.getTotalCount());
	//		if (response.getPlugins().size() > 0) {
	//			for (DescribePluginsResponse.PluginAttribute plugin: response.getPlugins()) {
	//				Object o = plugins.put(plugin.getPluginName(), plugin);
	//				LOGGER.warn("<<<OpenAPI>>> listPlugins get {} id={}", plugin.getPluginName(), plugin.getPluginId());
	//				if (o != null) {
	//					throw new RuntimeException("duplicated plugin name=" + plugin.getPluginName());
	//				}
	//			}
	//		} else {
	//			break;
	//		}
	//	}
	//	return plugins;
	//}
	//
	///**
	// *
	// * @param pluginId
	// * @return
	// * @throws ClientException
	// */
	//public List<DescribePluginApisResponse.ApiPluginSummary> describePluginApis(String pluginId) throws
	//	ClientException {
	//
	//}
	//
	//public List<DescribePluginsResponse.PluginAttribute> describePluginsByApi(String apiId, String groupId, String stage)
	//	throws ClientException {
	//	DescribePluginsByApiRequest request = new DescribePluginsByApiRequest();
	//
	//	request.setApiId(apiId);
	//	request.setGroupId(groupId);
	//	request.setStageName(stage);
	//
	//	DescribePluginsByApiResponse response = client.getAcsResponse(request);
	//	LOGGER.warn("<<<OpenAPI>>> describePluginsByApi num ={} done!", response.getTotalCount());
	//
	//	return response.getPlugins();
	//}
	//
	///**
	// * 清理所有的Plugin
	// */
	//public void cleanPlugins() throws Exception {
	//	while (true) {
	//		DescribePluginsRequest describePluginsRequest = new DescribePluginsRequest();
	//		DescribePluginsResponse describePluginsResponse = client.getAcsResponse(describePluginsRequest);
	//		if (describePluginsResponse.getTotalCount() == 0) {
	//			LOGGER.warn("<<<OpenAPI>>> plugin clean DONE!");
	//			break;
	//		}
	//		LOGGER.warn("<<<OpenAPI>>> api plugin begin {}/{} ", describePluginsResponse.getPlugins().size(),
	//			describePluginsResponse.getTotalCount());
	//		for (DescribePluginsResponse.PluginAttribute a : describePluginsResponse.getPlugins()) {
	//			// detachPlugin(null, groupId, "RELEASE", pluginAttribute.getPluginId());
	//			cleanPlugin(a.getPluginId());
	//			deletePlugin(a.getPluginId());
	//		}
	//	}
	//}
	//
	//public void cleanPlugin(String pluginId) throws ClientException {
	//	List<DescribePluginApisResponse.ApiPluginSummary> apis = describePluginApis(pluginId);
	//	LOGGER.info("<<<OPENAPI>> cleanPlugin id={}, apiCount={}", pluginId, apis.size());
	//	for (DescribePluginApisResponse.ApiPluginSummary api: apis) {
	//		detachPlugin(pluginId, api.getStageName(), api.getGroupId(), api.getApiId());
	//	}
	//}
	//
	//public String initPlugin(String pluginName, String pluginType, String text) throws ClientException {
	//	DescribePluginsRequest request = new DescribePluginsRequest();
	//	request.setPluginName(pluginName);
	//	request.setPluginType(pluginType);
	//
	//	DescribePluginsResponse response = client.getAcsResponse(request);
	//	if (response.getTotalCount() > 0) {
	//		DescribePluginsResponse.PluginAttribute pluginAttribute = response.getPlugins().get(0);
	//		return pluginAttribute.getPluginId();
	//	}
	//
	//	CreatePluginRequest createPluginRequest = new CreatePluginRequest();
	//	createPluginRequest.setPluginName(pluginName);
	//	createPluginRequest.setPluginType(pluginType);
	//	createPluginRequest.setPluginData(text);
	//
	//	CreatePluginResponse createPluginresponse = client.getAcsResponse(createPluginRequest);
	//	LOGGER.warn("<<<OpenAPI>>> createPlugin pluginId ={} done! text=\n{}",
	//		createPluginresponse.getPluginId(), text);
	//	return createPluginresponse.getPluginId();
	//}
}
