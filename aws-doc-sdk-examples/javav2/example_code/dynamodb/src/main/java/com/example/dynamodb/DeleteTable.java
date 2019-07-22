//snippet-sourcedescription:[DeleteTable.java demonstrates how to ...]
//snippet-keyword:[SDK for Java 2.0]
//snippet-keyword:[Code Sample]
//snippet-service:[dynamodb]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[]
//snippet-sourceauthor:[soo-aws]
/*
   Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

   This file is licensed under the Apache License, Version 2.0 (the "License").
   You may not use this file except in compliance with the License. A copy of
   the License is located at

    http://aws.amazon.com/apache2.0/

   This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
   CONDITIONS OF ANY KIND, either express or implied. See the License for the
   specific language governing permissions and limitations under the License.
*/
package com.example.dynamodb;
// snippet-start:[dynamodb.java2.delete_table.complete]
// snippet-start:[dynamodb.java2.delete_table.import]
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
 
// snippet-end:[dynamodb.java2.delete_table.import]
/**
 * Delete a DynamoDB table.
 *
 * Takes the name of the table to delete.
 *
 * **Warning** The named table will actually be deleted!
 *
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
public class DeleteTable
{
    public static void main(String[] args)
    {
        final String USAGE = "\n" +
            "Usage:\n" +
            "    DeleteTable <table>\n\n" +
            "Where:\n" +
            "    table - the table to delete.\n\n" +
            "Example:\n" +
            "    DeleteTable Greetings\n\n" +
            "**Warning** This program will actually delete the table\n" +
            "            that you specify!\n";

        if (args.length < 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String table_name = args[0];
        
        System.out.format("Deleting table %s...\n", table_name);

        // snippet-start:[dynamodb.java2.delete_table.main]
        DynamoDbClient ddb = DynamoDbClient.create();

        DeleteTableRequest request = DeleteTableRequest.builder()
        		.tableName(table_name)
        		.build();

        try {
            ddb.deleteTable(request);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        // snippet-end:[dynamodb.java2.delete_table.main]
        System.out.println("Done!");
    }
}
 
// snippet-end:[dynamodb.java2.delete_table.complete]
