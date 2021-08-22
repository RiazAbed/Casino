package com.rank.casino.rankcasino.repositories;

import com.rank.casino.rankcasino.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    @Transactional
    PlayerEntity findOneById(int id);

    @Transactional
    boolean existsById(int id);

}
