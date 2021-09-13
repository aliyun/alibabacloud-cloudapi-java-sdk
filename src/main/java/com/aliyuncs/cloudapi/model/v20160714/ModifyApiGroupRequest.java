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
package com.aliyuncs.cloudapi.model.v20160714;

import com.aliyuncs.RpcAcsRequest;

/**
 * @author auto create
 *
 */
public class ModifyApiGroupRequest extends RpcAcsRequest<ModifyApiGroupResponse> {
	
	public ModifyApiGroupRequest() {
		super("CloudAPI", "2016-07-14", "ModifyApiGroup", "apigateway");
	}

	private String groupId;

	private String groupName;

	private String description;

	private String userLogConfig;

	private String customTraceConfig;

	private String defaultDomain;

	private String basePath;

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
		putQueryParameter("GroupId", groupId);
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
		putQueryParameter("GroupName", groupName);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
		putQueryParameter("Description", description);
	}

	public String getUserLogConfig() {
		return userLogConfig;
	}

	public void setUserLogConfig(String userLogConfig) {
		this.userLogConfig = userLogConfig;
		putQueryParameter("UserLogConfig", userLogConfig);
	}

	public String getCustomTraceConfig() {
		return customTraceConfig;
	}

	public void setCustomTraceConfig(String customTraceConfig) {
		this.customTraceConfig = customTraceConfig;
		putQueryParameter("CustomTraceConfig", customTraceConfig);
	}

	public String getDefaultDomain() {
		return defaultDomain;
	}

	public void setDefaultDomain(String defaultDomain) {
		this.defaultDomain = defaultDomain;
		putQueryParameter("DefaultDomain", defaultDomain);
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
		putQueryParameter("BasePath", basePath);
	}

	@Override
	public Class<ModifyApiGroupResponse> getResponseClass() {
		return ModifyApiGroupResponse.class;
	}

}
