package com.unipampa.crud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final S3Client s3Client;
    private final String bucket;
    private final String publicBaseUrl;

    public ImageService(
            S3Client s3Client,
            @Value("${aws.s3.images-bucket}") String bucket,
            @Value("${aws.s3.public-base-url}") String publicBaseUrl) {
        this.s3Client = s3Client;
        this.bucket = bucket;
        this.publicBaseUrl = publicBaseUrl.replaceAll("/$", "");
    }

    public List<String> uploadImages(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("Envie ao menos uma imagem.");
        }

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                String key = "accommodations/" + UUID.randomUUID() + "-" +
                        sanitizeFilename(file.getOriginalFilename());
                PutObjectRequest request = PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .contentType(file.getContentType())
                        .build();
                s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
                urls.add(publicBaseUrl + "/" + key);

            } catch (Exception e) {
                throw new RuntimeException("Erro ao fazer upload para S3", e);
            }
        }

        return urls;
    }

    private String sanitizeFilename(String filename) {
        if (filename == null || filename.isBlank()) {
            return "image";
        }
        return filename.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
