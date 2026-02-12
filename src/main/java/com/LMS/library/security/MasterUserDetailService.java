package com.LMS.library.security;

import com.LMS.library.model.MasterUser;
import com.LMS.library.repository.MasterUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterUserDetailService implements UserDetailsService {
    @Autowired
    private final MasterUserRepository masterUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MasterUser user =  masterUserRepository.findByName(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found: " + username));



    return new User(
            user.getName(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority(user.getRole()))
    );
    }
}
