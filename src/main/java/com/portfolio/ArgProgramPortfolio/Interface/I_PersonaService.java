
package com.portfolio.ArgProgramPortfolio.Interface;

import com.portfolio.ArgProgramPortfolio.Entity.Persona;
import java.util.List;



public interface I_PersonaService {
    
    public List<Persona> verPersonas();
    public void crearPersona (Persona persona);
    public void borrarPersona (Long id);
    public Persona buscarPersona (Long id);
    
}
