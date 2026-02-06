package com.LMS.library.security;

import com.LMS.library.model.MasterUser;
import com.LMS.library.repository.MasterUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final MasterUserRepository masterUserRepository;
    private final PasswordEncoder passwordEncoder;

    public void login(MasterUser masterUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(masterUser.getUsername() ,masterUser.getPassword())
        );
    MasterUser masterUser1 = (MasterUser) authentication.getPrincipal();


    String token = authUtil.generateAccessToken(masterUser1);
    }
    public void signUp(MasterUser masterUser){
        MasterUser savedMasterUser = masterUserRepository.findByName(masterUser.getName()).orElse(null);
        if(savedMasterUser != null) {throw new IllegalArgumentException("User Already Exist");}
          masterUser.setPassword(passwordEncoder.encode(masterUser.getPassword()));
          masterUserRepository.save(masterUser);
    }
}
