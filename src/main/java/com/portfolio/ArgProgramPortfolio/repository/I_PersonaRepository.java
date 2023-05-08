
package com.portfolio.ArgProgramPortfolio.repository;

import com.portfolio.ArgProgramPortfolio.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_PersonaRepository extends JpaRepository<Persona,Long>  {
    
}
