package com.eyproject.myproject.classes.controller;

import com.eyproject.myproject.classes.exception.MyException;
import com.eyproject.myproject.classes.dto.PersonaDTO;
import com.eyproject.myproject.classes.response.Response;
import com.eyproject.myproject.classes.response.ResponseListData;
import com.eyproject.myproject.interfaces.iService.iPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private iPersonaService personaService;

    @PostMapping("/create")
    private Response create(@RequestBody PersonaDTO personaDTO) throws MyException {
        Response response = new Response();
        response.setResponseCode(false);
        try{
            personaService.create(personaDTO);
            response.setResponseCode(true);
        }catch (MyException | HttpMessageNotReadableException | DataIntegrityViolationException e ){
            response.setMsg(e.getMessage());
        }
        return response;
    }
    @PostMapping("/remove")
    private Response remove(@RequestParam Integer id){
        Response response = new Response();
        response.setResponseCode(false);
        try{
            personaService.remove(id);
            response.setResponseCode(true);
        }catch (MyException e ){
            response.setMsg(e.getMessage());
        }
        return response;
    }
    @GetMapping("/getById")
    private Response getById(@RequestParam Integer id){
        Response response = new Response();
        response.setResponseCode(false);
        try{
            personaService.getById(id);
            response.setResponseCode(true);
        }catch (MyException e ){
            response.setMsg(e.getMessage());
        }
        return response;
    }
    @PostMapping("/update")
    private Response update(@RequestBody PersonaDTO personaDTO){
        Response response = new Response();
        response.setResponseCode(false);
        try{
            personaService.update(personaDTO);
            response.setResponseCode(true);
        }catch (MyException e ){
            response.setMsg(e.getMessage());
        }
        return response;
    }
    @GetMapping("/list")
    private ResponseListData<PersonaDTO> list(){
        ResponseListData<PersonaDTO> response = new ResponseListData<>();
        response.setResponseCode(false);
        try{
            response.setData(personaService.list());
            response.setResponseCode(true);
        }catch (MyException e){
            response.setMsg(e.getMessage());
        }
        return response;
    }

    @GetMapping("/listNoResidenza")
    private ResponseListData<PersonaDTO> listNoResidenza(){
        ResponseListData<PersonaDTO> response = new ResponseListData<>();
        response.setResponseCode(false);
        try{
            response.setData(personaService.listWithResidenza());
            response.setResponseCode(true);
        }catch (MyException e){
            response.setMsg(e.getMessage());
        }
        return response;
    }
    /*
        funge da add/update indirizzo
     */
    @GetMapping("/listByIndirizzo")
    private ResponseListData<PersonaDTO> listByIndirizzo( @RequestParam String indirizzo){
        ResponseListData<PersonaDTO> response = new ResponseListData<>();
        response.setResponseCode(false);
        try {
            response.setData(personaService.listByIndirizzo(indirizzo));
            response.setResponseCode(true);
        }catch (MyException e){
            response.setMsg(e.getMessage());
        }
        return response;
    }
}
