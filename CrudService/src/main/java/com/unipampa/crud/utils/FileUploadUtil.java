package com.unipampa.crud.utils;

import java.io.*;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
  public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) {
    Path uploadPath = Paths.get(uploadDir);

    try (InputStream inputStream = multipartFile.getInputStream()) {
      Files.createDirectories(uploadPath);
      Path filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioe) {
      System.out.println("Could not save image file.");
      ioe.printStackTrace();
    }
  }
}