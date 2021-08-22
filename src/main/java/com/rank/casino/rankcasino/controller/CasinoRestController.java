package com.rank.casino.rankcasino.controller;

import com.rank.casino.rankcasino.controller.models.TransactionsRequest;
import com.rank.casino.rankcasino.entities.PlayerEntity;
import com.rank.casino.rankcasino.entities.TransactionEntity;
import com.rank.casino.rankcasino.repositories.PlayerRepository;
import com.rank.casino.rankcasino.repositories.TransactionRepository;
import com.rank.casino.rankcasino.services.CasinoService;
import com.rank.casino.rankcasino.services.PlayerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/casino")
public class CasinoRestController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private CasinoService casinoService;


    @RequestMapping(path = "/balance/{playerId}", method = RequestMethod.GET)
    public ResponseEntity<Double> getBalance(@PathVariable int playerId) {
        return ResponseEntity.ok().body(casinoService.getPlayer(playerId).getBalance());
    }

    @RequestMapping(path = "/wager/{playerId}/{transactionId}/{amount}", method = RequestMethod.POST)
    public ResponseEntity<String> wageringTransaction(@PathVariable int playerId, @PathVariable int transactionId, @PathVariable double amount) {

        PlayerStatus playerStatus = casinoService.transact(playerId, transactionId, amount, "wager");
        if (playerStatus.equals(PlayerStatus.Good)) {
            return ResponseEntity.ok().body("Transaction saved");
        } else if (playerStatus.equals(PlayerStatus.NoFunds)) {
            return new ResponseEntity<>("", HttpStatus.I_AM_A_TEAPOT);
        } else {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/win/{playerId}/{transactionId}/{amount}", method = RequestMethod.POST)
    public ResponseEntity<String> winningTransaction(@PathVariable int playerId, @PathVariable int transactionId, @PathVariable double amount) {

        PlayerStatus playerStatus = casinoService.transact(playerId, transactionId, amount, "win");
        if (playerStatus.equals(PlayerStatus.Good)) {
            return ResponseEntity.ok().body("Transaction saved");
        } else if (playerStatus.equals(PlayerStatus.NoFunds)) {
            return new ResponseEntity<>("", HttpStatus.I_AM_A_TEAPOT);
        } else {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/GetPlayerTransactions", method = RequestMethod.POST)
    public ResponseEntity<List<TransactionEntity>> getLastTenTransactinos(@RequestBody TransactionsRequest transactionsRequest) {

        if (transactionsRequest.getPassword().equals("swordfish")) {
            List<TransactionEntity> some = casinoService.getLastTenTransactions(transactionsRequest.getPlayerID());

            return ResponseEntity.ok(some);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
