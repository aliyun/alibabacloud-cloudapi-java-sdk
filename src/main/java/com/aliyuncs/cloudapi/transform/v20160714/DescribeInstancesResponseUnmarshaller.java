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
package com.aliyuncs.cloudapi.transform.v20160714;

import com.aliyuncs.cloudapi.model.v20160714.DescribeHistoryApisResponse;
import com.aliyuncs.cloudapi.model.v20160714.DescribeHistoryApisResponse.ApiHisItem;
import com.aliyuncs.cloudapi.model.v20160714.DescribeHistoryApisResponse.ApiHisItem.Status;
import com.aliyuncs.cloudapi.model.v20160714.DescribeInstancesResponse;
import com.aliyuncs.transform.UnmarshallerContext;

import java.util.ArrayList;
import java.util.List;

public class DescribeInstancesResponseUnmarshaller {

    public static DescribeInstancesResponse unmarshall(DescribeInstancesResponse describeInstancesResponse,
                                                       UnmarshallerContext context) {

        describeInstancesResponse.setRequestId(context.stringValue("DescribeInstancesResponse.RequestId"));
        describeInstancesResponse.setTotalCount(context.integerValue("DescribeInstancesResponse.TotalCount"));
        describeInstancesResponse.setPageSize(context.integerValue("DescribeInstancesResponse.PageSize"));
        describeInstancesResponse.setPageNumber(context.integerValue("DescribeInstancesResponse.PageNumber"));

        List<DescribeInstancesResponse.InstanceAttribute> instanceAttributes
            = new ArrayList<DescribeInstancesResponse.InstanceAttribute>();
        for (int i = 0; i < context.lengthValue("DescribeInstancesResponse.Instances.Length"); i++) {
            DescribeInstancesResponse.InstanceAttribute instanceAttribute = new DescribeInstancesResponse
                .InstanceAttribute();
            instanceAttribute.setInstanceId(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].InstanceId"));
            instanceAttribute.setInstanceName(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].InstanceName"));
            instanceAttribute.setInstanceType(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].InstanceType"));
            instanceAttribute.setInstanceSpec(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].InstanceSpec"));
            instanceAttribute.setClassicEgressAddress(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].ClassicEgressAddress"));
            instanceAttribute.setInternetEgressAddress(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].InternetEgressAddress"));
            instanceAttribute.setVpcEgressAddress(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].VpcEgressAddress"));
            instanceAttribute.setCreatedTime(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].CreatedTime"));
            instanceAttribute.setExpiredTime(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].ExpiredTime"));
            instanceAttribute.setStatus(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].Status"));
            instanceAttribute.setHttpsPolicy(context.stringValue("DescribeInstancesResponse.Instances[" + i + "].HttpsPolicy"));

            instanceAttributes.add(instanceAttribute);
        }
        describeInstancesResponse.setInstances(instanceAttributes);

        return describeInstancesResponse;
    }
}