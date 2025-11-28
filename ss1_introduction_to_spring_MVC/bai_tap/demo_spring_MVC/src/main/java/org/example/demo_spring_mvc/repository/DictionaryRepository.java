package org.example.demo_spring_mvc.repository;

import org.springframework.stereotype.Repository;
import org.example.demo_spring_mvc.entity.Word;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DictionaryRepository implements IDictionaryRepository {

    // Dùng ConcurrentHashMap an toàn hơn trong ứng dụng web nhiều luồng
    private final Map<String, Word> map = new ConcurrentHashMap<>();
    public DictionaryRepository() {
        init();  // Tự động chạy khi bean được tạo
    }

    // Dữ liệu mẫu
    private static final String[][] SAMPLE_DATA = {
            {"hello", "xin chào"},
            {"hi", "xin chào"},
            {"book", "quyển sách"},
            {"computer", "máy tính"},
            {"dog", "con chó"},
            {"cat", "con mèo"},
            {"car", "xe hơi"},
            {"coffee", "cà phê"},
            {"school", "trường học"},
            {"teacher", "giáo viên"},
            {"student", "học sinh"},
            {"apple", "quả táo"},
            {"banana", "quả chuối"},
            {"orange", "quả cam"},
            {"love", "yêu thương"},
            {"friend", "bạn bè"},
            {"water", "nước"},
            {"food", "thức ăn"},
            {"good", "tốt"},
            {"bad", "xấu"},
            {"work", "làm việc"},
            {"time", "thời gian"},
            {"day", "ngày"},
            {"night", "đêm"},
            {"morning", "buổi sáng"},
            {"evening", "buổi tối"},
            {"happy", "hạnh phúc"},
            {"sad", "buồn"},
            {"run", "chạy"},
            {"walk", "đi bộ"},
            {"read", "đọc"},
            {"write", "viết"}
    };

    // Chạy sau khi bean tạo xong
    public void init() {
        for (String[] row : SAMPLE_DATA) {
            String key = row[0].toLowerCase();
            map.put(key, new Word(row[0], row[1]));
        }
    }

    @Override
    public Word findByKey(String key) {
        if (key == null || key.isBlank()) return null;
        return map.get(key.toLowerCase());
    }

    @Override
    public Collection<Word> findAll() {
        return Collections.unmodifiableCollection(map.values());
    }

    @Override
    public boolean save(Word w) {
        if (w == null || w.getKey() == null || w.getKey().isBlank()) return false;

        map.put(w.getKey().toLowerCase(), w);
        return true;
    }
}
