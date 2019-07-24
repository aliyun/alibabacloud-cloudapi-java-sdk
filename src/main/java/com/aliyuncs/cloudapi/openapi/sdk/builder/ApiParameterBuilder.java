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

import com.aliyuncs.cloudapi.model.v20160714.DescribeApiResponse;
import com.aliyuncs.cloudapi.openapi.sdk.constant.ParameterLocation;
import com.aliyuncs.cloudapi.openapi.sdk.constant.ParameterType;
import com.aliyuncs.utils.StringUtils;

/**
 * 用于构建参数的Builder
 *
 * @author ailan.gl 2018-09-13
 */
public class ApiParameterBuilder {
	private ApiBuilder parent;

	private DescribeApiResponse.RequestParameter parameter;
	private DescribeApiResponse.ServiceParameter serviceParameter;

	public ApiParameterBuilder(ApiBuilder parent, String name, ParameterLocation location) {
		this.parent = parent;
		parameter = new DescribeApiResponse.RequestParameter();
		parameter.setApiParameterName(name);
		parameter.setLocation(location.openapiValue());
		parameter.setParameterType(ParameterType.STRING.openapiValue());
		serviceParameter = new DescribeApiResponse.ServiceParameter();
		serviceParameter.setServiceParameterName(name);
		serviceParameter.setLocation(location.openapiValue());
	}

	public ApiParameterBuilder(ApiBuilder parent, String name, ParameterLocation location,String serviceName) {
		this.parent = parent;
		parameter = new DescribeApiResponse.RequestParameter();
		parameter.setApiParameterName(name);
		parameter.setLocation(location.openapiValue());
		parameter.setParameterType(ParameterType.STRING.openapiValue());
		serviceParameter = new DescribeApiResponse.ServiceParameter();
		serviceParameter.setServiceParameterName(serviceName);
		serviceParameter.setLocation(location.openapiValue());
	}

	public ApiParameterBuilder backendLocation(ParameterLocation location) {
		serviceParameter.setLocation(location.openapiValue());
		return this;
	}

	public ApiParameterBuilder backendName(String name) {
		serviceParameter.setServiceParameterName(name);
		return this;
	}

	public ApiParameterBuilder required() {
		parameter.setRequired("REQUIRED");
		return this;
	}

	public ApiParameterBuilder defaultValue(String v) {
		parameter.setDefaultValue(v);
		return this;
	}

	public ApiParameterBuilder type(ParameterType type) {
		parameter.setParameterType(type.openapiValue());
		return this;
	}

	public ApiParameterBuilder stringValidator(long minLength, long maxLength, String regex) {
		parameter.setParameterType(ParameterType.STRING.openapiValue());
		if (minLength > 0) {
			parameter.setMinLength(minLength);
		}
		if (maxLength > 0) {
			parameter.setMaxLength(maxLength);
		}
		if (!StringUtils.isEmpty(regex)) {
			parameter.setRegularExpression(regex);
		}
		return this;
	}

	public ApiParameterBuilder minValue(long min) {
		parameter.setMinValue(min);
		return this;
	}

	public ApiParameterBuilder maxValue(long max) {
		parameter.setMaxValue(max);
		return this;
	}

	public ApiBuilder and() {
		parent.addParameter(parameter, serviceParameter);
		return parent;
	}
}
