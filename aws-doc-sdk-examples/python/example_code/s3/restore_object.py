# snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
# snippet-sourcedescription:[restore_object.py demonstrates how to restore an archived S3 Glacier object in an Amazon S3 bucket.]
# snippet-service:[s3]
# snippet-keyword:[Amazon S3]
# snippet-keyword:[Python]
# snippet-keyword:[Code Sample]
# snippet-sourcetype:[full-example]
# snippet-sourcedate:[2019-2-15]
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


def restore_object(bucket_name, object_name, days, retrieval_type='Standard'):
    """Restore an archived S3 Glacier object in an Amazon S3 bucket

    :param bucket_name: string
    :param object_name: string
    :param days: number of days to retain restored object
    :param retrieval_type: 'Standard' | 'Expedited' | 'Bulk'
    :return: True if a request to restore archived object was submitted, otherwise
    False
    """

    # Create request to restore object
    request = {'Days': days,
               'GlacierJobParameters': {'Tier': retrieval_type}}

    # Submit the request
    s3 = boto3.client('s3')
    try:
        s3.restore_object(Bucket=bucket_name, Key=object_name, RestoreRequest=request)
    except ClientError as e:
        # NoSuchBucket, NoSuchKey, or InvalidObjectState error == the object's
        # storage class was not GLACIER
        logging.error(e)
        return False
    return True


def main():
    """Exercise restore_object()"""

    # Assign these values before running the program
    test_bucket_name = 'BUCKET_NAME'
    test_object_name = 'OBJECT_NAME'

    # Set up logging
    logging.basicConfig(level=logging.DEBUG,
                        format='%(levelname)s: %(asctime)s: %(message)s')

    # Restore archived object for two days. Expedite the restoration.
    success = restore_object(test_bucket_name, test_object_name, 2, 'Expedited')
    if success:
        logging.info(f'Submitted request to restore {test_object_name} '
                     f'in {test_bucket_name}')


if __name__ == '__main__':
    main()
