package org.example.soccer_manager.repository;

import jakarta.validation.constraints.Pattern;
import org.example.soccer_manager.entity.SoccerPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISoccerPlayerRepository extends JpaRepository<SoccerPlayer, Long> {

  boolean existsByCodePlayer(
      @Pattern(regexp = "^[A-Z]{2}-[0-9]{3}$", message = "Mã cầu thủ phải có định dạng 2 chữ cái viết hoa - 3 số (Ví dụ: GER-001)") String codePlayer);

  boolean existsByCodePlayerAndIdNot(@Pattern(regexp = "^[A-Z]{2}-[0-9]{3}$", message = "Mã cầu thủ phải có định dạng 2 chữ cái viết hoa - 3 số (Ví dụ: VN-001)") String codePlayer, Long id);

  Integer countAllByPlayerStatus(boolean playerStatus);

  List<SoccerPlayer> findAllByPlayerStatus(boolean playerStatus);

  @Query("""
          SELECT s FROM SoccerPlayer s
          WHERE (:name IS NULL OR s.namePlayer LIKE %:name%)
            AND (:dobFrom IS NULL OR s.dayOfBirth >= :dobFrom)
            AND (:dobTo IS NULL OR s.dayOfBirth <= :dobTo)
            AND (:searchPosition IS NULL OR :searchPosition = '' OR s.position = :searchPosition)
      """)
  Page<SoccerPlayer> search(
      @Param("name") String name,
      @Param("dobFrom") LocalDate dobFrom,
      @Param("dobTo") LocalDate dobTo,
      @Param("searchPosition") String searchPosition,
      Pageable pageable);
}

