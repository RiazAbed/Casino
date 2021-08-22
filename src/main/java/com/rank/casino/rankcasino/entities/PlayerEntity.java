package com.rank.casino.rankcasino.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "players")
public class PlayerEntity {

    @Id
    @Column(name = "id", updatable = false)
    int id ;
    @Column(name = "first_name")
    String firstName ;
    @Column(name = "last_name")
    String lastName ;
    @Column(name = "balance")
    double balance ;
}
