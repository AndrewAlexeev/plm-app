package com.mai.projects.plm.controllers;

import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.model.response.TokenResponse;
import com.mai.projects.plm.security.jwt.JwtTokenProvider;
import com.mai.projects.plm.security.jwt.model.AuthenticationRequest;
import com.mai.projects.plm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl extends AbstractMainController implements AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @ResponseBody
    public ResponseEntity<ResponseObject<TokenResponse>> login(@RequestBody AuthenticationRequest requestDto) {
        try {
            String username = requestDto.getUserName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUserName(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            TokenResponse tokenResponse =new TokenResponse();
            tokenResponse.setToken(token);

            return prepareResponseEntity(tokenResponse);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
