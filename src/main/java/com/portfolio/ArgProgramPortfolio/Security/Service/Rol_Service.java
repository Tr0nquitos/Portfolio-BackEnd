/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.ArgProgramPortfolio.Security.Service;

import com.portfolio.ArgProgramPortfolio.Security.Entity.Rol;
import com.portfolio.ArgProgramPortfolio.Security.Enums.RolNombre;
import com.portfolio.ArgProgramPortfolio.Security.Repository.I_RolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class Rol_Service {
    @Autowired
    I_RolRepository irolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNombre(rolNombre);
    
    }
    public void save(Rol rol){
        irolRepository.save(rol);
    }
}
