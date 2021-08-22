package com.rank.casino.rankcasino.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "player_id")
    private int player_id;
    @Column(name = "type")
    private String type;
    @Column(name = "amount")
    private double amount;
    @Column(name = "creation_date ", updatable = false)
    private Date creationDate;

    public TransactionEntity() {
    }

    public TransactionEntity(int id, int player_id, String type, double amount) {
        this.id = id;
        this.player_id = player_id;
        this.type = type;
        this.amount = amount;
        this.creationDate = new Date();
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", player_id=" + player_id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                '}';
    }
}
