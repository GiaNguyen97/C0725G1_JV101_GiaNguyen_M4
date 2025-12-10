package org.example.borrowbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRecordDto {
    private Long id;
    private String borrowCode;
    private LocalDateTime borrowedAt;
    private String bookTitle;
}
