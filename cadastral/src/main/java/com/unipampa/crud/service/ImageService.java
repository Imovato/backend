package com.unipampa.crud.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ImageService {

    private final Cloudinary cloudinary;

    public ImageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public List<String> uploadImages(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("Envie ao menos uma imagem.");
        }

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Map result = cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.asMap("folder", "imovato/accommodations")
                );
                urls.add(result.get("secure_url").toString());

            } catch (Exception e) {
                throw new RuntimeException("Erro ao fazer upload para Cloudinary", e);
            }
        }

        return urls;
    }
}
