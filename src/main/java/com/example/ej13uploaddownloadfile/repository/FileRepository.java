package com.example.ej13uploaddownloadfile.repository;

import com.example.ej13uploaddownloadfile.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByName(String name);
}
