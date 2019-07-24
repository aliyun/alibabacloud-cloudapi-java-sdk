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
 * @version 
 */
public class ModifyLogConfigRequest extends RpcAcsRequest<ModifyLogConfigResponse> {
	
	public ModifyLogConfigRequest() {
		super("CloudAPI", "2016-07-14", "ModifyLogConfig", "apigateway");
	}

	private String slsProject;

	private String slsLogStore;

	private String logType;

	public String getSlsProject() {
		return this.slsProject;
	}

	public void setSlsProject(String slsProject) {
		this.slsProject = slsProject;
		if(slsProject != null){
			putQueryParameter("SlsProject", slsProject);
		}
	}

	public String getSlsLogStore() {
		return this.slsLogStore;
	}

	public void setSlsLogStore(String slsLogStore) {
		this.slsLogStore = slsLogStore;
		if(slsLogStore != null){
			putQueryParameter("SlsLogStore", slsLogStore);
		}
	}

	public String getLogType() {
		return this.logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
		if(logType != null){
			putQueryParameter("LogType", logType);
		}
	}

	@Override
	public Class<ModifyLogConfigResponse> getResponseClass() {
		return ModifyLogConfigResponse.class;
	}

}
