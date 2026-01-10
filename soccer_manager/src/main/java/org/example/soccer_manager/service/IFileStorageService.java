package org.example.soccer_manager.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {

    String saveFile(MultipartFile file, String subFolder);
}
