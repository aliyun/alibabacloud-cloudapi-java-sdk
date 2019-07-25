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

import com.aliyuncs.AcsResponse;
import com.aliyuncs.cloudapi.transform.v20160714.DescribeHistoryApisResponseUnmarshaller;
import com.aliyuncs.cloudapi.transform.v20160714.DescribeInstancesResponseUnmarshaller;
import com.aliyuncs.transform.UnmarshallerContext;

import java.util.List;

/**
 * @author auto create
 */
public class DescribeInstancesResponse extends AcsResponse {

	private String requestId;

	private Integer totalCount;

	private Integer pageSize;

	private Integer pageNumber;

	private List<InstanceAttribute> instances;

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Integer getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<InstanceAttribute> getInstances() {
		return instances;
	}

	public void setInstances(
		List<InstanceAttribute> instances) {
		this.instances = instances;
	}

	public static class InstanceAttribute {

		private String instanceId;

		private String instanceType;

		private String instanceSpec;

		private String instanceName;

		private String status;

		private String createdTime;

		private String internetEgressAddress;

		private String vpcEgressAddress;

		private String classicEgressAddress;

		private String expiredTime;

		private String httpsPolicy;

		public String getInstanceId() {
			return instanceId;
		}

		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}

		public String getInstanceType() {
			return instanceType;
		}

		public void setInstanceType(String instanceType) {
			this.instanceType = instanceType;
		}

		public String getInstanceSpec() {
			return instanceSpec;
		}

		public void setInstanceSpec(String instanceSpec) {
			this.instanceSpec = instanceSpec;
		}

		public String getInstanceName() {
			return instanceName;
		}

		public void setInstanceName(String instanceName) {
			this.instanceName = instanceName;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(String createdTime) {
			this.createdTime = createdTime;
		}

		public String getInternetEgressAddress() {
			return internetEgressAddress;
		}

		public void setInternetEgressAddress(String internetEgressAddress) {
			this.internetEgressAddress = internetEgressAddress;
		}

		public String getVpcEgressAddress() {
			return vpcEgressAddress;
		}

		public void setVpcEgressAddress(String vpcEgressAddress) {
			this.vpcEgressAddress = vpcEgressAddress;
		}

		public String getClassicEgressAddress() {
			return classicEgressAddress;
		}

		public void setClassicEgressAddress(String classicEgressAddress) {
			this.classicEgressAddress = classicEgressAddress;
		}

		public String getExpiredTime() {
			return expiredTime;
		}

		public void setExpiredTime(String expiredTime) {
			this.expiredTime = expiredTime;
		}

		public String getHttpsPolicy() {
			return httpsPolicy;
		}

		public void setHttpsPolicy(String httpsPolicy) {
			this.httpsPolicy = httpsPolicy;
		}
	}

	@Override
	public DescribeInstancesResponse getInstance(UnmarshallerContext context) {
		return	DescribeInstancesResponseUnmarshaller.unmarshall(this, context);
	}
}
