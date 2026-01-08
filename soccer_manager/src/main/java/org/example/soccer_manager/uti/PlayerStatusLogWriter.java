package org.example.soccer_manager.uti;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlayerStatusLogWriter {

    private static final String FILE_PATH = "src/main/resources/static/logs/player-status.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void write(
            String codePlayer,
            String namePlayer,
            boolean oldStatus,
            boolean newStatus) {

        String line = String.format(
                "[%s] Cầu thủ=%s | Tên=%s | Trạng thái: %s → %s",
                LocalDateTime.now().format(FORMATTER),
                codePlayer,
                namePlayer,
                toText(oldStatus),
                toText(newStatus));

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
                Paths.get(FILE_PATH),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            bufferedWriter.write(line);
            bufferedWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String toText(boolean status) {
        return status ? "THI ĐẤU" : "DỰ BỊ";
    }
}