//snippet-sourcedescription:[SyncPagination.java demonstrates how to ...]
//snippet-keyword:[SDK for Java 2.0]
//snippet-keyword:[Code Sample]
//snippet-service:[dynamodb]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[]
//snippet-sourceauthor:[soo-aws]
/*
 * Copyright 2011-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://aws.amazon.com/apache2.0
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.dynamodb;
// snippet-start:[dynamodb.java2.sync_pagination.complete]
// snippet-start:[dynamodb.java2.sync_pagination.import]

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;
import software.amazon.awssdk.services.dynamodb.paginators.ListTablesIterable;
// snippet-end:[dynamodb.java2.sync_pagination.import]

// snippet-start:[dynamodb.java2.sync_pagination.main]
public class SyncPagination {

    public static void main(String[] args) {
        
        final String USAGE = "\n" +
                "Usage:\n" +
                "    AsynPagination <type>\n\n" +
                "Where:\n" +
                "    type - the type of pagination. (auto, manual or default) \n\n" +
                "Example:\n" +
                "    AsynPagination auto\n";
        
        if (args.length < 1) {
            System.out.println(USAGE);
            System.exit(1);
        }
        
        String method = args[0];
        
        switch (method.toLowerCase()) {
        case "manual": 
            ManualPagination();
            break;
        case "auto": 
            AutoPagination();
            AutoPaginationWithResume();
            break;
        default: 
            ManualPagination();
            AutoPagination();
            AutoPaginationWithResume();
        }
    }
    
    private static void ManualPagination() {
        System.out.println("running ManualPagination...\n");
        
        final DynamoDbClient client = DynamoDbClient.create();
        ListTablesRequest listTablesRequest = ListTablesRequest.builder().limit(3).build();
        boolean done = false;
        while (!done) {
            ListTablesResponse listTablesResponse = client.listTables(listTablesRequest);
            System.out.println(listTablesResponse.tableNames());

            if (listTablesResponse.lastEvaluatedTableName() == null) {
                done = true;
            }

            listTablesRequest = listTablesRequest.toBuilder()
                                                 .exclusiveStartTableName(listTablesResponse.lastEvaluatedTableName())
                                                 .build();
        }

    }
    
    private static void AutoPagination() {
        System.out.println("running AutoPagination...\n");

        final DynamoDbClient client = DynamoDbClient.create();
        ListTablesRequest listTablesRequest = ListTablesRequest.builder().limit(3).build();

        ListTablesIterable responses = client.listTablesPaginator(listTablesRequest);


        System.out.println("AutoPagination: using for loop");
        for (final ListTablesResponse response : responses) {
            System.out.println(response.tableNames());
        }
        
        // Print the table names using the responses stream

        System.out.println("AutoPagination: using stream");

        responses.stream().forEach(response -> System.out.println(response.tableNames()));

        // Convert the stream of responses to stream of table names, then print the table names

        System.out.println("AutoPagination: using flatmap to get stream of table names");

        responses.stream()
                 .flatMap(response -> response.tableNames().stream())
                 .forEach(System.out::println);
        

        System.out.println("AutoPagination: iterating directly on the table names");

        Iterable<String> tableNames = responses.tableNames();
        tableNames.forEach(System.out::println);
    }
    
    private static void AutoPaginationWithResume() {

        System.out.println("running AutoPagination with resume in case of errors...\n");

        final DynamoDbClient client = DynamoDbClient.create();

        ListTablesRequest listTablesRequest = ListTablesRequest.builder().limit(3).build();
        ListTablesIterable responses = client.listTablesPaginator(listTablesRequest);

        ListTablesResponse lastSuccessfulPage = null;
        try {
            for (ListTablesResponse response : responses) {
                response.tableNames().forEach(System.out::println);
                lastSuccessfulPage = response;
            }
        } catch (Exception exception) {
            if (lastSuccessfulPage != null) {
            	System.out.println(exception.getMessage());
            }
        }
    }
}
// snippet-end:[dynamodb.java2.sync_pagination.main] 
// snippet-end:[dynamodb.java2.sync_pagination.complete]
