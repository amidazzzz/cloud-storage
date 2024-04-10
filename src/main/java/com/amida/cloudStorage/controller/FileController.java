package com.amida.cloudStorage.controller;

import com.amida.cloudStorage.repository.MinioRepository;
import com.amida.cloudStorage.repository.impl.MinioRepositoryImpl;
import io.minio.errors.*;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FileController {

    private final MinioRepositoryImpl minioRepository;

    @Autowired
    public FileController(MinioRepositoryImpl minioRepository) {
        this.minioRepository = minioRepository;
    }

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException, ServerException,
            InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        if (file.isEmpty()) {
            System.out.println("Файл не был выбран");
        } else {
            try {
                InputStream inputStream = file.getInputStream();
                minioRepository.putObject(file.getOriginalFilename(), inputStream);
                System.out.println("Файл успешно загружен");
            } catch (IOException e) {
                System.out.println("Ошибка при получении потока ввода из файла: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка при загрузке файла: " + e.getMessage());
            }
        }
        return "redirect:/files/list";
    }

    @GetMapping("/download/{fileName}")
    public String downloadFile(@PathVariable("fileName") String fileName, Model model) throws IOException, ServerException,
            InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        InputStream fileStream = minioRepository.getObject(fileName);
        model.addAttribute("file", fileStream);
        System.out.println(fileStream);

        return "redirect:/files/list";
    }

    @GetMapping("/list")
    public String listFiles(Model model) throws IOException, ServerException, InsufficientDataException,
            ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException,
            XmlParserException, InternalException {
        List<String> files = minioRepository.getListFiles("user-files");
        model.addAttribute("files", files);
        System.out.println(files);
        return "file-list";
    }
}
