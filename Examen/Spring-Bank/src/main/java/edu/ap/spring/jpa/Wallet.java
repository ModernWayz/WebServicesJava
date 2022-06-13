package edu.ap.spring.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Double coins;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCoins() {
        return this.coins;
    }

    public void setCoins(Double coins) {
        this.coins = coins;
    }

    public Wallet() {
    }

    public Wallet(String name, Double coins) {
        this.name = name;
        this.coins = coins;
    }

    public Transaction sendCoins(Wallet recipient, Double amount) throws Exception {
		Transaction newTransaction = new Transaction(this, recipient, amount);
				
		return newTransaction;
	}
}