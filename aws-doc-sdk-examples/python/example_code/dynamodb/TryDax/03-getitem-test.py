# snippet-sourcedescription:[ ]
# snippet-service:[dynamodb]
# snippet-keyword:[Python]
# snippet-keyword:[Amazon DynamoDB]
# snippet-keyword:[Code Sample]
# snippet-keyword:[ ]
# snippet-sourcetype:[full-example]
# snippet-sourcedate:[ ]
# snippet-sourceauthor:[AWS]
# snippet-start:[dynamodb.Python.TryDax.03-getitem-test] 

#
#  Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
#
#  This file is licensed under the Apache License, Version 2.0 (the "License").
#  You may not use this file except in compliance with the License. A copy of
#  the License is located at
# 
#  http://aws.amazon.com/apache2.0/
# 
#  This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
#  CONDITIONS OF ANY KIND, either express or implied. See the License for the
#  specific language governing permissions and limitations under the License.
#
#!/usr/bin/env python
from __future__ import print_function

import os, sys, time
import amazondax
import botocore.session

region = os.environ.get('AWS_DEFAULT_REGION', 'us-west-2')

session = botocore.session.get_session()
dynamodb = session.create_client('dynamodb', region_name=region) # low-level client

table_name = "TryDaxTable"

if len(sys.argv) > 1:
    endpoint = sys.argv[1]
    dax = amazondax.AmazonDaxClient(session, region_name=region, endpoints=[endpoint])
    client = dax
else:
    client = dynamodb

pk = 10
sk = 10
iterations = 50

start = time.time()
for i in range(iterations):
    for ipk in range(1, pk+1):
        for isk in range(1, sk+1):
            params = {
                'TableName': table_name,
                'Key': {
                    "pk": {'N': str(ipk)},
                    "sk": {'N': str(isk)}
                }
            }

            result = client.get_item(**params)
            print('.', end='', file=sys.stdout); sys.stdout.flush()
print()

end = time.time()
print('Total time: {} sec - Avg time: {} sec'.format(end - start, (end-start)/iterations))

# snippet-end:[dynamodb.Python.TryDax.03-getitem-test] 