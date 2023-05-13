package com.portfolio.ArgProgramPortfolio.controller;

import com.portfolio.ArgProgramPortfolio.Entity.Persona;
import com.portfolio.ArgProgramPortfolio.Interface.I_PersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonaController {
    
    @Autowired
    private I_PersonaService persoServ;
    
    @PostMapping ("/personas/crear")
    public String agregarPersona(@RequestBody Persona persona){
        persoServ.savePersona(persona);
        return "la persona fue creada correctamente";
    
    }
    
    @GetMapping ("/personas/traer")
    @ResponseBody
    public List<Persona> getPersonas(){
        return persoServ.getPersonas();    
    }
    @DeleteMapping ("/personas/borrar/{id}")
    public String borrarPersona(@PathVariable Long id){
        persoServ.deletePersona(id);
        return "la persona fue eliminada correctamente";
    
    }
    
    
    
    @PutMapping("personas/editar/{id}")
    public Persona editPersona (@PathVariable Long id,
                                @RequestParam ("nombre") String nuevoNombre,
                                @RequestParam ("apellido") String nuevoApellido,
                                @RequestParam ("img") String nuevoImg
                                ){
        Persona persona = persoServ.findPersona(id);
        
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
        
        persoServ.savePersona(persona);
        return persona;
    }
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return persoServ.findPersona((long)1);
    }
    
    }
