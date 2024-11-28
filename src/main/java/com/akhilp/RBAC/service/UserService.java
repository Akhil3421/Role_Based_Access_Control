package com.akhilp.RBAC.service;


import com.akhilp.RBAC.model.Users;
import com.akhilp.RBAC.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        if (repo.existsByUsername(user.getUsername())) {
            throw new UsernameNotFoundException("Username is already taken.");
        }
        if (user.getRole() == null) {
            user.setRole(Users.Role.USER);  // default role
        }
        return repo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername(),user.getRole());
        }
        else
            throw new UsernameNotFoundException("Invalid username or password");
    }
}
