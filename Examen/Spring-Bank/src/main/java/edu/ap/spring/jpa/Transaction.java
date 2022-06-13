package edu.ap.spring.jpa;

public class Transaction {
    public Wallet sender;
    public Wallet recipient;
    public Double amount;

    public Transaction() {
    }

    public Transaction(Wallet sender, Wallet recipient, Double amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    public Wallet getSender() {
        return this.sender;
    }

    public void setSender(Wallet sender) {
        this.sender = sender;
    }

    public Wallet getRecipient() {
        return this.recipient;
    }

    public void setRecipient(Wallet recipient) {
        this.recipient = recipient;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Transaction sender(Wallet sender) {
        setSender(sender);
        return this;
    }

    public Transaction recipient(Wallet recipient) {
        setRecipient(recipient);
        return this;
    }

    public Transaction amount(Double amount) {
        setAmount(amount);
        return this;
    }
}
