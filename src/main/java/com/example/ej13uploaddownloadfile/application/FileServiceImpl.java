package com.example.ej13uploaddownloadfile.application;

import com.example.ej13uploaddownloadfile.dtos.output.FileOutputDto;
import com.example.ej13uploaddownloadfile.entity.File;
import com.example.ej13uploaddownloadfile.exceptions.FileNotFound;
import com.example.ej13uploaddownloadfile.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileRepository fileRepository;

    Path root;


    @Override
    public void init(String path) {
        try {
            this.root = Paths.get(Objects.isNull(path) ? "src/" : path);
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
    @Override
    public void deleteAll(String path) {
        this.root = Paths.get(Objects.isNull(path) ? "src/" : path);
        FileSystemUtils.deleteRecursively(root.toFile());
        fileRepository.deleteAll();
    }


    @Override
    public FileOutputDto save(MultipartFile file) {
        try {
            System.out.println(root);
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            System.out.println(root);

            File newFile = new File(file.getContentType().split("/")[1], file.getOriginalFilename(), LocalDateTime.now());

            fileRepository.save(newFile);

            return new FileOutputDto(newFile);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource getFileByName(String filename) {
        try {
            File fileObjeto = fileRepository.findByName(filename).orElseThrow(() -> new FileNotFound("File does not exist"));

            Path file = root.resolve(fileObjeto.getName());
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFound("The file does not exist");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Resource getFileById(Long id) {
        try {
            File fileObj = fileRepository.findById(id).orElseThrow(() -> new FileNotFound("File does not exist"));

            Path file = root.resolve(fileObj.getName());
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFound("The file does not exist");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
