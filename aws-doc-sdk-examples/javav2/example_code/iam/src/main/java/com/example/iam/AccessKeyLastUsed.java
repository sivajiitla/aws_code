//snippet-sourcedescription:[AccessKeyLastUsed.java demonstrates how to display the time that an access key was last used.]
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

// snippet-start:[iam.java2.access_key_last_used.complete]
// snippet-start:[iam.java2.access_key_last_used.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.GetAccessKeyLastUsedRequest;
import software.amazon.awssdk.services.iam.model.GetAccessKeyLastUsedResponse;
// snippet-end:[iam.java2.access_key_last_used.import]

/**
 * Displays the time that an access key was last used
 */
public class AccessKeyLastUsed {
    public static void main(String[] args) {

        final String USAGE =
            "To run this example, supply an access key id\n" +
            "Ex: AccessKeyLastUsed <access-key-id>\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String access_id = args[0];

        // snippet-start:[iam.java2.access_key_last_used.main]
        Region region = Region.AWS_GLOBAL;
        IamClient iam = IamClient.builder().region(region).build();

        GetAccessKeyLastUsedRequest request = GetAccessKeyLastUsedRequest.builder()
            .accessKeyId(access_id).build();

        GetAccessKeyLastUsedResponse response = iam.getAccessKeyLastUsed(request);

        System.out.println("Access key was last used at: " +
                response.accessKeyLastUsed().lastUsedDate());
        // snippet-end:[iam.java2.access_key_last_used.main]
    }
}
 

// snippet-end:[iam.java2.access_key_last_used.complete]
