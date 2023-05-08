
package com.portfolio.ArgProgramPortfolio.Security.Controller;

import com.portfolio.ArgProgramPortfolio.Security.Dto.JwtDto;
import com.portfolio.ArgProgramPortfolio.Security.Dto.LoginUsuario;
import com.portfolio.ArgProgramPortfolio.Security.Dto.NuevoUsuario;
import com.portfolio.ArgProgramPortfolio.Security.Entity.Rol;
import com.portfolio.ArgProgramPortfolio.Security.Entity.Usuario;
import com.portfolio.ArgProgramPortfolio.Security.Enums.RolNombre;
import com.portfolio.ArgProgramPortfolio.Security.Service.Rol_Service;
import com.portfolio.ArgProgramPortfolio.Security.Service.UsuarioService;
import com.portfolio.ArgProgramPortfolio.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager; 
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    Rol_Service rol_Service;
    
    
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity (new Mensaje("Campos mal puestos o email invalido"),HttpStatus.BAD_REQUEST);
        
         if (usuarioService.existByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity (new Mensaje("Ese usuario ya existe"),HttpStatus.BAD_REQUEST);
         
          if (usuarioService.existByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity (new Mensaje("Ese Email ya existe"),HttpStatus.BAD_REQUEST);
          
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(),nuevoUsuario.getNombreUsuario(),nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        
        Set<Rol> roles = new HashSet<>();
        roles.add(rol_Service.getByRolNombre(RolNombre.ROL_USER).get());
        
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rol_Service.getByRolNombre(RolNombre.ROL_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        
        return new ResponseEntity(new Mensaje("Usuario creado!"),HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login( @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity (new Mensaje("Campos mal puestos"),HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        String jwt = jwtProvider.generateToken(authentication);
        
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(),userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto,HttpStatus.OK);
    
    
    }
        }
