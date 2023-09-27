package bootcamp.java.blackjack.spring.models.hand;

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

import bootcamp.java.blackjack.spring.models.player.Player;
import bootcamp.java.blackjack.spring.models.player.PlayerRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/hands")
public class HandController {
	
	@Autowired
	private HandRepository handRepo;
	@Autowired
	private PlayerRepository playerRepo;
	
	private void updatePlayerWinnings(Hand hand) throws Exception {
		Player player = playerRepo.findById(hand.getPlayer().getId()).get();
	    // If getWinLoss() is a WIN it adds the players wallet and the players total winnings
	    if (hand.getWinLoss().equals("WIN")) {
	        double wallet = player.getWallet() + hand.getAmountWon();
	        double winnings = player.getWinnings() + hand.getAmountWon();
	        player.setWinnings(winnings);
	        player.setWallet(wallet);
	        playerRepo.save(player);
        }
	    // If getWinLoss() is LOSS it subtracts the players wallet and the players total winnings
        else if (hand.getWinLoss().equals("LOSS")) {
        	double wallet = player.getWallet() - hand.getInitialBet();
            double winnings = player.getWinnings() - hand.getInitialBet();
            player.setWinnings(winnings);
            player.setWallet(wallet);
            playerRepo.save(player);
        }
        // CHECKS getWinLoss is either "WIN" or "LOSS" and if it isn't it throws and exception
        else {
        	throw new Exception("Invalid MUST INPUT EITHER 'WIN' OR 'LOSS' ");
        }
    }

	
	@GetMapping
	public ResponseEntity<Iterable<Hand>> getHands() {
		Iterable<Hand> hand = handRepo.findAll();
		return new ResponseEntity<Iterable<Hand>>(hand, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Hand> getHand(@PathVariable int id){
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		
		Optional<Hand> hand = handRepo.findById(id);
		if(hand.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Hand>(hand.get(), HttpStatus.OK); 
		
	}

	@PostMapping 
	public ResponseEntity<Hand> PostHand(@RequestBody Hand hand) throws Exception {
        if (hand.getId() != 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        handRepo.save(hand);
        updatePlayerWinnings(hand);
        return new ResponseEntity<>(hand, HttpStatus.CREATED); 
	    
	}
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}") 
	public ResponseEntity PutHand(@PathVariable int id, @RequestBody Hand hand) throws Exception {
		if(hand.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		handRepo.save(hand);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity DeleteHand(@PathVariable int id) {
		if(id <= 0) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		handRepo.deleteById(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
