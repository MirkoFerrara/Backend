package com.eyproject.myproject.interfaces.iService;

import com.eyproject.myproject.classes.dto.ResidenzaDTO;
import com.eyproject.myproject.classes.exception.MyException;
import com.eyproject.myproject.classes.dto.PersonaDTO;

import java.util.List;

public interface iPersonaService {
    void create(PersonaDTO personaDTO) throws MyException;
    void remove(Integer id)throws MyException;
    void update(PersonaDTO personaDTO) throws MyException;
    PersonaDTO getById(Integer id) throws MyException;
    List<PersonaDTO> list()  throws MyException;
    List<PersonaDTO> listByIndirizzo(String indirizzo) throws MyException;
    List<PersonaDTO> listWithResidenza()throws MyException ;
}
