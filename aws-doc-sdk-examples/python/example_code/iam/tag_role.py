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
# snippet-start:[iam.python.tag_role.complete]

# Requires boto3 1.9.72
import boto3


# Create IAM client
iam = boto3.client('iam')

role_name='AccountantRole1'

tags=[
    {
        'Key': 'Department',
        'Value': 'Accounting'
    },
    {
        'Key': 'Environment',
        'Value': 'Production'
    }
]

try:
    response = iam.tag_role(
        Tags=tags,
        RoleName=role_name
    )
    print(response)
except Exception as e:
    print(e)
    
# snippet-end:[iam.python.tag_role.complete]
# snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
# snippet-sourcedescription:[tag_role.py demonstrates how to Tag an IAM Role.]
# snippet-keyword:[Python]
# snippet-keyword:[AWS SDK for Python (Boto3)]
# snippet-keyword:[Code Sample]
# snippet-keyword:[AWS Identity and Access Management (IAM)]
# snippet-service:[iam]
# snippet-sourcetype:[full-example]
# snippet-sourcedate:[2019-01-02]
# snippet-sourceauthor:[walkerk1980]
