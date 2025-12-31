package org.example.quan_ly_heo.service;

import lombok.RequiredArgsConstructor;
import org.example.quan_ly_heo.entity.Origin;
import org.example.quan_ly_heo.repository.OriginRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OriginService {

    private final OriginRepository originRepository;

    public List<Origin> findAll() {
        return originRepository.findAll();
    }

    public Optional<Origin> findById(Long id) {
        return originRepository.findById(id);
    }

    public Origin save(Origin origin) {
        return originRepository.save(origin);
    }

    public void deleteById(Long id) {
        originRepository.deleteById(id);
    }
}
