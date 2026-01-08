package org.example.soccer_manager.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.soccer_manager.entity.SoccerPlayer;
import org.example.soccer_manager.service.ISoccerPlayerService;
import org.example.soccer_manager.uti.PlayerStatusLogWriter;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SoccerPlayerAspect {

    private final ISoccerPlayerService soccerPlayerService;

    // Lưu trạng thái cũ theo từng request
    private static final ThreadLocal<Boolean> OLD_STATUS = new ThreadLocal<>();

    public SoccerPlayerAspect(ISoccerPlayerService soccerPlayerService) {
        this.soccerPlayerService = soccerPlayerService;
    }

    // 🔹 Chạy TRƯỚC controller để lấy trạng thái cũ
    @Before("execution(* org.example.soccer_manager.controller.SoccerPlayerController.registerToCompete(..)) && args(soccerPlayer,..)")
    public void captureOldStatus(SoccerPlayer soccerPlayer) {
        if (soccerPlayer.getId() == null)
            return;
        SoccerPlayer player = soccerPlayerService.findById(soccerPlayer.getId());
        OLD_STATUS.set(player.isPlayerStatus());
    }

    // 🔹 Chạy SAU KHI controller chạy XONG & KHÔNG LỖI
    @AfterReturning(pointcut = "execution(* org.example.soccer_manager.controller.SoccerPlayerController.registerToCompete(..)) && args(soccerPlayer,..)")
    public void auditChangePlayerStatus(SoccerPlayer soccerPlayer) {
        if (soccerPlayer.getId() == null)
            return;
        SoccerPlayer player = soccerPlayerService.findById(soccerPlayer.getId());

        Boolean oldStatus = OLD_STATUS.get();
        boolean newStatus = player.isPlayerStatus();

        if (oldStatus != null && oldStatus != newStatus) {
            PlayerStatusLogWriter.write(
                    player.getCodePlayer(),
                    player.getNamePlayer(),
                    oldStatus,
                    newStatus);
        }

        OLD_STATUS.remove(); // bắt buộc
    }
}
