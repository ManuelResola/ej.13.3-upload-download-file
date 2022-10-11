package com.example.ej13uploaddownloadfile.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String metadata;

    @Column
    String name;

    @Column
    LocalDateTime upload_date;

    public File(String metadata, String name, LocalDateTime upload_date) {
        this.name = name;
        this.metadata = metadata;
        this.upload_date = upload_date;
    }
}
