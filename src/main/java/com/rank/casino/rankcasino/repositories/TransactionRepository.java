package com.rank.casino.rankcasino.repositories;

import com.rank.casino.rankcasino.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Transactional
    Boolean existsById(int id);

    @Transactional
    List<TransactionEntity> findAllById(int id);

    @Transactional
    @Query(nativeQuery = true, value = "SELECT * FROM transactions WHERE player_id = :playerId ORDER BY creation_date DESC LIMIT 10")
    List<TransactionEntity> getLastTenTransactions(int playerId);
}
