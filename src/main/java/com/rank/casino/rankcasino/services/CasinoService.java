package com.rank.casino.rankcasino.services;

import com.rank.casino.rankcasino.entities.PlayerEntity;
import com.rank.casino.rankcasino.entities.TransactionEntity;
import com.rank.casino.rankcasino.repositories.PlayerRepository;
import com.rank.casino.rankcasino.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CasinoService {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public PlayerEntity getPlayer(int playerId) {
        return playerRepository.findOneById(playerId);
    }

    public PlayerStatus transact(int playerId, int transactionId, double amount, String type) {
        PlayerStatus playerStatus = validatePlayer(playerId);

        if (playerStatus.equals(PlayerStatus.Good)) {
            if (!transactionRepository.existsById(transactionId)) {
                updatePlayer(playerId, transactionId, amount, type);
            }
            return playerStatus;
        } else {
            return playerStatus;
        }
    }

    public List<TransactionEntity> getLastTenTransactions(int playerid) {
        return transactionRepository.getLastTenTransactions(playerid);
    }

    private PlayerStatus validatePlayer(int playerId) {

        if (!playerRepository.existsById(playerId)) {
            return PlayerStatus.NotFound;
        } else if (playerRepository.findOneById(playerId).getBalance() < 0) {
            return PlayerStatus.NoFunds;
        } else {
            return PlayerStatus.Good;
        }
    }

    private void updatePlayer(int playerId, int transactionId, double amount, String transactionType) {
        TransactionEntity transactionEntity = new TransactionEntity(transactionId, playerId, transactionType, amount);
        transactionRepository.save(transactionEntity);
        PlayerEntity playerToUpddate = playerRepository.findOneById(playerId);
        if (transactionType.equals("wager")) {
            playerToUpddate.setBalance(playerToUpddate.getBalance() - amount);
        } else if (transactionType.equals("win")) {
            playerToUpddate.setBalance(playerToUpddate.getBalance() + amount);
        }
        playerRepository.save(playerToUpddate);
    }


}
