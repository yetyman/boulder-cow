package bouldercow.flow;

import bouldercow.board.SharedBoard;
import bouldercow.model.GameState;
import bouldercow.player.Player;
import bouldercow.playerboard.PlayerArea;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "bouldercow")
@EnableScheduling
public class BouldercowApp {


    public static void main(String[] args) {
        SpringApplication.run(BouldercowApp.class, args);
    }
}
