package com.eyproject.myproject.classes.controller;

import com.eyproject.myproject.classes.dto.ResidenzaDTO;
import com.eyproject.myproject.classes.exception.MyException;
import com.eyproject.myproject.classes.response.Response;
import com.eyproject.myproject.classes.response.ResponseListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.eyproject.myproject.interfaces.iService.iResidenzaService;

@RestController
@RequestMapping("/api/residenza")
public class ResidenzaController {

    @Autowired
    private iResidenzaService residenzaService;

    @PostMapping("/create")
    private Response create(@RequestBody ResidenzaDTO residenzaDTO){
        Response response = new Response();
        response.setResponseCode(false);
        try{
            residenzaService.create(residenzaDTO);
            response.setResponseCode(true);
        }catch (MyException e ){
            response.setMsg(e.getMessage());
        }
        return response;
    }

    @PostMapping("/update")
    private Response update(@RequestBody ResidenzaDTO residenzaDTO){
        Response response = new Response();
        response.setResponseCode(false);
        try{
            residenzaService.update(residenzaDTO);
            response.setResponseCode(true);
        }catch (MyException e){
            response.setMsg(e.getMessage());
        }
        return response;
    }

    @PostMapping("/remove")
    private Response remove(@RequestParam Integer id){
        Response response = new Response();
        response.setResponseCode(false);
        try{
            residenzaService.remove(id);
            response.setResponseCode(true);
        }catch (MyException e ){
            response.setMsg(e.getMessage());
        }
        return response;
    }

    @GetMapping("/list")
    private ResponseListData<ResidenzaDTO> list(){
        ResponseListData<ResidenzaDTO> response = new ResponseListData<>();
        response.setResponseCode(false);
        try {
            response.setData(residenzaService.list());
            response.setResponseCode(true);
        }catch (MyException e){
            response.setMsg(e.getMessage());
        }
        return response;
    }
}
