package com.example.FilRougeFrontOffice.service;


import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FilesStorageService {

    void save(MultipartFile file, UUID uuidName, String mimetype);

    Resource load(String filename);

    boolean delete(String filename);
}
