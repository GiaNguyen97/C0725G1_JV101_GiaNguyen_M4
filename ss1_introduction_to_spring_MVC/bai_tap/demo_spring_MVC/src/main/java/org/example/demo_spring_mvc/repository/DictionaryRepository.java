package org.example.demo_spring_mvc.repository;

import org.springframework.stereotype.Repository;
import org.example.demo_spring_mvc.entity.Word;


import java.util.*;

@Repository
public class DictionaryRepository implements IDictionaryRepository{
    private final Map<String, Word> map = new HashMap<>();


    public void init() {
        // Khởi tạo dữ liệu mẫu (gọn, dễ mở rộng). Thêm/ sửa ở đây hoặc load từ file.
        String[][] data = {
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
                {"write", "viết"},
                // ... bạn có thể thêm tiếp
        };

        for (String[] r : data) {
            map.put(r[0].toLowerCase(), new Word(r[0], r[1]));
        }
    }

    @Override
    public Word findByKey(String key) {
        if (key == null) return null;
        return map.get(key.toLowerCase());
    }

    @Override
    public Collection<Word> findAll() {
        return Collections.unmodifiableCollection(map.values());
    }

    @Override
    public boolean save(Word w) {
        if (w == null || w.getKey() == null) {
            return false;
        }
        map.put(w.getKey().toLowerCase(), w);
        return true;
    }
}