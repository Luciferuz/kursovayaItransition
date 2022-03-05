package com.antsiferov.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    public void uploadFiles(MultipartFile[] multipartFiles) {
        for (MultipartFile current : multipartFiles) {
            File fileObj = convertToFile(current);
            String filename = fileObj.getName();
            amazonS3.putObject(new PutObjectRequest(bucketName, filename, fileObj));
            fileObj.delete();
        }
    }

    private File convertToFile(MultipartFile multipartFile) {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedFile;
    }

    public byte[] downloadFile(String filename) {
        S3Object s3object = amazonS3.getObject(bucketName, filename);
        S3ObjectInputStream inputstream = s3object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputstream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
