package com.example.ej13uploaddownloadfile.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomError {

    String message;
    Long httpStatus;
    LocalDateTime timeStamtp;
}
