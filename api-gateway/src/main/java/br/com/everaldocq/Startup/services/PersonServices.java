package br.com.everaldocq.startup.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.everaldocq.startup.controllers.PersonController;
import br.com.everaldocq.startup.data.vo.v1.PersonVO;
import br.com.everaldocq.startup.exceptions.ResourceNotFoundException;
import br.com.everaldocq.startup.mapper.DozerMapper;
import br.com.everaldocq.startup.model.Person;
import br.com.everaldocq.startup.repositories.PersonRepository;
;

@Service
public class PersonServices {

    private static final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");
        
        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class) ;
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO findById(Long id) {

        logger.info("Encontrando uma pessoa!");

        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados para essa ID!"));

        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {

        logger.info("Criando uma pessoa!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {

        logger.info("Atualizando uma pessoa!");

        var entity = repository.findById(person.getKey())
            .orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados para essa ID!"));
        /*repository.findById(person.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados para essa ID!"));*/
        
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;

        //return repository.save(person);
        //return repository.save(entity);
    }

    public void delete(Long id) {

        logger.info("Deletando uma pessoa!");

        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados para essa ID!"));
        repository.delete(entity);
        
    }
    
}