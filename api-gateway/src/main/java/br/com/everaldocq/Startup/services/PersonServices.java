package br.com.everaldocq.startup.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.everaldocq.startup.model.Person;;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {

        logger.info("Finding all people!");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id) {

        logger.info("Encontrando uma pessoa!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Everaldo");
        person.setLastName("Quintela");
        person.setAddress("Maceió-AL, Brasil");
        person.setGender("Male");
        return person;
    }

    public Person create(Person person) {

        logger.info("Criando uma pessoa!");
        return person;
    }

    public Person update(Person person) {

        logger.info("Atualizando uma pessoa!");
        return person;
    }

    public void delete(String id) {

        logger.info("Deletando uma pessoa!");
    }

    private Person mockPerson(int i) {

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person Name: " + i);
        person.setLastName("Last Name: " + i);
        person.setAddress("Address in Brasil: " + i);
        person.setGender("Male");
        return person;
    }

}
