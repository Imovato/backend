package com.unipampa.crud.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadUtilTest {

    @Test
    void testSaveFileSuccess(@TempDir Path tempDir) throws IOException {
        String uploadDir = tempDir.toString();
        String fileName = "test.txt";
        byte[] content = "Hello, World!".getBytes();
        MultipartFile multipartFile = new MockMultipartFile("file", fileName, "text/plain", content);

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        Path filePath = tempDir.resolve(fileName);
        assertTrue(Files.exists(filePath), "O arquivo deve existir após o salvamento.");
        byte[] savedContent = Files.readAllBytes(filePath);
        assertArrayEquals(content, savedContent, "O conteúdo do arquivo salvo deve ser igual ao original.");
    }

    @Test
    void testSaveFileFailure(@TempDir Path tempDir) throws IOException {
        String uploadDir = tempDir.toString();
        String fileName = "error.txt";
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return "error";
            }
            @Override
            public String getOriginalFilename() {
                return fileName;
            }
            @Override
            public String getContentType() {
                return "text/plain";
            }
            @Override
            public boolean isEmpty() {
                return false;
            }
            @Override
            public long getSize() {
                return 0;
            }
            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }
            @Override
            public java.io.InputStream getInputStream() throws IOException {
                throw new IOException("Simulated exception");
            }
            @Override
            public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
            }
        };

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        Path filePath = tempDir.resolve(fileName);
        assertFalse(Files.exists(filePath), "O arquivo não deve ser criado se ocorrer uma exceção durante o salvamento.");
    }
}