package org.example.soccer_manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.soccer_manager.entity.SoccerPlayer;
import org.example.soccer_manager.service.ISoccerPlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/soccers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SoccerPlayerRestController {
    private final ISoccerPlayerService soccerPlayerService;

    @GetMapping
    public ResponseEntity<Page<SoccerPlayer>> getAllSoccerPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("codePlayer").ascending());
        Page<SoccerPlayer> players = soccerPlayerService.search(null, null, null, null, pageable);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSoccerPlayerById(@PathVariable Long id) {
        SoccerPlayer player = soccerPlayerService.findById(id);
        if (player == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Không tìm thấy cầu thủ với ID: " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSoccerPlayer(@Valid @RequestBody SoccerPlayer soccerPlayer) {
        if (soccerPlayerService.existsByCodePlayer(soccerPlayer.getCodePlayer())) {
            Map<String, String> error = new HashMap<>();
            error.put("codePlayer", "Mã cầu thủ đã tồn tại");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        if (soccerPlayer.getNationalTeam() != null) {
            try {
                soccerPlayerService.validateCodePlayer(soccerPlayer.getCodePlayer(),
                        soccerPlayer.getNationalTeam().getCountryCode());
            } catch (IllegalArgumentException ex) {
                Map<String, String> error = new HashMap<>();
                error.put("codePlayer", ex.getMessage());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
        }

        SoccerPlayer savedPlayer = soccerPlayerService.save(soccerPlayer);
        if (savedPlayer != null) {
            return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Thêm mới cầu thủ thất bại");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSoccerPlayer(@PathVariable Long id,
            @Valid @RequestBody SoccerPlayer soccerPlayer) {
        SoccerPlayer existingPlayer = soccerPlayerService.findById(id);
        if (existingPlayer == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Không tìm thấy cầu thủ với ID: " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        if (soccerPlayerService.existsByCodePlayerAndIdNot(soccerPlayer.getCodePlayer(), id)) {
            Map<String, String> error = new HashMap<>();
            error.put("codePlayer", "Trùng mã với cầu thủ khác");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        if (soccerPlayer.getNationalTeam() != null) {
            try {
                soccerPlayerService.validateCodePlayer(soccerPlayer.getCodePlayer(),
                        soccerPlayer.getNationalTeam().getCountryCode());
            } catch (IllegalArgumentException ex) {
                Map<String, String> error = new HashMap<>();
                error.put("codePlayer", ex.getMessage());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
        }

        soccerPlayer.setId(id);
        SoccerPlayer updatedPlayer = soccerPlayerService.save(soccerPlayer);
        if (updatedPlayer != null) {
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Cập nhật cầu thủ thất bại");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
