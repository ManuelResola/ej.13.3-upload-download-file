package com.example.ej13uploaddownloadfile.dtos.output;

import com.example.ej13uploaddownloadfile.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileOutputDto {
    Long id;
    String metadata;
    String name;
    LocalDateTime upload_date;

    public FileOutputDto(File file){
        setId(file.getId());
        setMetadata(file.getMetadata());
        setName(file.getName());
        setUpload_date(file.getUpload_date());
    }
}
