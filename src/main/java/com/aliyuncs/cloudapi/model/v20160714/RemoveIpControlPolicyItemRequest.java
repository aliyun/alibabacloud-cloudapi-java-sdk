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
public class RemoveIpControlPolicyItemRequest extends RpcAcsRequest<RemoveIpControlPolicyItemResponse> {
	
	public RemoveIpControlPolicyItemRequest() {
		super("CloudAPI", "2016-07-14", "RemoveIpControlPolicyItem", "apigateway");
	}

	private String ipControlId;

	private String policyItemIds;

	public String getIpControlId() {
		return this.ipControlId;
	}

	public void setIpControlId(String ipControlId) {
		this.ipControlId = ipControlId;
		if(ipControlId != null){
			putQueryParameter("IpControlId", ipControlId);
		}
	}

	public String getPolicyItemIds() {
		return this.policyItemIds;
	}

	public void setPolicyItemIds(String policyItemIds) {
		this.policyItemIds = policyItemIds;
		if(policyItemIds != null){
			putQueryParameter("PolicyItemIds", policyItemIds);
		}
	}

	@Override
	public Class<RemoveIpControlPolicyItemResponse> getResponseClass() {
		return RemoveIpControlPolicyItemResponse.class;
	}

}
