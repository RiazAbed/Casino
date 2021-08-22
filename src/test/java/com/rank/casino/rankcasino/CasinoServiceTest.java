package com.rank.casino.rankcasino;


import com.rank.casino.rankcasino.entities.PlayerEntity;
import com.rank.casino.rankcasino.entities.TransactionEntity;
import com.rank.casino.rankcasino.repositories.PlayerRepository;
import com.rank.casino.rankcasino.services.CasinoService;
import com.rank.casino.rankcasino.services.PlayerStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankCasinoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CasinoServiceTest {

    public PlayerEntity playerEntity = new PlayerEntity();

    @Autowired
    private CasinoService casinoService;

    @Autowired
    public PlayerRepository playerRepo;


    @Test
    public void testGetPlayer() {
        assertEquals(playerRepo.findOneById(1).getFirstName(), casinoService.getPlayer(1).getFirstName());
        assertEquals(playerRepo.findOneById(1).getId(), casinoService.getPlayer(1).getId());
        assertEquals(playerRepo.findOneById(1).getLastName(), casinoService.getPlayer(1).getLastName());

        assertNull(casinoService.getPlayer(1234421));
    }


    @Test
    public void testWager() {
        double initialAmount = casinoService.getPlayer(1).getBalance();
        casinoService.transact(1, 1234, 100, "wager");
        assertEquals(casinoService.getPlayer(1).getBalance(), initialAmount - 100, 0);
    }

    @Test
    public void testRepeatedTransaction() {
        double initialAmount = casinoService.getPlayer(1).getBalance();
        casinoService.transact(1, 1234, 100, "wager");
        casinoService.transact(1, 1234, 100, "wager");
        casinoService.transact(1, 1234, 100, "wager");
        casinoService.transact(1, 1234, 100, "wager");
        assertEquals(casinoService.getPlayer(1).getBalance(), initialAmount - 100, 0);

    }

    @Test
    public void testWin() {
        double initialAmount = casinoService.getPlayer(1).getBalance();
        casinoService.transact(1, 1234, 100, "win");
        assertEquals(casinoService.getPlayer(1).getBalance(), initialAmount + 100, 0);
    }

    @Test
    public void testPlayerNotFound() {
        assertEquals(casinoService.transact(52412343, 1234, 100, "wager"), PlayerStatus.NotFound);

    }

    @Test
    public void testPlayerNoFunds() {
        double deduct = casinoService.getPlayer(2).getBalance();
        casinoService.transact(2, 1, deduct, "wager");
        casinoService.transact(2, 2, deduct, "wager");
        assertEquals(casinoService.transact(2, 2, deduct, "wager"), PlayerStatus.NoFunds);

    }

    @Test
    public void testGetLastTransactions() {
        casinoService.transact(3, 1, 12, "wager");
        casinoService.transact(3, 2, 11, "wager");
        casinoService.transact(3, 3, 10, "wager");
        casinoService.transact(3, 4, 9, "wager");
        casinoService.transact(3, 5, 8, "wager");
        casinoService.transact(3, 6, 7, "wager");
        casinoService.transact(3, 7, 12, "win");
        casinoService.transact(3, 8, 13, "win");
        casinoService.transact(3, 9, 14, "win");
        casinoService.transact(3, 10, 15, "win");
        casinoService.transact(3, 11, 16, "win");
        casinoService.transact(3, 12, 17, "win");

        List<TransactionEntity> transactions = casinoService.getLastTenTransactions(3);
        assertEquals(transactions.size(), 10);
        assertEquals(transactions.get(0).getId(), 12);
        assertEquals(transactions.get(9).getId(), 3);

    }

}
