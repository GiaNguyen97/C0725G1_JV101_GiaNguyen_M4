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
    private final org.example.soccer_manager.service.INationalTeamService nationalTeamService;
    private final org.example.soccer_manager.service.IFileStorageService fileStorageService;

    @GetMapping
    public ResponseEntity<Page<SoccerPlayer>> getAllSoccerPlayers(@RequestParam(required = false) String name, @RequestParam(required = false) String position, @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate dobFrom, @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate dobTo, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("codePlayer").ascending());
        Page<SoccerPlayer> players = soccerPlayerService.search(name, dobFrom, dobTo, position, pageable);
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

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createSoccerPlayer(@Valid @ModelAttribute SoccerPlayer soccerPlayer, @RequestParam(value = "imageFile", required = false) org.springframework.web.multipart.MultipartFile imageFile) {

        if (soccerPlayerService.existsByCodePlayer(soccerPlayer.getCodePlayer())) {
            Map<String, String> error = new HashMap<>();
            error.put("codePlayer", "Mã cầu thủ đã tồn tại");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        if (soccerPlayer.getNationalTeam() != null) {
            var team = nationalTeamService.findById(soccerPlayer.getNationalTeam().getId());
            if (team != null) {
                try {
                    soccerPlayerService.validateCodePlayer(soccerPlayer.getCodePlayer(), team.getCountryCode());
                } catch (IllegalArgumentException ex) {
                    Map<String, String> error = new HashMap<>();
                    error.put("codePlayer", ex.getMessage());
                    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
                }
            }
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = fileStorageService.saveFile(imageFile, "players");
            soccerPlayer.setUrlImage(imagePath);
        }

        boolean isSaved = soccerPlayerService.save(soccerPlayer);

        if (isSaved) {
            return new ResponseEntity<>("Thêm mới cầu thủ thành công", HttpStatus.CREATED);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Thêm mới cầu thủ thất bại");

            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSoccerPlayer(@PathVariable Long id, @Valid @RequestBody SoccerPlayer soccerPlayer) {
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
                soccerPlayerService.validateCodePlayer(soccerPlayer.getCodePlayer(), soccerPlayer.getNationalTeam().getCountryCode());
            } catch (IllegalArgumentException ex) {
                Map<String, String> error = new HashMap<>();
                error.put("codePlayer", ex.getMessage());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
        }

        soccerPlayer.setId(id);
        boolean isUpdated = soccerPlayerService.save(soccerPlayer);

        if (isUpdated) {
            return new ResponseEntity<>("Cập nhật cầu thủ thành công", HttpStatus.OK);
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
