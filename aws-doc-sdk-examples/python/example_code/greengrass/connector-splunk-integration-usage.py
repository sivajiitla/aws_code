# Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License"). You
# may not use this file except in compliance with the License. A copy of
# the License is located at
#
# http://aws.amazon.com/apache2.0/
#
# or in the "license" file accompanying this file. This file is
# distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
# ANY KIND, either express or implied. See the License for the specific
# language governing permissions and limitations under the License.
#
# This sample is used in the AWS IoT Greengrass Developer Guide: 
# https://docs.aws.amazon.com/greengrass/latest/developerguide/splunk-connector.html
#
# snippet-start:[greengrass.python.connector-splunk-integration-usage.complete]
import greengrasssdk
import time
import json

iot_client = greengrasssdk.client('iot-data')
send_topic = 'splunk/logs/put'

def create_request_with_all_fields():
    return {
        "request": {
            "event": "Access log test message."
        },
        "id" : "req_123"
    }

def publish_basic_message():
    messageToPublish = create_request_with_all_fields()
    print "Message To Publish: ", messageToPublish
    iot_client.publish(topic=send_topic,
        payload=json.dumps(messageToPublish))

publish_basic_message()

def function_handler(event, context):
    return
# snippet-end:[greengrass.python.connector-splunk-integration-usage.complete]
# snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
# snippet-sourcedescription:[Sends an input message to the Splunk Integration connector.]
# snippet-keyword:[Python]
# snippet-keyword:[Code Sample]
# snippet-keyword:[AWS IoT Greengrass]
# snippet-keyword:[AWS IoT Greengrass Core SDK]
# snippet-keyword:[iot-data client]
# snippet-keyword:[publish]
# snippet-keyword:[Greengrass connector]
# snippet-service:[greengrass]
# snippet-sourcetype:[full-example]
# snippet-sourcedate:[2019-01-02]
# snippet-sourceauthor:[AWS]