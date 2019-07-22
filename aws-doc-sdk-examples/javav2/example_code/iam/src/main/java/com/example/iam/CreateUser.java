//snippet-sourcedescription:[CreateUser.java demonstrates how to create an IAM user.]
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
// snippet-start:[iam.java2.create_user.complete]
// snippet-start:[iam.java2.create_user.import]
import software.amazon.awssdk.services.iam.model.CreateUserRequest;
import software.amazon.awssdk.services.iam.model.CreateUserResponse;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
 
// snippet-end:[iam.java2.create_user.import]
/**
 * Creates an IAM user
 */
public class CreateUser {

    public static void main(String[] args) {

        final String USAGE =
            "To run this example, supply a username\n" +
            "Ex: CreateUser <username>\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String username = args[0];

        // snippet-start:[iam.java2.create_user.main]
        Region region = Region.AWS_GLOBAL;
        IamClient iam = IamClient.builder().region(region).build();

        CreateUserRequest request = CreateUserRequest.builder()
            .userName(username).build();

        CreateUserResponse response = iam.createUser(request);

        System.out.println("Successfully created user: " +
                response.user().userName());
        // snippet-end:[iam.java2.create_user.main]
    }
}
 
// snippet-end:[iam.java2.create_user.complete]
