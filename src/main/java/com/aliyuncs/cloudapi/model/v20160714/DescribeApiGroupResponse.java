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
import com.aliyuncs.cloudapi.transform.v20160714.DescribeApiGroupResponseUnmarshaller;
import com.aliyuncs.transform.UnmarshallerContext;

import java.util.List;

/**
 * @author auto create
 * @version 
 */
public class DescribeApiGroupResponse extends AcsResponse {

	private String requestId;

	private String groupId;

	private String groupName;

	private String subDomain;

	private String description;

	private String createdTime;

	private String modifiedTime;

	private String regionId;

	private String status;

	private String billingStatus;

	private String illegalStatus;

	private Integer trafficLimit;

	private List<DomainItem> customDomains;

	private List<StageInfo> stageItems;

	private String vpcDomain;

	private String instanceId;

	private String httpsPolicy;

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSubDomain() {
		return this.subDomain;
	}

	public void setSubDomain(String subDomain) {
		this.subDomain = subDomain;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillingStatus() {
		return this.billingStatus;
	}

	public void setBillingStatus(String billingStatus) {
		this.billingStatus = billingStatus;
	}

	public String getIllegalStatus() {
		return this.illegalStatus;
	}

	public void setIllegalStatus(String illegalStatus) {
		this.illegalStatus = illegalStatus;
	}

	public Integer getTrafficLimit() {
		return this.trafficLimit;
	}

	public void setTrafficLimit(Integer trafficLimit) {
		this.trafficLimit = trafficLimit;
	}

	public List<DomainItem> getCustomDomains() {
		return this.customDomains;
	}

	public void setCustomDomains(List<DomainItem> customDomains) {
		this.customDomains = customDomains;
	}

	public List<StageInfo> getStageItems() {
		return this.stageItems;
	}

	public void setStageItems(List<StageInfo> stageItems) {
		this.stageItems = stageItems;
	}

	public String getVpcDomain() {
		return vpcDomain;
	}

	public void setVpcDomain(String vpcDomain) {
		this.vpcDomain = vpcDomain;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getHttpsPolicy() {
		return httpsPolicy;
	}

	public void setHttpsPolicy(String httpsPolicy) {
		this.httpsPolicy = httpsPolicy;
	}

	public static class DomainItem {

		private String domainName;

		private String certificateId;

		private String certificateName;

		private String domainCNAMEStatus;

		private String domainBindingStatus;

		private String domainLegalStatus;

		private String domainRemark;

		public String getDomainName() {
			return this.domainName;
		}

		public void setDomainName(String domainName) {
			this.domainName = domainName;
		}

		public String getCertificateId() {
			return this.certificateId;
		}

		public void setCertificateId(String certificateId) {
			this.certificateId = certificateId;
		}

		public String getCertificateName() {
			return this.certificateName;
		}

		public void setCertificateName(String certificateName) {
			this.certificateName = certificateName;
		}

		public String getDomainCNAMEStatus() {
			return this.domainCNAMEStatus;
		}

		public void setDomainCNAMEStatus(String domainCNAMEStatus) {
			this.domainCNAMEStatus = domainCNAMEStatus;
		}

		public String getDomainBindingStatus() {
			return this.domainBindingStatus;
		}

		public void setDomainBindingStatus(String domainBindingStatus) {
			this.domainBindingStatus = domainBindingStatus;
		}

		public String getDomainRemark() {
			return domainRemark;
		}

		public void setDomainRemark(String domainRemark) {
			this.domainRemark = domainRemark;
		}

		public String getDomainLegalStatus() {
			return domainLegalStatus;
		}

		public void setDomainLegalStatus(String domainLegalStatus) {
			this.domainLegalStatus = domainLegalStatus;
		}
	}

	public static class StageInfo {

		private String stageId;

		private String stageName;

		private String description;

		public String getStageId() {
			return this.stageId;
		}

		public void setStageId(String stageId) {
			this.stageId = stageId;
		}

		public String getStageName() {
			return this.stageName;
		}

		public void setStageName(String stageName) {
			this.stageName = stageName;
		}

		public String getDescription() {
			return this.description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

	@Override
	public DescribeApiGroupResponse getInstance(UnmarshallerContext context) {
		return	DescribeApiGroupResponseUnmarshaller.unmarshall(this, context);
	}
}
