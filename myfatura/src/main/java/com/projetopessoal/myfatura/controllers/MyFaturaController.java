package com.projetopessoal.myfatura.controllers;

import com.projetopessoal.myfatura.modelos.Fatura;
import com.projetopessoal.myfatura.repositorios.FaturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/myfatura")
public class MyFaturaController {

    @Autowired
    private FaturaRepositorio _faturaRepositorio;

    @GetMapping()
    public ResponseEntity GetFaturas(){
        try {
            return new ResponseEntity(_faturaRepositorio.CapturarTodasFaturas(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("Erro interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity GetFaturasPorAno(@PathVariable long ano){
        try {
            return new ResponseEntity(_faturaRepositorio.CapturarFaturasPorAno(ano), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("Erro interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity CriarFatura(@RequestBody Fatura faturaDTO){
        try {
            return new ResponseEntity(_faturaRepositorio.CriarFatura(faturaDTO), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("Erro interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity ExcluirFatura(@PathVariable long id){
        try {
            return new ResponseEntity(_faturaRepositorio.ExcluirFatura(id) , HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("Erro interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity EditarFatura(@RequestBody Fatura faturaDTO){
        try {
            return new ResponseEntity(_faturaRepositorio.EditarFatura(faturaDTO) , HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("Erro interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
