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
 *
 */
public class CreateApiGroupRequest extends RpcAcsRequest<CreateApiGroupResponse> {
	
	public CreateApiGroupRequest() {
		super("CloudAPI", "2016-07-14", "CreateApiGroup", "apigateway");
	}

	private String instanceId;

	private String groupName;

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

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
		putQueryParameter("InstanceId", instanceId);
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

	@Override
	public Class<CreateApiGroupResponse> getResponseClass() {
		return CreateApiGroupResponse.class;
	}

}
