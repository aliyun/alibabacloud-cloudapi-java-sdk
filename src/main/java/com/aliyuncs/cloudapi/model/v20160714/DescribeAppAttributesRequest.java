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

import java.util.List;

import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.cloudapi.openapi.sdk.entity.Tag;

/**
 * @author auto create
 */
public class DescribeAppAttributesRequest extends RpcAcsRequest<DescribeAppAttributesResponse> {
	
	public DescribeAppAttributesRequest() {
		super("CloudAPI", "2016-07-14", "DescribeAppAttributes", "apigateway");
	}

	private Long appId;

	private String appName;

	private Integer pageNumber;

	private Integer pageSize;

	private List<Tag> tags;

	private Boolean enableTagAuth;

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

	public Long getAppId() {
		return this.appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
		putQueryParameter("AppId", appId);
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
		putQueryParameter("AppName", appName);
	}

	public Integer getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		putQueryParameter("PageNumber", pageNumber);
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		putQueryParameter("PageSize", pageSize);
	}

	public Boolean getEnableTagAuth() {
		return enableTagAuth;
	}

	public void setEnableTagAuth(Boolean enableTagAuth) {
		this.enableTagAuth = enableTagAuth;
		putQueryParameter("EnableTagAuth", enableTagAuth);
	}

	@Override
	public Class<DescribeAppAttributesResponse> getResponseClass() {
		return DescribeAppAttributesResponse.class;
	}

}
