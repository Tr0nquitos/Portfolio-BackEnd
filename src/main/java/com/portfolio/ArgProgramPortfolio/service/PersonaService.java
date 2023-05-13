
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
    public List<Persona> getPersonas() {
        List<Persona> persona = persoRepo.findAll();
        return persona;
    }

    @Override
    public void savePersona(Persona persona) {
        persoRepo.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        persoRepo.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        return persoRepo.findById(id).orElse(null);
    }
        
   
    
}
