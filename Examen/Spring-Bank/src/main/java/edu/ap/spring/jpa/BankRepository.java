package edu.ap.spring.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends CrudRepository<Wallet, Long> {
    public Wallet findByName(String name);
}
