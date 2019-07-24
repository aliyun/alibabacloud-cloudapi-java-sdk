/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.aliyuncs.cloudapi.openapi.sdk.builder;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cloudapi.model.v20160714.*;
import com.aliyuncs.cloudapi.openapi.sdk.common.OperationContext;
import com.aliyuncs.cloudapi.openapi.sdk.common.OperationLogger;
import com.aliyuncs.cloudapi.openapi.sdk.common.OperationLoggerFactory;
import com.aliyuncs.cloudapi.openapi.sdk.constant.ApiAuthType;
import com.aliyuncs.cloudapi.openapi.sdk.constant.ParameterLocation;
import com.aliyuncs.exceptions.ClientException;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于构建API的Builder
 *
 * @author ailan.gl 2018-09-10
 */
public class ApiBuilder {
	private static final OperationLogger LOGGER = OperationLoggerFactory.getLogger("Api");

	private IAcsClient client;

	private String apiId;
	private String groupId;

	private CreateApiRequest apiRequest;
	private DescribeApiResponse.RequestConfig requestConfig;
	private DescribeApiResponse.ServiceConfig serviceConfig;
	private DescribeApiResponse.OpenIdConnectConfig oidcConfig;
	private List<DescribeApiResponse.RequestParameter> requestParameters = new ArrayList<DescribeApiResponse.RequestParameter>();
	private List<DescribeApiResponse.ServiceParameter> serviceParameters = new ArrayList<DescribeApiResponse.ServiceParameter>();
	private List<DescribeApiResponse.ServiceParameterMap> serviceParameterMap = new ArrayList<DescribeApiResponse.ServiceParameterMap>();
	private List<DescribeApiResponse.ConstantParameter> constantParameters = new ArrayList<DescribeApiResponse.ConstantParameter>();
	private List<DescribeApiResponse.SystemParameter> systemParameters = new ArrayList<DescribeApiResponse.SystemParameter>();

	public ApiBuilder(IAcsClient client, String groupId) {
		this.client = client;
		this.groupId = groupId;

		apiRequest = new CreateApiRequest();
		apiRequest.setGroupId(groupId);
		apiRequest.setAuthType("APP");
		apiRequest.setVisibility("PRIVATE");
		apiRequest.setResultType("JSON");
		apiRequest.setResultSample("{}");

		requestConfig = new DescribeApiResponse.RequestConfig();
		requestConfig.setRequestMode("");
		requestConfig.setRequestProtocol("HTTP");
		requestConfig.setRequestMode("PASSTHROUGH");
		// requestConfig.setBodyFormat("STREAM");

		requestConfig.setPostBodyDescription("");
		serviceConfig = new DescribeApiResponse.ServiceConfig();
		serviceConfig.setContentTypeCatagory("CLIENT");
		serviceConfig.setMock("false");
		serviceConfig.setServiceVpcEnable(false);
	}

	public String getApiId() {
		return this.apiId;
	}

	public String getPath() {
		return this.requestConfig.getRequestPath();
	}

	public String getMethod() {
		return this.requestConfig.getRequestHttpMethod();
	}

	public ApiBuilder describe() throws ClientException {
		DescribeApiRequest request = new DescribeApiRequest();
		request.setApiId(apiId);
		OperationContext c = LOGGER.begin("DescribeApi", apiId);

		try {
			DescribeApiResponse response = client.getAcsResponse(request);

			this.oidcConfig = response.getOpenIdConnectConfig();
			this.requestConfig = response.getRequestConfig();
			this.serviceConfig = response.getServiceConfig();
			this.requestParameters = response.getRequestParameters();
			this.serviceParameterMap = response.getServiceParametersMap();
			this.constantParameters = response.getConstantParameters();
			this.systemParameters = response.getSystemParameters();
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiBuilder(IAcsClient client, String groupId, String apiId) {
		this.client = client;
		this.groupId = groupId;
		this.apiId = apiId;
	}

	public ApiBuilder name(String name) {
		apiRequest.setApiName(name);
		return this;
	}

	public ApiBuilder path(String path) {
		requestConfig.setRequestPath(path);
		return this;
	}

	public ApiBuilder method(String method) {
		requestConfig.setRequestHttpMethod(method);
		if (serviceConfig.getServiceHttpMethod() == null || serviceConfig.getServiceHttpMethod().length() == 0) {
			serviceConfig.setServiceHttpMethod(method);
		}

		return this;
	}

	public ApiBuilder aoneAppName(String aoneAppName) {
		serviceConfig.setAoneAppName(aoneAppName);
		return this;
	}

	public ApiBuilder backendHttp(String method, String host, String path) {
		if (host.startsWith("https:")) {
			serviceConfig.setServiceProtocol("HTTPS");
		} else {
			serviceConfig.setAoneAppName("HTTP");
		}
		serviceConfig.setServiceHttpMethod(method);
		serviceConfig.setServiceAddress(host);
		serviceConfig.setServicePath(path);
		serviceConfig.setServiceTimeout(10000);
		return this;
	}

	public ApiBuilder backendHttpVpc(String method, String vpcAccessName, String path) {
		DescribeApiResponse.VpcConfig vpcConfig = new DescribeApiResponse.VpcConfig();
		vpcConfig.setName(vpcAccessName);
		serviceConfig.setVpcConfig(vpcConfig);
		serviceConfig.setServiceTimeout(10000);
		return this;
	}

	public ApiBuilder backendPath(String path) {
		serviceConfig.setServicePath(path);
		return this;
	}

	public ApiBuilder backendFunctionCompute(String regionId, String roleArn, String serviceName, String functionName) {
		return backendFunctionCompute(regionId, roleArn, serviceName, functionName, null);
	}

	public ApiBuilder backendFunctionCompute(String regionId, String roleArn, String serviceName, String functionName, String version) {
		DescribeApiResponse.FunctionComputeConfig fc = new DescribeApiResponse.FunctionComputeConfig();
		fc.setFcRegionId(regionId);
		fc.setServiceName(serviceName);
		fc.setFunctionName(functionName);
		fc.setRoleArn(roleArn);
		// TODO: set version
		serviceConfig.setFunctionComputeConfig(fc);
		serviceConfig.setServiceTimeout(10000);
		return this;
	}

	public ApiBuilder backendMock(String mockResult) {
		serviceConfig.setMock("TRUE");
		serviceConfig.setMockResult(mockResult);
		return this;
	}

	public ApiBuilder backendMock(int statusCode, String mockBody) {
		serviceConfig.setMockStatusCode(statusCode);
		serviceConfig.setMock("TRUE");
		serviceConfig.setMockResult(mockBody);
		return this;
	}

	public ApiBuilder backendMockHeader(String name, String value) {
		if (serviceConfig.getMockHeaders() == null) {
			serviceConfig.setMockHeaders(new ArrayList<DescribeApiResponse.MockHeader>());
		}
		DescribeApiResponse.MockHeader header = new DescribeApiResponse.MockHeader();
		header.setHeaderName(name);
		header.setHeaderValue(value);
		serviceConfig.getMockHeaders().add(header);
		return this;
	}

	public ApiBuilder backendTimeout(int timeout) {
		serviceConfig.setServiceTimeout(timeout);
		return this;
	}

	public ApiBuilder enableMapping() {
		requestConfig.setRequestMode("MAPPING");
		return this;
	}

	public ApiBuilder websocketApiType(String websocketApiType) {
		apiRequest.setWebSocketApiType(websocketApiType);
		return this;
	}

	public ApiBuilder oidcGetToken(boolean needAppAuth, String kid, String publicJwk) {
		apiRequest.setAuthType(needAppAuth ? "APPOPENID" : "OPENID");
		oidcConfig = new DescribeApiResponse.OpenIdConnectConfig();
		oidcConfig.setOpenIdApiType("IDTOKEN");
		oidcConfig.setPublicKeyId(kid);
		oidcConfig.setPublicKey(publicJwk);
		return this;
	}

	public ApiBuilder oidcBusiness(boolean needAppAuth, String kid, String parameterName) {
		apiRequest.setAuthType(needAppAuth ? "APPOPENID" : "OPENID");
		oidcConfig = new DescribeApiResponse.OpenIdConnectConfig();
		oidcConfig.setOpenIdApiType("BUSINESS");
		oidcConfig.setIdTokenParamName(parameterName);
		return this;
	}

	public ApiBuilder bodyFormatStream() {
		requestConfig.setBodyFormat("STREAM");
		return this;
	}

	public ApiBuilder bodyFormatForm() {
		requestConfig.setBodyFormat("FORM");
		return this;
	}

	public ApiBuilder enableHMacSHA1() {
		apiRequest.setAllowSignatureMethod("HmacSHA1,HmacSHA256");
		return this;
	}

	public ApiParameterBuilder pathParameter(String name) {
		return parameter(name, ParameterLocation.PATH).required();
	}

	public ApiParameterBuilder queryParameter(String name) {
		return parameter(name, ParameterLocation.QUERY);
	}

	public ApiParameterBuilder formParameter(String name) {
		requestConfig.setRequestMode("MAPPING");
		requestConfig.setBodyFormat("FORM");
		return parameter(name, ParameterLocation.FORM_DATA);
	}

	public ApiParameterBuilder headerParameter(String name) {
		return parameter(name, ParameterLocation.HEADER);
	}

	public ApiParameterBuilder parameter(String name, ParameterLocation location) {
		return new ApiParameterBuilder(this, name, location);
	}

	public ApiParameterBuilder parameter(String name, ParameterLocation location, String serviceName) {
		return new ApiParameterBuilder(this, name, location, serviceName);
	}

	public ApiParameterBuilder oidcParameter(String name, ParameterLocation location, String serviceName) {
		return new ApiParameterBuilder(this, name, location, serviceName);
	}

	public ApiBuilder protocol(String protocol) {
		requestConfig.setRequestProtocol(protocol);
		return this;
	}

	public ApiBuilder authType(ApiAuthType authType) {
		apiRequest.setAuthType(authType.openapiValue());
		return this;
	}

	public ApiBuilder forceNonceCheck() {
		return forceNonceCheck(true);
	}

	public ApiBuilder forceNonceCheck(boolean b) {
		apiRequest.setForceNonceCheck(b);
		return this;
	}

	public ApiBuilder disableInternet() {
		return disableInternet(true);
	}

	public ApiBuilder disableInternet(boolean b) {
		apiRequest.setDisableInternet(b);
		return this;
	}

	public ApiBuilder systemParameter(String name, ParameterLocation location, String systemName) {
		DescribeApiResponse.SystemParameter sp = new DescribeApiResponse.SystemParameter();
		sp.setLocation(location.openapiValue());
		sp.setServiceParameterName(name);
		sp.setParameterName(systemName);
		systemParameters.add(sp);
		return this;
	}

	public ApiBuilder constantParameter(String name, ParameterLocation location, String value) {
		DescribeApiResponse.ConstantParameter cp = new DescribeApiResponse.ConstantParameter();
		cp.setLocation(location.openapiValue());
		cp.setConstantValue(value);
		cp.setServiceParameterName(name);
		constantParameters.add(cp);
		return this;
	}

	public ApiBuilder create() throws ClientException {
		CreateApiRequest request = apiRequest;
		request.setRequestConfig(requestConfig);
		request.setOpenIdConnectConfig(oidcConfig);
		request.setServiceConfig(serviceConfig);
		request.setRequestParameters(requestParameters);
		request.setServiceParameters(serviceParameters);
		request.setServiceParametersMap(serviceParameterMap);
		request.setConstantParameters(constantParameters);
		request.setSystemParameters(systemParameters);

		OperationContext c = LOGGER.begin("CreateApi", JSON.toJSONString(request));
		try {
			CreateApiResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok:" + response.getApiId());
			this.apiId = response.getApiId();
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	/**
	 * 变更API信息
	 * @return
	 * @throws ClientException
	 */
	public ApiBuilder modify() throws ClientException {
		ModifyApiRequest mr = new ModifyApiRequest();
		mr.setApiId(apiId);
		mr.setGroupId(groupId);
		mr.setForceNonceCheck(this.apiRequest.getForceNonceCheck());
		mr.setApiName(this.apiRequest.getApiName());
		mr.setAuthType(this.apiRequest.getAuthType());

		mr.setDescription(this.apiRequest.getDescription());
		mr.setVisibility(this.apiRequest.getVisibility());
		mr.setResultType(this.apiRequest.getResultType());
		mr.setResultSample(this.apiRequest.getResultSample());

		mr.setRequestConfig(requestConfig);
		mr.setOpenIdConnectConfig(oidcConfig);
		mr.setServiceConfig(serviceConfig);
		mr.setRequestParameters(requestParameters);
		mr.setServiceParameters(serviceParameters);
		mr.setServiceParametersMap(serviceParameterMap);
		mr.setConstantParameters(constantParameters);
		mr.setSystemParameters(systemParameters);

		OperationContext c = LOGGER.begin("ModifyApi", JSON.toJSONString(mr, true));
		try {
			ModifyApiResponse response = client.getAcsResponse(mr);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	/**
	 * 部署API
	 * @param stageName
	 * @return
	 * @throws ClientException
	 */
	public ApiBuilder deploy(String stageName) throws ClientException {
		return deploy(stageName, "deploying:" + stageName);
	}

	/**
	 * 部署API
	 * @param stageName
	 * @param description
	 * @return
	 * @throws ClientException
	 */
	public ApiBuilder deploy(String stageName, String description) throws ClientException {
		DeployApiRequest request = new DeployApiRequest();
		request.setGroupId(groupId);
		request.setApiId(apiId);
		request.setStageName(stageName);
		request.setDescription(description);

		String msg = String.format("%s/%s -> %s", groupId, apiId, stageName);
		OperationContext c = LOGGER.begin("DeployApi", msg);

		try {
			DeployApiResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiBuilder abolishAll() throws ClientException {
		DescribeDeployedApisRequest request = new DescribeDeployedApisRequest();
		request.setGroupId(groupId);
		request.setApiId(apiId);

		OperationContext c = LOGGER.begin("DescribeDeployedApis", apiId);
		List<String> stages = new ArrayList<String>();
		try {
			DescribeDeployedApisResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "count:" + response.getDeployedApis().size());
			for (DescribeDeployedApisResponse.DeployedApiItem i: response.getDeployedApis()) {
				stages.add(i.getStageName());
			}
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}

		for (String stageName: stages) {
			abolish(stageName);
		}
		return this;
	}

	public ApiBuilder abolish(String stageName) throws ClientException {
		AbolishApiRequest request = new AbolishApiRequest();
		request.setGroupId(groupId);
		request.setApiId(apiId);
		request.setStageName(stageName);

		String msg = String.format("%s/%s -> %s", groupId, apiId, stageName);
		OperationContext c = LOGGER.begin("AbolishApi", msg);

		try {
			AbolishApiResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	// TODO
	//public ApiBuilder switchVersion() {
	//	//
	//	//	SwitchApiRequest r;
	//	//	r = new SwitchApiRequest();
	//	//	r.setApiId(apiId);
	//	//	r.setStageName(stageName);
	//	//	r.setDescription("fgdfgdfgdfgfdwy");
	//	//	r.setHistoryVersion(version);
	//	//	SwitchApiResponse response = client.getAcsResponse(r);
	//	//	LOGGER.warn("<<<OpenAPI>>> switchApi {} done!", apiId);
	//}

	public ApiBuilder authorize(String stageName, long appId) throws ClientException {
		return authorize(stageName, appId, null);
	}

	public ApiBuilder authorize(String stageName, long appId, String authValidTime) throws ClientException {
		SetApisAuthoritiesRequest request = new SetApisAuthoritiesRequest();
		request.setGroupId(groupId);
		request.setApiIds(apiId);
		request.setStageName(stageName);
		request.setAppId(appId);
		request.setAuthValidTime(authValidTime);

		String msg = String.format("%s/%s[%s] -> %d", groupId, apiId, stageName, appId);
		OperationContext c = LOGGER.begin("SetApisAuthorities", msg);

		try {
			SetApisAuthoritiesResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	//public ApiBuilder unauthorize(long appId) throws ClientException {
	//	for (String stage: stages) {
	//		unauthorize(stage, appId);
	//	}
	//	return this;
	//}

	public ApiBuilder unauthorize(String stageName, long appId) throws ClientException {
		RemoveApisAuthoritiesRequest request = new RemoveApisAuthoritiesRequest();
		request.setGroupId(groupId);
		request.setApiIds(apiId);
		request.setStageName(stageName);
		request.setAppId(appId);

		String msg = String.format("%s/%s[%s] -> %d", groupId, apiId, stageName, appId);
		OperationContext c = LOGGER.begin("RemoveApisAuthorities", msg);

		try {
			RemoveApisAuthoritiesResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiBuilder delete() throws ClientException {
		DeleteApiRequest request = new DeleteApiRequest();
		request.setApiId(apiId);
		request.setGroupId(groupId);

		String msg = String.format("%s/%s", groupId, apiId);
		OperationContext c = LOGGER.begin("DeleteApi", msg);

		try {
			DeleteApiResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiBuilder plugin(String stageName, String pluginId) throws ClientException {
		AttachPluginRequest request = new AttachPluginRequest();
		request.setPluginId(pluginId);
		request.setStageName(stageName);
		request.setApiId(apiId);

		String msg = String.format("%s[%s] -> %s", apiId, stageName, pluginId);
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

	public ApiBuilder detachPlugin(String stageName, String pluginId) throws ClientException {
		DetachPluginRequest request = new DetachPluginRequest();
		request.setApiId(apiId);
		request.setStageName(stageName);
		request.setPluginId(pluginId);

		String msg = String.format("%s[%s] -> %s", apiId, stageName, pluginId);
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

	//public ApiBuilder trafficControl(String stageName, String throttleId) throws ClientException {
	//	openapi.bindApiThrottle(stageName, po.getId(), throttleId);
	//	return this;
	//}
	//
	//public ApiBuilder unbindTrafficControl(String stageName, String throttleId) throws ClientException {
	//	openapi.bindApiThrottle(stageName, po.getId(), throttleId);
	//	return this;
	//}
	//
	//public ApiBuilder bindIpControl(String stageName, String controlId) throws ClientException {
	//	openapi.bindApiIpControl(stageName, po.getId(), controlId);
	//	return this;
	//}

	public static ApiBuilder describeByName(IAcsClient client, String groupId, String name) throws ClientException {
		DescribeApisRequest request = new DescribeApisRequest();
		request.setGroupId(groupId);
		request.setApiName(name);

		OperationContext c = LOGGER.begin("DescribeApis", groupId + ":" + name);
		try {
			DescribeApisResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "count:" + response.getTotalCount());
			for (DescribeApisResponse.ApiSummary a: response.getApiSummarys()) {
				if (a.getApiName().equals(name)) {
					ApiBuilder api = new ApiBuilder(client, groupId, a.getApiId());
					return api;
				}
			}
			return null;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public static List<ApiBuilder> importSwagger(IAcsClient client, String groupId, String swaggerContent) throws ClientException {
		ImportSwaggerRequest request = new ImportSwaggerRequest();
		request.setGroupId(groupId);
		request.setbOverwrite(true);
		if (swaggerContent.startsWith("{")) {
			request.setData(swaggerContent);
			request.setDataFormat("json");
		} else {
			request.setData(swaggerContent);
			request.setDataFormat("yaml");
		}

		OperationContext c = LOGGER.begin("ImportSwagger", groupId + "\n" + swaggerContent);
		try {
			ImportSwaggerResponse response = client.getAcsResponse(request);
			if (response.getFailed().size() > 0) {
				throw new ClientException("BadSwagger", response.getFailed().get(0).getErrorMsg(), response.getRequestId());
			}
			c.success(response.getRequestId(), "apis:" + response.getSuccess().size());

			List<ApiBuilder> result = new ArrayList<ApiBuilder>();
			for (ImportSwaggerResponse.ApiImportSwaggerSuccess r: response.getSuccess()) {
				ApiBuilder api = new ApiBuilder(client, groupId, r.getApiUid());
				result.add(api);
			}
			return result;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	/**
	 * 由ApiParameterBuilder.and()完成添加工作
	 * @param p
	 * @param sp
	 */
	void addParameter(DescribeApiResponse.RequestParameter p, DescribeApiResponse.ServiceParameter sp) {
		requestParameters.add(p);
		serviceParameters.add(sp);
		DescribeApiResponse.ServiceParameterMap mapping = new DescribeApiResponse.ServiceParameterMap();
		mapping.setRequestParameterName(p.getApiParameterName());
		mapping.setServiceParameterName(sp.getServiceParameterName());
		serviceParameterMap.add(mapping);
	}

}
	//
	//
	///**
	// * 获取API信息
	// *
	// * @param apiId
	// * @return
	// * @throws ClientException
	// */
	//public DescribeApiResponse getApiInfo(String apiId) throws ClientException {
	//	DescribeApiRequest request = new DescribeApiRequest();
	//	request.setApiId(apiId);
	//	return client.getAcsResponse(request);
	//}
	//
	//public DescribeApiDocResponse getApiDocInfo(String apiId) throws ClientException {
	//	DescribeApiDocRequest request = new DescribeApiDocRequest();
	//	request.setApiId(apiId);
	//	request.setStageName("RELEASE");
	//	return client.getAcsResponse(request);
	//}
	//
	//public DescribeApiHistoryResponse getApiHistoryInfo(String apiId) throws ClientException {
	//	DescribeApiHistoryRequest request = new DescribeApiHistoryRequest();
	//	request.setApiId(apiId);
	//	request.setStageName("RELEASE");
	//	request.setHistoryVersion("20190129134839143");
	//	return client.getAcsResponse(request);
	//}
	//
	//public DescribeDeployedApiResponse getDeployedApiInfo(String apiId) throws ClientException {
	//	DescribeDeployedApiRequest request = new DescribeDeployedApiRequest();
	//	request.setApiId(apiId);
	//	request.setStageName("RELEASE");
	//	return client.getAcsResponse(request);
	//}
	//




