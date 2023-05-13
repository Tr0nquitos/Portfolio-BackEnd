/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.ArgProgramPortfolio.controller;

import com.portfolio.ArgProgramPortfolio.DTO.Dto_Experiencia;
import com.portfolio.ArgProgramPortfolio.Entity.Experiencia;
import com.portfolio.ArgProgramPortfolio.Security.Controller.Mensaje;
import com.portfolio.ArgProgramPortfolio.service.ExperienciaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ExperienciaController {
    @Autowired
    ExperienciaService Expservice;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = Expservice.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!Expservice.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = Expservice.getOne(id).get();
        return new ResponseEntity<>(experiencia, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!Expservice.existsById(id)) {
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Expservice.delete(id);
        return new ResponseEntity<>(new Mensaje("experiencia eliminada"), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Dto_Experiencia dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Expservice.existsByNombreE(dtoexp.getNombreE()))
            return new ResponseEntity<>(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescripcionE());
        Expservice.save(experiencia);

        return new ResponseEntity<>(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Dto_Experiencia dtoexp){
        if(!Expservice.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        if(Expservice.existsByNombreE(dtoexp.getNombreE()) && Expservice.getByNombreE(dtoexp.getNombreE()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = Expservice.getOne(id).get();
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE((dtoexp.getDescripcionE()));

        Expservice.save(experiencia);
        return new ResponseEntity<>(new Mensaje("Experiencia actualizada"), HttpStatus.OK);

    }
}


