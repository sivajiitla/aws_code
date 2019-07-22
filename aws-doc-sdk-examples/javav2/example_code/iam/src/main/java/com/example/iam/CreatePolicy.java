//snippet-sourcedescription:[CreatePolicy.java demonstrates how to create a fixed policy with a provided policy name.]
//snippet-keyword:[SDK for Java 2.0]
//snippet-keyword:[Code Sample]
//snippet-service:[iam]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[]
//snippet-sourceauthor:[soo-aws]
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
package com.example.iam;
// snippet-start:[iam.java2.create_policy.complete]
// snippet-start:[iam.java2.create_policy.import]
import software.amazon.awssdk.services.iam.model.CreatePolicyRequest;
import software.amazon.awssdk.services.iam.model.CreatePolicyResponse;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
 
// snippet-end:[iam.java2.create_policy.import]
/**
 * Creates a fixed policy with a provided policy name.
 */
public class CreatePolicy {

    // snippet-start:[iam.java2.create_policy.policy_document]
    public static final String POLICY_DOCUMENT =
        "{" +
        "  \"Version\": \"2012-10-17\"," +
        "  \"Statement\": [" +
        "    {" +
        "        \"Effect\": \"Allow\"," +
        "        \"Action\": \"logs:CreateLogGroup\"," +
        "        \"Resource\": \"%s\"" +
        "    }," +
        "    {" +
        "        \"Effect\": \"Allow\"," +
        "        \"Action\": [" +
        "            \"dynamodb:DeleteItem\"," +
        "            \"dynamodb:GetItem\"," +
        "            \"dynamodb:PutItem\"," +
        "            \"dynamodb:Scan\"," +
        "            \"dynamodb:UpdateItem\"" +
        "       ]," +
        "       \"Resource\": \"RESOURCE_ARN\"" +
        "    }" +
        "   ]" +
        "}";
    // snippet-end:[iam.java2.create_policy.policy_document]

    public static void main(String[] args) {

        final String USAGE =
            "To run this example, supply a policy name\n" +
            "Ex: CreatePolicy <policy-name>\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String policy_name = args[0];

        // snippet-start:[iam.java2.create_policy.main]
        Region region = Region.AWS_GLOBAL;
        IamClient iam = IamClient.builder().region(region).build();

        CreatePolicyRequest request = CreatePolicyRequest.builder()
            .policyName(policy_name)
            .policyDocument(POLICY_DOCUMENT).build();

        CreatePolicyResponse response = iam.createPolicy(request);

        System.out.println("Successfully created policy: " +
                response.policy().policyName());
        // snippet-end:[iam.java2.create_policy.main]
    }
}
 
// snippet-end:[iam.java2.create_policy.complete]
