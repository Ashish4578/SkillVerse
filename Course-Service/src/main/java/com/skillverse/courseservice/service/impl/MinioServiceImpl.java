package com.skillverse.courseservice.service.impl;

import com.skillverse.courseservice.dto.response.FileUploadResponseDTO;
import com.skillverse.courseservice.service.MinioService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Override
    public FileUploadResponseDTO uploadFile( MultipartFile file) {

        try {

            String fileName = UUID.randomUUID()
                            + "-"
                            + file.getOriginalFilename();

            minioClient.putObject( PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(
                                    file.getInputStream(),
                                    file.getSize(),
                                    -1)
                            .contentType(
                                    file.getContentType())
                            .build());

            String fileUrl ="http://localhost:9000/"
                            + bucketName
                            + "/"
                            + fileName;

            return FileUploadResponseDTO
                    .builder()
                    .fileName(fileName)
                    .fileUrl(fileUrl)
                    .fileSize(file.getSize())
                    .build();

        } catch (Exception ex) {
            throw new RuntimeException(
                    "Failed to upload file",
                    ex);
        }
    }
}