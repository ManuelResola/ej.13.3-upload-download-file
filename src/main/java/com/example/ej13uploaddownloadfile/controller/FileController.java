package com.example.ej13uploaddownloadfile.controller;

import com.example.ej13uploaddownloadfile.application.FileService;
import com.example.ej13uploaddownloadfile.dtos.output.FileOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    @Autowired
    FileService fileService;


    @GetMapping("/setpath")
    public void setPath(@RequestParam String path){
        fileService.init(path);
    }


    @PostMapping(value = "/upload/{type}")
    public ResponseEntity<FileOutputDto> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String type) {

        if (!file.getContentType().split("/")[1].equals(type))
            throw new RuntimeException("Invalid content type");

        return ResponseEntity.status(HttpStatus.OK).body(fileService.save(file));
    }


    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> getFileById(@PathVariable Long id) {
        Resource file = fileService.getFileById(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    @GetMapping("/file/name/{name}")
    public ResponseEntity<Resource> getFileByName(@PathVariable String name) {
        Resource file = fileService.getFileByName(name);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


}