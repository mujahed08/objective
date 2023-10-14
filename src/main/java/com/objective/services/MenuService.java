package com.objective.services;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.objective.payload.AllMenu;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MenuService {


	@CacheEvict(value = "getList", key = "#name")
	public void clearCache(String name) {
		log.info("Clearing cache for {}", name);
		log.info("Cache has been cleared");
	}
	
	@Cacheable(value = "getList")
	public AllMenu getList(String name) {
		
		try {
            InputStream obj = minioClient().getObject(GetObjectArgs
            		.builder()
            		.bucket("simplebucketo")
            		.object(String.format("/%s/%s.json", name, name)).build());
			/*
			 * byte[] content = IOUtils.toByteArray(obj); obj.close();
			 */
            
            ObjectMapper mapper = new ObjectMapper();
            
            AllMenu json = mapper.readValue(obj, AllMenu.class);
            
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		return null;
	}
	
	MinioClient minioClient() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
		MinioClient minioClient = null;
		try {
			minioClient = MinioClient.builder()
              .endpoint("http://localhost:9000")
              //.credentials("minio", "minio_pasword")
              .credentials("9gE3pSHcwfrp0AFc", "hhILVjQ4gPDRltNUEQyAn4aPegOFYvgE")
              .build();

			boolean found =
	          minioClient.bucketExists(BucketExistsArgs.builder().bucket("simplebucketo").build());
			if (!found) {
				// Make a new bucket called 'simplebucketo'.
				minioClient.makeBucket(MakeBucketArgs.builder().bucket("simplebucketo").build());
			} else {
				System.out.println("Bucket 'simplebucketo' already exists.");
			}
		} catch (MinioException e) {
			System.out.println("Error occurred: " + e);
			System.out.println("HTTP trace: " + e.httpTrace());
		}
		return minioClient;
	}
}
