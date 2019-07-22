# snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
# snippet-sourcedescription:[get_bucket_acl.py demonstrates how to retrieve the access control list of an Amazon S3 bucket.]
# snippet-service:[s3]
# snippet-keyword:[Amazon S3]
# snippet-keyword:[Python]
# snippet-sourcetype:[full-example]
# snippet-sourcedate:[2019-03-07]
# snippet-sourceauthor:[AWS]

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

import logging
import boto3
from botocore.exceptions import ClientError


def get_bucket_acl(bucket_name):
    """Retrieve the access control list of an Amazon S3 bucket

    :param bucket_name: string
    :return: Dictionary defining the bucket's access control policy consisting
     of owner and grants. If error, return None.
    """

    # Retrieve the bucket ACL
    s3 = boto3.client('s3')
    try:
        response = s3.get_bucket_acl(Bucket=bucket_name)
    except ClientError as e:
        # AllAccessDisabled error == bucket not found
        logging.error(e)
        return None

    # Return both the Owner and Grants keys
    # The Owner and Grants settings together form the Access Control Policy.
    # The Grants alone form the Access Control List.
    return {'Owner': response['Owner'], 'Grants': response['Grants']}


def main():
    """Exercise get_bucket_acl()"""

    # Assign this value before running the program
    test_bucket_name = 'BUCKET_NAME'

    # Set up logging
    logging.basicConfig(level=logging.DEBUG,
                        format='%(levelname)s: %(asctime)s: %(message)s')

    # Retrieve the current bucket ACL
    acl = get_bucket_acl(test_bucket_name)
    if acl is None:
        exit(-1)

    # Output the bucket ACL grantees and permissions
    for grantee in acl['Grants']:
        # The grantee type determines the grantee_identifier
        grantee_type = grantee['Grantee']['Type']
        if grantee_type == 'CanonicalUser':
            grantee_identifier = grantee['Grantee']['DisplayName']
        elif grantee_type == 'AmazonCustomerByEmail':
            grantee_identifier = grantee['Grantee']['EmailAddress']
        elif grantee_type == 'Group':
            grantee_identifier = grantee['Grantee']['URI']
        else:
            grantee_identifier = 'Unknown'
        logging.info(f'Grantee: {grantee_identifier}, '
                     f'Permissions: {grantee["Permission"]}')


if __name__ == '__main__':
    main()
