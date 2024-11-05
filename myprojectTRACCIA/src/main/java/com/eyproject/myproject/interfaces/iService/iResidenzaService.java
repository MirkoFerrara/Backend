package com.eyproject.myproject.interfaces.iService;

import com.eyproject.myproject.classes.dto.ResidenzaDTO;
import com.eyproject.myproject.classes.exception.MyException;
import com.eyproject.myproject.classes.pojo.ResidenzaPojo;

import java.util.List;

public interface iResidenzaService {

    void create(ResidenzaDTO residenzaDTO) throws MyException;
    void update( ResidenzaDTO residenzaDTO) throws MyException;
    List<ResidenzaDTO> list() throws MyException;

    /*************************** Da fare *******************************/

    void remove(Integer id) throws MyException;
    ResidenzaDTO get(Integer id) throws MyException;
}
