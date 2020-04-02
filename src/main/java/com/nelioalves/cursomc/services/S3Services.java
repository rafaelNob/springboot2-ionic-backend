package com.nelioalves.cursomc.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Services {

	private Logger Log = LoggerFactory.getLogger(S3Services.class);

	@Autowired
	private AmazonS3 S3Config;

	@Value("${s3.bucket}")
	private String bucketName;

	public void uploadFile(String localFile) {
		try {
			File file = new File(localFile);
			Log.info("Inicio Upload");
			S3Config.putObject(new PutObjectRequest(bucketName, "teste", file));
			Log.info("fim Upload");
		} catch (AmazonServiceException e) {
			Log.info("AmazonServiceException " + e.getErrorMessage());
		}
	}

}
