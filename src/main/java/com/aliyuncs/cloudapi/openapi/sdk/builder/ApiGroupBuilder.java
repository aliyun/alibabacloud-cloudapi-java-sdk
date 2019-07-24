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

import com.aliyuncs.IAcsClient;
import com.aliyuncs.cloudapi.model.v20160714.*;
import com.aliyuncs.cloudapi.openapi.sdk.common.OperationContext;
import com.aliyuncs.cloudapi.openapi.sdk.common.OperationLogger;
import com.aliyuncs.cloudapi.openapi.sdk.common.OperationLoggerFactory;
import com.aliyuncs.exceptions.ClientException;

import java.util.ArrayList;
import java.util.List;

/**
 * API分组的快捷管理工具
 *
 * @author ailan.gl 2018-12-01
 */
public class ApiGroupBuilder {
	private static final OperationLogger LOGGER = OperationLoggerFactory.getLogger("ApiGroupBuilder");

	private IAcsClient client;
	private String groupId;
	private DescribeApiGroupResponse groupInfo;

	/**
	 * 新建一个Group
	 * @param client
	 */
	public ApiGroupBuilder(IAcsClient client) {
		this.client = client;
		this.groupInfo = new DescribeApiGroupResponse();
	}

	/**
	 * 新建一个Group
	 * @param client
	 */
	public ApiGroupBuilder(IAcsClient client, String groupId) {
		this(client);
		this.groupId = groupId;
	}

	public String getGroupId() {
		return groupId;
	}

	public ApiGroupBuilder name(String name) {
		this.groupInfo.setGroupName(name);
		return this;
	}

	public ApiGroupBuilder description(String description) {
		this.groupInfo.setDescription(description);
		return this;
	}

	public ApiGroupBuilder instanceId(String instanceId) {
		// TODO: 增加instanceId, network, policy 等字段
		return this;
	}

	public DescribeApiGroupResponse getInfo() {
		return groupInfo;
	}


	/**
	 * 使用GroupId获取ApiGroup
	 * @return
	 * @throws ClientException
	 */
	public ApiGroupBuilder describe() throws ClientException {
		DescribeApiGroupRequest request = new DescribeApiGroupRequest();
		request.setGroupId(groupId);
		OperationContext lc = LOGGER.begin("DescribeApiGroup", groupId);
		try {
			DescribeApiGroupResponse response = client.getAcsResponse(request);
			lc.success(response.getRequestId(), response.getGroupName());
			this.groupInfo = response;
			return this;
		} catch (ClientException ex) {
			lc.failed(ex);
			throw ex;
		}
	}

	public ApiGroupBuilder create() throws ClientException {
		CreateApiGroupRequest request = new CreateApiGroupRequest();
		request.setGroupName(groupInfo.getGroupName());
		request.setDescription(groupInfo.getDescription());
		request.setInstanceId(groupInfo.getInstanceId());

		OperationContext c = LOGGER.begin("CreateApiGroup", groupInfo.getGroupName());
		try {
			CreateApiGroupResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			this.groupId = response.getGroupId();
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiGroupBuilder modifyAttribute() {
		return this;
	}

	public ApiGroupBuilder delete() throws ClientException {
		DeleteApiGroupRequest request = new DeleteApiGroupRequest();
		request.setGroupId(groupId);

		OperationContext c = LOGGER.begin("DeleteApiGroup", groupId);
		try {
			DeleteApiGroupResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiGroupBuilder domain(String domain) throws Exception {
		SetDomainRequest request = new SetDomainRequest();
		request.setGroupId(groupId);
		request.setDomainName(domain);
		OperationContext c = LOGGER.begin("SetDomain", groupId + " -> " + domain);
		try {
			SetDomainResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiGroupBuilder deleteDomain(String domain) throws Exception {
		DeleteDomainRequest request = new DeleteDomainRequest();
		request.setGroupId(groupId);
		request.setDomainName(domain);

		String msg = domain + " -> " + groupId;
		OperationContext c = LOGGER.begin("DeleteDomain", msg);
		try {
			DeleteDomainResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiGroupBuilder domainCertificate(String domain, String publicKey, String privateKey)
		throws ClientException {
		SetDomainCertificateRequest request = new SetDomainCertificateRequest();
		request.setGroupId(groupId);
		request.setDomainName(domain);
		request.setCertificateName("cert");
		request.setCertificateBody(publicKey);
		request.setCertificatePrivateKey(privateKey);

		String msg = domain + " -> " + groupId;
		OperationContext c = LOGGER.begin("setDomainCertificate", msg);
		try {
			SetDomainCertificateResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	/**
	 *
	 * @param domain
	 * @return
	 */
	public ApiGroupBuilder deleteDomainCertificate(String domain) {
		//DeleteDomainCertificateRequest request = new DeleteDomainCertificateRequest();
		//request.setGroupId(groupId);
		//request.setDomainName(domain);
		//request.setCertificateId("");
		return this;
	}

	public ApiGroupBuilder modifyInstance(final String instanceId) throws ClientException {
		ModifyApiGroupInstanceRequest request = new ModifyApiGroupInstanceRequest();
		request.setGroupId(groupId);
		request.setTargetInstanceId(instanceId);

		String msg = String.format("%s -> %s", groupId, instanceId);
		OperationContext c = LOGGER.begin("ModifyApiGroupInstance", msg);
		try {
			ModifyApiGroupInstanceResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			groupInfo.setInstanceId(instanceId);
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiGroupBuilder modifyHttpsPolicy(String httpsPolicy) throws ClientException {
		ModifyApiGroupNetworkPolicyRequest request = new ModifyApiGroupNetworkPolicyRequest();
		request.setGroupId(groupId);
		request.setHttpsPolicy(httpsPolicy);

		String msg = String.format("%s -> %s", groupId, httpsPolicy);
		OperationContext c = LOGGER.begin("ModifyApiGroupNetworkPolicy", msg);
		try {
			ModifyApiGroupNetworkPolicyResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiGroupBuilder enableInnerDomain(boolean enableInnerDomain) {
		return this;
	}

	public ApiGroupBuilder enableOuterDomain(boolean enableOuterDomain) {
		return this;
	}

	/**
	 * 开放WebSocket权限
	 *
	 * @param domainName
	 * @throws ClientException
	 */
	public ApiGroupBuilder enableWebSocket(String domainName) throws ClientException {
		SetDomainWebSocketStatusRequest request = new SetDomainWebSocketStatusRequest();
		request.setGroupId(groupId);
		request.setDomainName(domainName);
		request.setActionValue("OPEN");
		client.getAcsResponse(request);

		OperationContext c = LOGGER.begin("SetDomainWebSocket", groupId + " -> " + domainName);
		try {
			SetDomainWebSocketStatusResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public List<DescribeApisResponse.ApiSummary> listApis() throws ClientException {
		List<DescribeApisResponse.ApiSummary> result = new ArrayList<DescribeApisResponse.ApiSummary>();

		for (int pageNumber = 1; pageNumber < 100; pageNumber++) {
			DescribeApisRequest request = new DescribeApisRequest();
			request.setGroupId(groupId);
			request.setPageNumber(pageNumber);
			request.setPageSize(100);

			OperationContext c = LOGGER.begin("DescribeApis", groupId + ":" + pageNumber);
			try {
				DescribeApisResponse response = client.getAcsResponse(request);
				c.success(response.getRequestId(), "count:" + response.getApiSummarys().size());
				if (response.getApiSummarys().size() == 0) {
					break;
				}
				for (DescribeApisResponse.ApiSummary a: response.getApiSummarys()) {
					result.add(a);
				}
			} catch (ClientException ex) {
				c.failed(ex);
				throw ex;
			}
		}
		return result;
	}

	public ApiGroupBuilder stageVariable(String stageName, String name, String value) throws ClientException {
		CreateApiStageVariableRequest request = new CreateApiStageVariableRequest();
		request.setGroupId(groupId);
		request.setStageId(getStageIdByName(stageName));
		request.setVariableName(name);
		request.setVariableValue(value);

		String msg = String.format("%s[%s] %s -> %s", groupId, stageName, name, value);
		OperationContext c = LOGGER.begin("CreateApiStageVariable", msg);
		try {
			CreateApiStageVariableResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public ApiGroupBuilder deleteStageVariable(String stageName, String name) throws ClientException {
		DeleteApiStageVariableRequest request = new DeleteApiStageVariableRequest();
		request.setGroupId(groupId);
		request.setStageId(getStageIdByName(stageName));
		request.setVariableName(name);

		String msg = String.format("%s[%s] %s", groupId, stageName, name);
		OperationContext c = LOGGER.begin("DeleteApiStageVariable", msg);
		try {
			DeleteApiStageVariableResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	// TODO nextVersion
	//public ApiGroupBuilder cleanStageVariables(String stageName) throws ClientException {
	//	String stageId = getStageIdByName(groupId, stageName);
	//	DescribeApiStageRequest request = new DescribeApiStageRequest();
	//	request.setGroupId(groupId);
	//	request.setStageId(stageId);
	//	DescribeApiStageResponse response = client.getAcsResponse(request);
	//	for (DescribeApiStageResponse.VariableItem item: response.getVariables()) {
	//		deleteVariable(groupId, stageId, stageName, item.getVariableName());
	//	}
	//	return this;
	//}

	public String getStageIdByName(String stageName) throws ClientException {
		if (groupInfo == null) {
			describe();
		}

		for (DescribeApiGroupResponse.StageInfo i: groupInfo.getStageItems()) {
			if (stageName.equalsIgnoreCase(i.getStageName())) {
				return i.getStageId();
			}
		}
		throw new IllegalArgumentException("Unknown StageName:" + stageName);
	}

	/**
	 * 使用GroupName获取Group, 模糊查询, 可能返回多个值
	 * @param client
	 * @param groupName
	 * @return
	 * @throws ClientException
	 */
	public static List<ApiGroupBuilder> describeByGroupName(IAcsClient client, String groupName) throws ClientException {
		DescribeApiGroupsRequest request = new DescribeApiGroupsRequest();
		request.setGroupName(groupName);
		OperationContext lc = LOGGER.begin("DescribeApiGroups", groupName);

		try {
			DescribeApiGroupsResponse response = client.getAcsResponse(request);

			List<ApiGroupBuilder> result = new ArrayList<ApiGroupBuilder>();
			if (response.getTotalCount() > 0) {
				for (DescribeApiGroupsResponse.ApiGroupAttribute a: response.getApiGroupAttributes()) {
					result.add(new ApiGroupBuilder(client, a.getGroupId()).describe());
				}
			}
			lc.success(response.getRequestId(), "" + response.getTotalCount());
			return result;
		} catch (ClientException ex) {
			lc.failed(ex);
			throw ex;
		}
	}

}

//public ApiGroup bindDomain() {
//
//}
//
//public String activateInnerDomain() {
//
//}
//
//public ApiGroup delete() {
//
//}
//while (true) {
//	DescribeApisRequest r = new DescribeApisRequest();
//	r.setGroupId(groupId);
//
//	DescribeApisResponse response = client.getAcsResponse(r);
//	LOGGER.warn("<<<OpenAPI>>> api clean begin {}/{} ", response.getApiSummarys().size(),
//		response.getTotalCount());
//	for (DescribeApisResponse.ApiSummary api : response.getApiSummarys()) {
//		try {
//			abolishApi(groupId, api.getApiId(), "RELEASE");
//			abolishApi(groupId, api.getApiId(), "TEST");
//			abolishApi(groupId, api.getApiId(), "PRE");
//		} catch (Exception ex) {
//			LOGGER.warn("<<<OpenAPI>>> abolishApi {} can't be done!", api.getApiId());
//		}
//
//		deleteApi(groupId, api.getApiId());
//	}
//	if (response.getApiSummarys().size() == response.getTotalCount()) {
//		LOGGER.warn("<<<OpenAPI>>> api clean DONE!");
//		break;
//	}
//}
