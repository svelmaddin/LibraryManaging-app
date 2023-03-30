package com.elmaddin.TaskForWork.service;

import com.elmaddin.TaskForWork.dto.LoginRequest;
import com.elmaddin.TaskForWork.dto.TokenResponseDto;
import com.elmaddin.TaskForWork.exception.GenericException;
import com.elmaddin.TaskForWork.utils.TokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService,
                       TokenGenerator tokenGenerator,
                       AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public String getLoggedInUsername(){
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
    public TokenResponseDto login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
       return TokenResponseDto.builder()
               .accessToken(tokenGenerator.generateToken(auth))
               .userResponse(userService.getUser(loginRequest.getUsername()))
               .build();
        }catch (Exception e){
            throw  GenericException.builder()
                    .errorMessage("user not found")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
