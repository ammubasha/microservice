package com.basha.aws.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;


@Service
@Configuration("application")
public class SqsService {
	
	@Value("${sqsurl}")
	private String url;
	
	@Value("${cloud.aws.credentials.accessKey}") String awsAccessKey;
    @Value("${cloud.aws.credentials.secretKey}") String awsSecretKey;
	
	public String getMessage(){
		return "Hello Basha "+url;
	}
	
	public void sendMessage(String message){
		
	                
	                StaticCredentialsProvider credentialsProvider  = new StaticCredentialsProvider(
	                        new BasicAWSCredentials(awsAccessKey, awsSecretKey));

	/*	new ProfileCredentialsProvider();
	        try {
	            credentialsProvider.getCredentials();
	        } catch (Exception e) {
	            throw new AmazonClientException(
	                    "Cannot load the credentials from the credential profiles file. " +
	                    "Please make sure that your credentials file is at the correct " +
	                    "location ",
	                    e);
	        }
	        
	       */
		 AmazonSQSClientBuilder.standard().setCredentials(credentialsProvider);
		
			AmazonSQS sqsclient = AmazonSQSClientBuilder.standard()
					.withCredentials(credentialsProvider)
					.withRegion(Regions.AP_SOUTHEAST_1)
					.build();
	
			sqsclient.sendMessage(url, message);
			
	}

}
