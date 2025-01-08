package com.projeto.loja.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.loja.DTO.AuthDTO;
import com.projeto.loja.DTO.LoginResponseDTO;
import com.projeto.loja.DTO.RegisterDTO;
import com.projeto.loja.Exception.InvalidCredentialsException;
import com.projeto.loja.Exception.UserAlreadyExistsException;
import com.projeto.loja.Model.User;
import com.projeto.loja.Model.UserRole;
import com.projeto.loja.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public LoginResponseDTO authenticate(AuthDTO authDTO) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.password());
            var auth = authenticationManager.authenticate(authToken);

            String token = tokenService.generateToken((User) auth.getPrincipal());
            return new LoginResponseDTO(token);
        } catch (Exception e) {
            throw new InvalidCredentialsException("Credenciais inválidas. Verifique seu login e senha.");
        }
    }

    public LoginResponseDTO registerAdmin(RegisterDTO registerDTO) {
        if (repository.findByLogin(registerDTO.login()) != null) {
            throw new UserAlreadyExistsException("Usuário com login '" + registerDTO.login() + "' já existe.");
        }

        String encryptedPassword = passwordEncoder.encode(registerDTO.password());
        User newUser = new User(registerDTO.login(), encryptedPassword, registerDTO.role());
        repository.save(newUser);

        return authenticate(new AuthDTO(registerDTO.login(), registerDTO.password()));
    }

    public LoginResponseDTO registerDefault(AuthDTO authDTO) {
        if (repository.findByLogin(authDTO.login()) != null) {
            throw new UserAlreadyExistsException("Usuário com login '" + authDTO.login() + "' já existe.");
        }

        String encryptedPassword = passwordEncoder.encode(authDTO.password());
        User newUser = new User(authDTO.login(), encryptedPassword, UserRole.USER);
        repository.save(newUser);

        return authenticate(new AuthDTO(authDTO.login(), authDTO.password()));
    }
}
