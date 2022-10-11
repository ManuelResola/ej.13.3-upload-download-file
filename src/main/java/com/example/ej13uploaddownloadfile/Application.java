package com.example.ej13uploaddownloadfile;

import com.example.ej13uploaddownloadfile.application.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.ej13uploaddownloadfile"})
public class Application implements CommandLineRunner {

    @Autowired
    FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(args.length > 0){
            fileService.deleteAll(args[0]);
            fileService.init(args[0]);
        }
        else{
            System.out.println("No se ha recibido ningún valor");
        }
    }
}
