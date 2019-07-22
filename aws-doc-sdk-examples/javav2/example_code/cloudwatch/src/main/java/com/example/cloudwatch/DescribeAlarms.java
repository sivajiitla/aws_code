//snippet-sourcedescription:[DescribeAlarms.java demonstrates how to get a information about CloudWatch alarms.]
//snippet-keyword:[SDK for Java 2.0]
//snippet-keyword:[Code Sample]
//snippet-service:[cloudwatch]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[]
//snippet-sourceauthor:[soo-aws]
// snippet-start:[cloudwatch.java2.describe_alarms.complete]
/*
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.example.cloudwatch;

// snippet-start:[cloudwatch.java2.describe_alarms.import]
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.DescribeAlarmsRequest;
import software.amazon.awssdk.services.cloudwatch.model.DescribeAlarmsResponse;
import software.amazon.awssdk.services.cloudwatch.model.MetricAlarm;
// snippet-end:[cloudwatch.java2.describe_alarms.import]

/**
 * Lists all CloudWatch alarms
 */
public class DescribeAlarms {

    public static void main(String[] args) {

        // snippet-start:[cloudwatch.java2.describe_alarms.main]
        CloudWatchClient cw = CloudWatchClient.builder().build();

        boolean done = false;
        String new_token = null;

        while(!done) {
        	DescribeAlarmsResponse response;
        	if (new_token == null) {
        		DescribeAlarmsRequest request = DescribeAlarmsRequest.builder().build();
                response = cw.describeAlarms(request);
        	}
        	else {
        		DescribeAlarmsRequest request = DescribeAlarmsRequest.builder()
        				.nextToken(new_token)
        				.build();
                response = cw.describeAlarms(request);
        	}


            for(MetricAlarm alarm : response.metricAlarms()) {
                System.out.printf("Retrieved alarm %s", alarm.alarmName());
            }

            if(response.nextToken() == null) {
                done = true;
            }
            else {
                new_token = response.nextToken();
            }
        }
        // snippet-end:[cloudwatch.java2.describe_alarms.main]
    }
}
// snippet-end:[cloudwatch.java2.describe_alarms.complete]