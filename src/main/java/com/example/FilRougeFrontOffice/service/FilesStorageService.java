package com.example.FilRougeFrontOffice.service;


import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FilesStorageService {

    public void init();

    public void save(MultipartFile file, UUID uuidName, String mimetype);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
