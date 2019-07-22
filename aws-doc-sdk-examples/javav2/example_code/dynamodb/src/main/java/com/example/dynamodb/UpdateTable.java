//snippet-sourcedescription:[UpdateTable.java demonstrates how to ...]
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
// snippet-start:[dynamodb.java2.update_table.complete]
// snippet-start:[dynamodb.java2.update_table.import]
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateTableRequest;

import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
 
// snippet-end:[dynamodb.java2.update_table.import]
/**
 * Update a DynamoDB table (change provisioned throughput).
 *
 * Takes the name of the table to update, the read capacity and the write
 * capacity to use.
 *
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
public class UpdateTable
{
    public static void main(String[] args)
    {
        final String USAGE = "\n" +
            "Usage:\n" +
            "    UpdateTable <table> <read> <write>\n\n" +
            "Where:\n" +
            "    table - the table to put the item in.\n" +
            "    read  - the new read capacity of the table.\n" +
            "    write - the new write capacity of the table.\n\n" +
            "Example:\n" +
            "    UpdateTable HelloTable 16 10\n";

        if (args.length < 3) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String table_name = args[0];
Long read_capacity = Long.parseLong(args[1]);
        Long write_capacity = Long.parseLong(args[2]);

        System.out.format(
                "Updating %s with new provisioned throughput values\n",
                table_name);
        System.out.format("Read capacity : %d\n", read_capacity);
        System.out.format("Write capacity : %d\n", write_capacity);

        // snippet-start:[dynamodb.java2.update_table.main]
        ProvisionedThroughput table_throughput = ProvisionedThroughput.builder()
        		.readCapacityUnits(read_capacity)
        		.writeCapacityUnits(write_capacity)
        		.build();

        DynamoDbClient ddb = DynamoDbClient.create();

        UpdateTableRequest request = UpdateTableRequest.builder()
        		.provisionedThroughput(table_throughput)
        		.tableName(table_name)
        		.build();

        try {
            ddb.updateTable(request);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // snippet-end:[dynamodb.java2.update_table.main]
        System.out.println("Done!");
    }
}
 
// snippet-end:[dynamodb.java2.update_table.complete]
