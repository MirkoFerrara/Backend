package com.eyproject.myproject.classes.service;

import com.eyproject.myproject.classes.exception.MyException;
import com.eyproject.myproject.classes.dto.PersonaDTO;
import com.eyproject.myproject.classes.pojo.PersonaPojo;
import com.eyproject.myproject.classes.utils.Utils;
import com.eyproject.myproject.interfaces.iService.iPersonaService;
import com.eyproject.myproject.interfaces.repository.iPersonaRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements iPersonaService {

    @Autowired
    private iPersonaRepo personaRepo;

    @Override
    @Transactional(rollbackOn = {MyException.class , DataIntegrityViolationException.class})
    public void create(PersonaDTO personaDTO) throws MyException {
        try{
            Utils.validationPersonaCheck(personaDTO);
            PersonaPojo personaPojo = new PersonaPojo();
            personaPojo.setCodiceFiscale(personaDTO.getCodiceFiscale());
            personaPojo.setCognome(personaDTO.getCognome());
            personaPojo.setNome(personaDTO.getNome());
            personaPojo.setDataNascita(personaDTO.getDataNascita());
            personaRepo.save(personaPojo);
        } catch (MyException e){
            throw new MyException("I dati inseriti non sono validi");
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Il codice fiscale inserito già presente");
        }
    }

    @Override
    @Transactional(rollbackOn = MyException.class)
    public void remove(Integer id) throws MyException {
        Optional<PersonaPojo> personaPojo = personaRepo.findById(id);

        if (personaPojo.isEmpty())
            throw new MyException("Non ci sono persone associate a quell'id");

        if(! (personaPojo.get().getResidenza() ==null ))
            throw new MyException("Per eliminare una persona è necessario eliminare prima la residenza corrispondente");

        personaRepo.delete(personaPojo.get());
    }

    @Override
    @Transactional(rollbackOn = MyException.class)
    public void update(PersonaDTO personaDTO) throws MyException {
        Optional<PersonaPojo> personaPojo = personaRepo.findById(personaDTO.getId());

        if (personaPojo.isEmpty())
            throw new MyException("Non ci sono persone associate a quell'id");

        Utils.validationPersonaCheck(personaDTO);

        personaPojo.get().setDataNascita(personaDTO.getDataNascita());
        personaPojo.get().setNome(personaDTO.getNome());
        personaPojo.get().setCognome(personaDTO.getCognome());
        personaPojo.get().setCodiceFiscale(personaDTO.getCodiceFiscale());

        personaRepo.save(personaPojo.get());
    }

    @Override
    public PersonaDTO getById(Integer id) throws MyException {

        Optional<PersonaPojo> optionalPersonaPojo = personaRepo.findById(id);

        if (optionalPersonaPojo.isEmpty())
            throw new MyException("Non ci sono persone associate a quell'id");

        return transformPersona(optionalPersonaPojo.get());
    }

    @Override
    public List<PersonaDTO> list() throws MyException {

        List<PersonaPojo> list = personaRepo.findAll();

        if (list.isEmpty())
            throw new MyException("Lista vuota");

        return transformListPersona(list);
    }

    @Override
    public List<PersonaDTO> listByIndirizzo(String indirizzo) throws MyException {
        List<PersonaPojo> list = personaRepo.findByIndirizzo(indirizzo.toLowerCase());

        if (list.isEmpty())
            throw new MyException("Lista vuota");

        return transformListPersona(list);
    }

    @Override
    public List<PersonaDTO> listWithResidenza() throws MyException {

        List<PersonaPojo> filteredList = personaRepo.findAll().stream().filter(s->s.getResidenza()==null).toList();

        if (filteredList.isEmpty())
            throw new MyException("Lista vuota");

        return transformListPersona(filteredList);
    }

    /***********************************************************************************************/

    private PersonaDTO transformPersona(PersonaPojo personaPojo) {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setDataNascita(personaPojo.getDataNascita());
        personaDTO.setNome(personaPojo.getNome());
        personaDTO.setCognome(personaPojo.getCognome());
        personaDTO.setCodiceFiscale(personaPojo.getCodiceFiscale());
        if(personaPojo.getResidenza()!= null){
            personaDTO.setIndirizzo(personaPojo.getResidenza().getIndirizzo());
            personaDTO.setCitta(personaPojo.getResidenza().getCitta());
            personaDTO.setCap(personaPojo.getResidenza().getCap());
        }
        return personaDTO;
    }

    private List<PersonaDTO> transformListPersona(List<PersonaPojo> personaPojoList) {
        return personaPojoList.stream().map(
                s -> {
                    PersonaDTO personaDTO = new PersonaDTO();
                    personaDTO.setCognome(s.getCognome());
                    personaDTO.setNome(s.getNome());
                    personaDTO.setId(s.getId());
                    personaDTO.setDataNascita(s.getDataNascita());
                    personaDTO.setCodiceFiscale(s.getCodiceFiscale());
                    if(s.getResidenza()!= null){
                        personaDTO.setIndirizzo(s.getResidenza().getIndirizzo());
                        personaDTO.setCitta(s.getResidenza().getCitta());
                        personaDTO.setCap(s.getResidenza().getCap());
                    }
                    return personaDTO;
                }
        ).toList();
    }
}
