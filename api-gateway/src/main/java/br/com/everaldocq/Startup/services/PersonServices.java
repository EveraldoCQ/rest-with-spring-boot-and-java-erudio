package br.com.everaldocq.startup.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.everaldocq.startup.exceptions.ResourceNotFoundException;
import br.com.everaldocq.startup.model.Person;
import br.com.everaldocq.startup.repositories.PersonRepository;;

@Service
public class PersonServices {

    private static final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {

        logger.info("Finding all people!");
        
        return repository.findAll();
    }

    public Person findById(Long id) {

        logger.info("Encontrando uma pessoa!");

        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados para essa ID!"));
    }

    public Person create(Person person) {

        logger.info("Criando uma pessoa!");
        return repository.save(person);
    }

    public Person update(Person person) {

        logger.info("Atualizando uma pessoa!");

        /*var entity = repository.findById(person.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados para essa ID!"));*/
        repository.findById(person.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados para essa ID!"));
        
        /*entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());*/

        return repository.save(person);
        //return repository.save(entity);
    }

    public void delete(Long id) {

        logger.info("Deletando uma pessoa!");

        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados para essa ID!"));
        repository.delete(entity);
        
    }
    
}