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
public class DescribeIpControlPolicyItemsRequest extends RpcAcsRequest<DescribeIpControlPolicyItemsResponse> {
	
	public DescribeIpControlPolicyItemsRequest() {
		super("CloudAPI", "2016-07-14", "DescribeIpControlPolicyItems", "apigateway");
	}

	private String ipControlId;

	private String policyItemId;

	private Integer pageSize;

	private Integer pageNumber;

	public String getIpControlId() {
		return this.ipControlId;
	}

	public void setIpControlId(String ipControlId) {
		this.ipControlId = ipControlId;
		if(ipControlId != null){
			putQueryParameter("IpControlId", ipControlId);
		}
	}

	public String getPolicyItemId() {
		return this.policyItemId;
	}

	public void setPolicyItemId(String policyItemId) {
		this.policyItemId = policyItemId;
		if(policyItemId != null){
			putQueryParameter("PolicyItemId", policyItemId);
		}
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if(pageSize != null){
			putQueryParameter("PageSize", pageSize.toString());
		}
	}

	public Integer getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		if(pageNumber != null){
			putQueryParameter("PageNumber", pageNumber.toString());
		}
	}

	@Override
	public Class<DescribeIpControlPolicyItemsResponse> getResponseClass() {
		return DescribeIpControlPolicyItemsResponse.class;
	}

}
