package com.kitm.ems_backend.controller;


import com.kitm.ems_backend.dto.JwtResponse;
import com.kitm.ems_backend.dto.LoginRequestDTO;
import com.kitm.ems_backend.dto.MessageResponse;
import com.kitm.ems_backend.dto.SignupRequestDTO;
import com.kitm.ems_backend.entity.Role;
import com.kitm.ems_backend.entity.User;
import com.kitm.ems_backend.enums.Roles;
import com.kitm.ems_backend.repository.RoleRepository;
import com.kitm.ems_backend.repository.UserRepository;
import com.kitm.ems_backend.security.jwt.JwtUtils;
import com.kitm.ems_backend.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO signupRequest)
    {
        if (userRepository.existsByUsername(signupRequest.getUsername()))
        {
            throw new RuntimeException("Error: UserName is already taken.");
        }

        User user = new User(signupRequest.getUsername(), encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null)
        {
            Role userRole = roleRepository.findByName(Roles.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        }
        else{
            strRoles.forEach(role -> {
                switch (role){
                    case "admin":
                        Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(Roles.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                        break;
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered succesfuly"));
    }
}
