package com.skillverse.courseservice.service;

import com.skillverse.courseservice.dto.response.FileUploadResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface MinioService {

    FileUploadResponseDTO uploadFile(
            MultipartFile file);
}