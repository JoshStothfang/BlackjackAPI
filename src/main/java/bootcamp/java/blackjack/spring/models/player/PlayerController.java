package bootcamp.java.blackjack.spring.models.player;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/api/players")
public class PlayerController {
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Player>> getPlayers() {
		Iterable<Player> Player = playerRepo.findAll();
		return new ResponseEntity<Iterable<Player>>(Player, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Player> getPlayer(@PathVariable int id){
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		
		Optional<Player> player = playerRepo.findById(id);
		if(player.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Player>(player.get(), HttpStatus.OK); 
		
	}
	
	@GetMapping("{username}/{password}")
	public ResponseEntity<Player> PlayerLogin(@PathVariable String username, @PathVariable String password){
		Optional<Player> player = playerRepo.findPlayerByUsernameAndPassword(username, password);
		if(player.isEmpty()) {
			return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Player>(player.get(), HttpStatus.OK);
	}


	
	@PostMapping 
	public ResponseEntity<Player> PostPlayer(@RequestBody Player player) {
	    try {
	        if (player.getId() != 0) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        Player savedPlayer = playerRepo.save(player);
	        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED); 
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the exception for debugging
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}") 
	public ResponseEntity PutPlayer(@PathVariable int id, @RequestBody Player player) {
		if(player.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		playerRepo.save(player);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity DeletePlayer(@PathVariable int id) {
		if(id <= 0) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		playerRepo.deleteById(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
	

