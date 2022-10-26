package com.grammercetamol.models.implementation;

import com.grammercetamol.models.AppUsers;
import com.grammercetamol.models.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class UserDetialsServicesImpl implements UserDetailsService {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUsers appUsers = appUserRepository.findByUsername(email)
                .orElseThrow(()-> new UsernameNotFoundException("AppUsers Not Found with username: "+ email));
        return UserDetailsImpl.build(appUsers);
    }
}
