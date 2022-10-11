package com.example.ej13uploaddownloadfile.application;

import com.example.ej13uploaddownloadfile.dtos.output.FileOutputDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public void init(String path);

    public void deleteAll(String path);

    public FileOutputDto save(MultipartFile file);

    public Resource getFileByName(String filename);

    public Resource getFileById(Long id);
}
