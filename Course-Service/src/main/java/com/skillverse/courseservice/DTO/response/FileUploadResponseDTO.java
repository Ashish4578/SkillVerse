package com.skillverse.courseservice.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileUploadResponseDTO {

    private String fileName;

    private String fileUrl;

    private Long fileSize;
}