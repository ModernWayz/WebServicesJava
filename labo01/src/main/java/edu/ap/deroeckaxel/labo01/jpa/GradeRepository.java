package edu.ap.deroeckaxel.labo01.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {
    //public Grade findByFirstAndLastName(String firstName, String lastName);
}
