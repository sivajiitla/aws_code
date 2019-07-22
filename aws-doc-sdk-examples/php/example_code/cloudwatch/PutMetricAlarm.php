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
 *  ABOUT THIS PHP SAMPLE: This sample is part of the SDK for PHP Developer Guide topic at
 * https://docs.aws.amazon.com/sdk-for-php/v3/developer-guide/cw-examples-work-with-alarms.html
 *
 *
 *
 */
// snippet-start:[cloudwatch.php.put_metric_alarm.complete]
// snippet-start:[cloudwatch.php.put_metric_alarm.import]

require 'vendor/autoload.php';

use Aws\CloudWatch\CloudWatchClient; 
use Aws\Exception\AwsException;
// snippet-end:[cloudwatch.php.put_metric_alarm.import]

/**
 * Put Metric Alarm
 *
 * This code expects that you have AWS credentials set up per:
 * https://docs.aws.amazon.com/sdk-for-php/v3/developer-guide/guide_credentials.html
 */
 
// snippet-start:[cloudwatch.php.put_metric_alarm.main]
$client = new Aws\CloudWatch\CloudWatchClient([
    'profile' => 'default',
    'region' => 'us-west-2',
    'version' => '2010-08-01'
]);

try {
    $result = $client->putMetricAlarm(array(
        // AlarmName is required
        'AlarmName' => 'string',
        // MetricName is required
        'MetricName' => 'string',
        // Namespace is required
        'Namespace' => 'string',
        // Statistic is required
        //string: SampleCount | Average | Sum | Minimum | Maximum
        'Statistic' => 'string',
        // Period is required
        'Period' => integer,
        'Unit' => 'Count/Second',
        // EvaluationPeriods is required
        'EvaluationPeriods' => integer,
        // Threshold is required
        'Threshold' => integer,
        // ComparisonOperator is required
        // string: GreaterThanOrEqualToThreshold | GreaterThanThreshold | LessThanThreshold | LessThanOrEqualToThreshold
        'ComparisonOperator' => 'string',
    ));
    var_dump($result);
} catch (AwsException $e) {
    // output error message if fails
    error_log($e->getMessage());
}
 
 
// snippet-end:[cloudwatch.php.put_metric_alarm.main]
// snippet-end:[cloudwatch.php.put_metric_alarm.complete]
// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[PutMetricAlarm.php demonstrates how to create or update an alarm and associate it with the specified metric.]
// snippet-keyword:[PHP]
// snippet-keyword:[AWS SDK for PHP v3]
// snippet-keyword:[Code Sample]
// snippet-keyword:[Amazon Cloudwatch]
// snippet-service:[cloudwatch]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[2018-12-27]
// snippet-sourceauthor:[jschwarzwalder (AWS)]

