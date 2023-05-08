
package com.portfolio.ArgProgramPortfolio.service;

import com.portfolio.ArgProgramPortfolio.Entity.Persona;
import com.portfolio.ArgProgramPortfolio.Interface.I_PersonaService;
import com.portfolio.ArgProgramPortfolio.repository.I_PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
 public class PersonaService implements I_PersonaService {
    
    @Autowired
    public I_PersonaRepository persoRepo;

    @Override
    public List<Persona> verPersonas() {
        return persoRepo.findAll();
    }

    @Override
    public void crearPersona(Persona persona) {
        persoRepo.save(persona);
    }

    @Override
    public void borrarPersona(Long id) {
        persoRepo.deleteById(id);
    }

    @Override
    public Persona buscarPersona(Long id) {
        return persoRepo.findById(id).orElse(null);
    }
        
   
    
}
