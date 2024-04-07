package com.amida.cloudStorage.repository.impl;

import com.amida.cloudStorage.repository.MinioRepository;
import io.minio.*;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
@Service
public class MinioRepositoryImpl implements MinioRepository {
    private final MinioClient minioClient;
    private final String ROOT_BUCKET = "user-files";
    @Autowired
    public MinioRepositoryImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @PostConstruct
    public void makeRootBucket() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException {
        boolean rootBucketExists = minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(ROOT_BUCKET)
                        .build());
        if (rootBucketExists)
            return;

        minioClient.makeBucket(
                MakeBucketArgs.builder()
                        .bucket(ROOT_BUCKET)
                        .build()
        );
    }


    @Override
    public void putObject(String objectName, InputStream inputStream) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(ROOT_BUCKET)
                        .object(objectName)
                        .stream(inputStream, -1, 10485760)
                        .build()
        );
    }

    @Override
    public InputStream getObject(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(ROOT_BUCKET)
                        .object(objectName)
                        .build()
        );
    }

    @Override
    public void removeObject(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(ROOT_BUCKET)
                        .object(objectName)
                        .build()
        );
    }

    @Override
    public void makeBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.makeBucket(
                MakeBucketArgs.builder()
                        .bucket(ROOT_BUCKET)
                        .build()
        );
    }

    @Override
    public void removeBucket(String bucketName) {

    }

    @Override
    public List<String> getListFiles(String bucketName) {
        return null;
    }

    @Override
    public List<String> searchFiles(String bucketName, String objectName) {
        return null;
    }
}
