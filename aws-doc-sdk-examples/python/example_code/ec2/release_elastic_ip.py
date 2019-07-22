# Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
#
# This file is licensed under the Apache License, Version 2.0 (the "License").
# You may not use this file except in compliance with the License. A copy of the
# License is located at
#
# http://aws.amazon.com/apache2.0/
#
# This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
# OF ANY KIND, either express or implied. See the License for the specific
# language governing permissions and limitations under the License.


import boto3
from botocore.exceptions import ClientError


ec2 = boto3.client('ec2')

try:
    response = ec2.release_address(AllocationId='ALLOCATION_ID')
    print('Address released')
except ClientError as e:
    print(e)
 

# snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
# snippet-sourcedescription:[release_elastic_ip.py demonstrates how to release an elastic IP address for an Amazon EC2 instance.]
# snippet-keyword:[Python]
# snippet-keyword:[AWS SDK for Python (Boto3)]
# snippet-keyword:[Code Sample]
# snippet-keyword:[Amazon EC2]
# snippet-service:[ec2]
# snippet-sourcetype:[full-example]
# snippet-sourcedate:[2018-06-25]
# snippet-sourceauthor:[jschwarzwalder (AWS)]

