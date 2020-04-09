package com.mai.projects.plm.controllers;

import com.mai.projects.plm.enums.ErrorEnum;
import com.mai.projects.plm.enums.RoleEnum;
import com.mai.projects.plm.entities.Role;
import com.mai.projects.plm.entities.Status;
import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.exception.ServerException;
import com.mai.projects.plm.model.request.RegistrationRequest;
import com.mai.projects.plm.repository.RoleRepository;
import com.mai.projects.plm.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
public class RegistrationControllerImpl extends AbstractMainController implements RegistrationController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity registration(@Valid RegistrationRequest registrationRequest) {
        Role userRole = roleRepository.findByName(RoleEnum.USER.getName()).orElse(null);
        User user =  new User();

        user.setUserName(registrationRequest.getUserName());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(encoder.encode(registrationRequest.getPassword()));
        user.setRoles(List.of(userRole));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setStatus(Status.ACTIVE);
        try{
            userRepository.save(user);
        }catch(DataIntegrityViolationException ex){
            throw new ServerException(ErrorEnum.USERNAME_OR_EMAIL_ARE_ALWAYS_EXIST, List.of());
        }
        return prepareResponseEntity();
    }
}
