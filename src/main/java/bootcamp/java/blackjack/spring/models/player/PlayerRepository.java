package bootcamp.java.blackjack.spring.models.player;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
	
	Optional<Player> findPlayerByUsernameAndPassword(String username, String password);
	
}
