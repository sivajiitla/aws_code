<?php
/**
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * This file is licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License. A copy of
 * the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * ABOUT THIS PHP SAMPLE => This sample is part of the SDK for PHP Developer Guide topic at
 * https://docs.aws.amazon.com/AmazonCloudFront/latest/DeveloperGuide/Invalidation.html#invalidation-specifying-objects
 *
 */
// snippet-start:[cloudfront.php.private_distribution.complete]
// snippet-start:[cloudfront.php.private_distribution.import]
require 'vendor/autoload.php';

use Aws\CloudFront\CloudFrontClient;
use Aws\Exception\AwsException;

// snippet-end:[cloudfront.php.private_distribution.import]

/**
 * Get a Signed URL for an Amazon CloudFront Distribution.
 *
 * This code expects that you have AWS credentials set up per:
 * https://docs.aws.amazon.com/sdk-for-php/v3/developer-guide/guide_credentials.html
 */

// snippet-start:[cloudfront.php.private_distribution.main]
// Create a CloudFront Client
$client = new Aws\CloudFront\CloudFrontClient([
    'profile' => 'default',
    'version' => '2014-11-06',
    'region' => 'us-east-2'
]);

// Set up parameter values for the resource
$resourceKey = 'rtmp://example-distribution.cloudfront.net/videos/example.mp4';
$expires = time() + 300;

// Create a signed URL for the resource using the canned policy
$signedUrlCannedPolicy = $client->getSignedUrl([
    'url' => $resourceKey,
    'expires' => $expires,
    'private_key' => '/path/to/your/cloudfront-private-key.pem',
    'key_pair_id' => '<CloudFront key pair id>'
]);

// snippet-end:[cloudfront.php.private_distribution.main]
// snippet-end:[cloudfront.php.private_distribution.complete] 
// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[SignPriveDistribution.php demonstrates how to provide users access to your private content using an Amazon CloudFront Distribution.]
// snippet-keyword:[PHP]
// snippet-keyword:[AWS SDK for PHP v3]
// snippet-keyword:[Code Sample]
// snippet-keyword:[getSignedUrl]
// snippet-keyword:[Amazon CloudFront]
// snippet-service:[cloudfront]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[2018-12-27]
// snippet-sourceauthor:[jschwarzwalder (AWS)]
