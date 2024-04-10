package com.amida.cloudStorage.service;

import com.amida.cloudStorage.model.User;
import com.amida.cloudStorage.repository.impl.MinioRepositoryImpl;
import io.minio.errors.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioService {
    private MinioRepositoryImpl minioRepository;

    public MinioService(MinioRepositoryImpl minioRepository) {
        this.minioRepository = minioRepository;
    }

    public InputStream downloadFile(User user, String object) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioRepository.getObject(object);
    }

    public void uploadFile(String fileName, InputStream inputStream) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioRepository.putObject(fileName, inputStream);
    }
}
