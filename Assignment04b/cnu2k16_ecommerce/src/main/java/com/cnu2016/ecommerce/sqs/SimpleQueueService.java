package com.cnu2016.ecommerce.sqs;

/**
 * Created by vipulj on 11/07/16.
 */

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.cnu2016.ecommerce.pojo.LogPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SimpleQueueService {

    final static Logger LOGGER = LoggerFactory.getLogger(SimpleQueueService.class);
    AmazonSQS sqs = new AmazonSQSClient(new DefaultAWSCredentialsProviderChain());

    public SimpleQueueService() {}

    public void pushToQueue(LogPOJO log) throws Exception {

        Region usEastOne = Region.getRegion(Regions.US_EAST_1);

        sqs.setRegion(usEastOne);

        try {
            String myQueueUrl = sqs.getQueueUrl("cnu2016_vjain_assignment05a").getQueueUrl();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String logJson = objectMapper.writeValueAsString(log);
                sqs.sendMessage(new SendMessageRequest(myQueueUrl, logJson));
            } catch (JsonProcessingException e) {
                LOGGER.error("Json Error", e);
            }
        } catch (AmazonServiceException ase) {
            LOGGER.error("Amazon Service Exception", ase);
        } catch (AmazonClientException ace) {
            LOGGER.error("Amazon Client Exception", ace);
        }
    }
}