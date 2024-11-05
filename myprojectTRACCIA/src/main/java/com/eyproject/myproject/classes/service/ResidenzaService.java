package com.eyproject.myproject.classes.service;

import com.eyproject.myproject.classes.dto.ResidenzaDTO;
import com.eyproject.myproject.classes.exception.MyException;
import com.eyproject.myproject.classes.pojo.PersonaPojo;
import com.eyproject.myproject.classes.pojo.ResidenzaPojo;
import com.eyproject.myproject.classes.utils.Utils;
import com.eyproject.myproject.interfaces.iService.iResidenzaService;
import com.eyproject.myproject.interfaces.repository.iPersonaRepo;
import com.eyproject.myproject.interfaces.repository.iResidenzaRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidenzaService implements iResidenzaService {

    @Autowired
    private iResidenzaRepo residenzaRepo;
    @Autowired
    private iPersonaRepo personaRepo;

    @Override
    @Transactional(rollbackOn = MyException.class)
    public void create(ResidenzaDTO residenzaDTO) throws MyException {

        Utils.validationResidenzaCheck(residenzaDTO);

        Optional<PersonaPojo> personaPojo = personaRepo.findById(residenzaDTO.getId_anagrafica());
        if (personaPojo.isEmpty())
            throw new MyException("Nessuna persona trovata");

        ResidenzaPojo residenzaPojo = new ResidenzaPojo();
        try {
            residenzaPojo.setPersona(personaPojo.get());
            residenzaPojo.setIndirizzo(residenzaDTO.getIndirizzo().toLowerCase());
            residenzaPojo.setCap(residenzaDTO.getCap());
            residenzaPojo.setCitta(residenzaDTO.getCitta());
            residenzaRepo.save(residenzaPojo);
        } catch (DataIntegrityViolationException e) {
            throw new MyException("Errore di integrità: " + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = MyException.class)
    public void update(ResidenzaDTO residenzaDTO) throws MyException {

        Optional<PersonaPojo> personaPojo = personaRepo.findById(residenzaDTO.getId_anagrafica());
        if (personaPojo.isEmpty())
            throw new MyException("Nessuna persona trovata");

        Optional<ResidenzaPojo> residenzaPojo = residenzaRepo.findById(residenzaDTO.getId());
        if (residenzaPojo.isEmpty())
            throw new MyException("Nessuna residenza trovata");

        Utils.validationResidenzaCheck(residenzaDTO);

        try {
            residenzaPojo.get().setIndirizzo(residenzaDTO.getIndirizzo().toLowerCase());
            residenzaPojo.get().setCap(residenzaDTO.getCap());
            residenzaPojo.get().setCitta(residenzaDTO.getCitta());
            residenzaRepo.save(residenzaPojo.get());
        } catch (DataIntegrityViolationException e) {
            throw new MyException("Errore di integrità: " + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = MyException.class)
    public void remove(Integer id) throws MyException {

        Optional<ResidenzaPojo> residenzaPojo = residenzaRepo.findById(id);
        if (residenzaPojo.isEmpty())
            throw new MyException("Nessuna residenza trovata");

        PersonaPojo persona = residenzaPojo.get().getPersona();
        persona.setResidenza(null);
        residenzaRepo.delete(residenzaPojo.get());
        personaRepo.save(persona);
    }

    @Override
    public List<ResidenzaDTO> list() throws MyException {
        List<ResidenzaPojo> residenzaPojo = residenzaRepo.findAll();
        if (residenzaPojo.isEmpty())
            throw new MyException("Lista vuota");

        return transformListaResidenza(residenzaPojo);
    }

    /***********************************************************************************************/

    private List<ResidenzaDTO> transformListaResidenza(List<ResidenzaPojo> residenzaPojo) {
        return residenzaPojo.stream().map(s -> {
            ResidenzaDTO residenzaDTO = new ResidenzaDTO();
            residenzaDTO.setId(s.getId());
            residenzaDTO.setCap(s.getCap());
            residenzaDTO.setCitta(s.getCitta());
            residenzaDTO.setIndirizzo(s.getIndirizzo());
            residenzaDTO.setId_anagrafica(s.getPersona().getId());
            return residenzaDTO;
        }).toList();
    }


    @Override
    public ResidenzaDTO get(Integer id) {
        return null;
    }

}
