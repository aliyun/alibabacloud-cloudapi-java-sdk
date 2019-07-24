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
 * 鉴权相关信息
 *
 * @author ailan.gl 2018-09-14
 */
public class AppBuilder {
	private static final OperationLogger LOGGER = OperationLoggerFactory.getLogger("AppBuilder");

	private IAcsClient client;

	private long appId;
	private String appName;
	private String description;
	private String appKey;
	private String appSecret;
	private String appCode;

	public AppBuilder(IAcsClient client) {
		this.client = client;
	}

	public AppBuilder(IAcsClient client, long appId) {
		this(client);
		this.appId = appId;
	}

	public AppBuilder appName(String name) {
		this.appName = name;
		return this;
	}

	public AppBuilder description(String description) {
		this.description = description;
		return this;
	}

	public long getAppId() {
		return appId;
	}

	public String getAppName() {
		return appName;
	}

	public String getDescription() {
		return description;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getAppCode() {
		return appCode;
	}

	/**
	 * 查询App的状态
	 * @return
	 * @throws ClientException
	 */
	public AppBuilder describe() throws ClientException {
		DescribeAppSecurityRequest request = new DescribeAppSecurityRequest();
		request.setAppId(appId);

		OperationContext oc = LOGGER.begin("DescribeAppSecurity", "" + appId);
		try {
			DescribeAppSecurityResponse response = client.getAcsResponse(request);
			this.appKey = response.getAppKey();
			this.appSecret = response.getAppSecret();
			return this;
		} catch (ClientException ex) {
			oc.failed(ex);
			throw ex;
		}
	}

	public AppBuilder create() throws ClientException {
		CreateAppRequest request = new CreateAppRequest();
		request.setAppName(appName);
		request.setDescription(description);

		OperationContext c = LOGGER.begin("CreateApp", appName);
		try {
			CreateAppResponse response = client.getAcsResponse(request);
			this.appId = response.getAppId();
			c.success(response.getRequestId(), "ok:" + appId);
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public AppBuilder reset() throws ClientException {
		ResetAppKeySecretRequest request = new ResetAppKeySecretRequest();
		request.setAppKey(appKey);

		OperationContext c = LOGGER.begin("ResetAppKeySecret", appKey);
		try {
			ResetAppKeySecretResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public AppBuilder resetSecret() throws ClientException {
		ResetAppSecretRequest request = new ResetAppSecretRequest();
		request.setAppKey(appKey);

		OperationContext c = LOGGER.begin("ResetAppSecret", appKey);
		try {
			ResetAppSecretResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public AppBuilder delete() throws ClientException {
		DeleteAppRequest request = new DeleteAppRequest();
		request.setAppId(appId);

		OperationContext c = LOGGER.begin("DeleteApp", "appId:" + appId);
		try {
			DeleteAppResponse response = client.getAcsResponse(request);
			c.success(response.getRequestId(), "ok");
			return this;
		} catch (ClientException ex) {
			c.failed(ex);
			throw ex;
		}
	}

	public AppBuilder sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	public static List<AppBuilder> describeByAppName(IAcsClient client, String appName) throws ClientException {
		DescribeAppAttributesRequest request = new DescribeAppAttributesRequest();
		request.setAppName(appName);

		DescribeAppAttributesResponse r = client.getAcsResponse(request);
		List<AppBuilder> result = new ArrayList<AppBuilder>();
		for (DescribeAppAttributesResponse.AppAttribute a : r.getApps()) {
			AppBuilder app = new AppBuilder(client, a.getAppId()).describe();
			app.appName(a.getAppName())
				.description(a.getDescription());
			result.add(app);
		}
		return result;
	}
}
