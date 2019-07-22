//snippet-sourcedescription:[ListUsers.java demonstrates how to list existing users in the specified User Pool.]
//snippet-keyword:[Java]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Cognito]
//snippet-service:[cognito]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[2019-06-22]
//snippet-sourceauthor:[jschwarzwalder AWS]
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
//snippet-start:[cognito.java2.ListUsers.complete]

package com.example.cognito;
//snippet-start:[cognito.java2.ListUsers.import]

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;
//snippet-end:[cognito.java2.ListUsers.import]

public class ListUsers {

    public static void main(String[] args) {
        final String USAGE = "\n" +
                "Usage:\n" +
                "    ListUsers <user_pool_id> \n\n" +
                "Where:\n" +
                "    user_pool_id - The id given your user pool when created.\n\n" +
                "Example:\n" +
                "    ListUsers us-east-2_P0oL1D\n";

        if (args.length < 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String user_pool_id = args[0];
        //snippet-start:[cognito.java2.ListUsers.main]
        CognitoIdentityProviderClient cognitoclient = CognitoIdentityProviderClient.builder().region(Region.US_EAST_1).build();

        ListUsersResponse response = cognitoclient.listUsers(ListUsersRequest.builder()
                .userPoolId(user_pool_id)
                .build());

        for(UserType user : response.users()) {
            System.out.println("User " + user.username() + " Status " + user.userStatus() + " Created " + user.userCreateDate() );
        }
        //snippet-end:[cognito.java2.ListUsers.main]
    }
}
//snippet-end:[cognito.java2.ListUsers.complete]