package org.example.webmusic.service;


import lombok.RequiredArgsConstructor;
import org.example.webmusic.entity.Song;
import org.example.webmusic.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService implements ISongService {

    private final ISongRepository songRepository;

    @Value("${upload.path}")
    private String uploadPath;

    private final String[] ALLOWED_EXT = {".mp3", ".wav", ".ogg", ".m4p"};

    public boolean isValidFile(MultipartFile file) {
        String name = file.getOriginalFilename().toLowerCase();
        for (String ext : ALLOWED_EXT) {
            if (name.endsWith(ext)) return true;
        }
        return false;
    }

    public void saveSong(String name, String artist, String category, MultipartFile file) throws IOException {

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        File outFile = new File(uploadPath + fileName);

        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }

        file.transferTo(outFile);

        Song song = new Song();
        song.setName(name);
        song.setArtist(artist);
        song.setCategory(category);
        song.setFilePath(fileName);

        songRepository.save(song);
    }
    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public Song save(Song song, MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadPath + fileName);
                Files.copy(file.getInputStream(), path);
                song.setFilePath(fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Upload file fail!");
        }
        return songRepository.save(song);
    }

    @Override
    public Song update(Long id, Song newSong, MultipartFile file) {
        Song old = songRepository.findById(id).orElse(null);
        if (old == null) return null;

        old.setName(newSong.getName());
        old.setArtist(newSong.getArtist());
        old.setCategory(newSong.getCategory());

        try {
            if (!file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadPath + fileName);
                Files.copy(file.getInputStream(), path);
                old.setFilePath(fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Upload file fail!");
        }

        return songRepository.save(old);
    }

    @Override
    public void delete(Long id) {
        songRepository.deleteById(id);
    }
}