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

import boto3

# Create IAM client
sts_default_provider_chain = boto3.client('sts')

print('Default Provider Identity: : ' + sts_default_provider_chain.get_caller_identity()['Arn'])

role_to_assume_arn='arn:aws:iam::123456789012:role/roleName'
role_session_name='test_session'

response=sts_default_provider_chain.assume_role(
    RoleArn=role_to_assume_arn,
    RoleSessionName=role_session_name
)

creds=response['Credentials']

sts_assumed_role = boto3.client('sts',
    aws_access_key_id=creds['AccessKeyId'],
    aws_secret_access_key=creds['SecretAccessKey'],
    aws_session_token=creds['SessionToken'],
)

print('AssumedRole Identity: ' + sts_assumed_role.get_caller_identity()['Arn'])

 

# snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
# snippet-sourcedescription:[assumerole.py demonstrates how to retrieve an assumed role that you can use for cross-account or federation access to an AWS resource.]
# snippet-keyword:[Python]
# snippet-keyword:[AWS SDK for Python (Boto3)]
# snippet-keyword:[Code Sample]
# snippet-keyword:[AWS Security Token Service (STS)]
# snippet-service:[sts]
# snippet-sourcetype:[full-example]
# snippet-sourcedate:[2018-09-17]
# snippet-sourceauthor:[walkerk1980]

