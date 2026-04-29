package pd16.Repository;

import lombok.Getter;
import pd16.Model.Game;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GameRepository {
    List<Game> games = new ArrayList<>(List.of(
            Game.of("The Witcher 3", "RPG", new BigDecimal("149.99")),
            Game.of("FIFA 26", "Sports", new BigDecimal("249.99")),
            Game.of("Cyberpunk 2077", "RPG", new BigDecimal("199.99")),
            Game.of("Minecraft", "Sandbox", new BigDecimal("99.99")),
            Game.of("Call of Duty: Modern Warfare", "Shooter", new BigDecimal("299.99"))));

    public void addGame(Game game) {
        games.add(game);
    }
}
