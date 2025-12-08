package org.example.webmusic.service;

import org.example.webmusic.entity.Song;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findById(Long id);
    Song save(Song song, MultipartFile file);
    Song update(Long id, Song newSong, MultipartFile file);
    void delete(Long id);
}
