package com.nelioalves.cursomc.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
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

	public URI uploadFile(MultipartFile multipartFile) {

		try {
		String fileName = multipartFile.getOriginalFilename();
		InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new RuntimeException("Erro de IO " +e.getMessage());
		}

	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata md = new ObjectMetadata();
			md.setContentType(contentType);
			Log.info("Inicio Upload");
			S3Config.putObject(bucketName, fileName, is, md);
			Log.info("fim Upload");
			return S3Config.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao convertert url para uri"+e.getMessage());
		}

	}

}
