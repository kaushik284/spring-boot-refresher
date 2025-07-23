package learning.kaushik.state_machine_demo.configs;

import learning.kaushik.state_machine_demo.enums.GameGenre;
import learning.kaushik.state_machine_demo.enums.Games;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * Load property game.names into names Map.<br/>
 * Example game.names.broken_arrow=strategy,real_time_tactics<br/> will be
 * loaded into names Map as {Games.broken_arrow: [GameGenre.STRATEGY, GameGenre.REAL_TIME_TACTICS]}
 */
@Configuration
@ConfigurationProperties(prefix = "game")
@Getter
@Setter
public class GameGenreProperties {
    private Map<Games, List<GameGenre>> names;
}
