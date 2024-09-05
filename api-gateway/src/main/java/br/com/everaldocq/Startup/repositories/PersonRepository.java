package br.com.everaldocq.startup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.everaldocq.startup.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{ //Tipo do ID do objeto Person no segundo parâmetro
}
