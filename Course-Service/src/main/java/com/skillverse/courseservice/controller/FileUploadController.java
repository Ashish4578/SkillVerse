package com.skillverse.courseservice.controller;

import com.skillverse.courseservice.DTO.response.FileUploadResponseDTO;
import com.skillverse.courseservice.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/courses/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final MinioService minioService;

    @PostMapping(
            value = "/upload",
            consumes =
                    MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileUploadResponseDTO>
    uploadFile(
            @RequestParam("file")
            MultipartFile file) {

        return ResponseEntity.ok(
                minioService.uploadFile(file));
    }
}