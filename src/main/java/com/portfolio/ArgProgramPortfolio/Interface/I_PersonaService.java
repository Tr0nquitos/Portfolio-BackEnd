
package com.portfolio.ArgProgramPortfolio.Interface;

import com.portfolio.ArgProgramPortfolio.Entity.Persona;
import java.util.List;



public interface I_PersonaService {
    
    public List<Persona> getPersonas();
    public void savePersona (Persona persona);
    public void deletePersona (Long id);
    public Persona findPersona (Long id);
    
}
