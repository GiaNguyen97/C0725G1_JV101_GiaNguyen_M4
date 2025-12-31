package org.example.quan_ly_heo.service;

import lombok.RequiredArgsConstructor;
import org.example.quan_ly_heo.entity.Pig;
import org.example.quan_ly_heo.repository.PigRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PigService {

    private final PigRepository pigRepository;

        public Page<Pig> search(Boolean sold, String code, Long originId, Pageable pageable) {
            return pigRepository.search(sold, code, originId, pageable);
        }

        public Pig findById(Long id) {
            return pigRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy heo với ID: " + id));
        }

        public Pig save(Pig pig) {
            return pigRepository.save(pig);
        }

        public Pig update(Pig pig) {
            return pigRepository.save(pig);
        }

        public void deleteById(Long id) {
            pigRepository.deleteById(id);
        }
}
