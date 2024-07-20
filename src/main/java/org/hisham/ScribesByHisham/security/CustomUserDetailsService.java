package org.hisham.ScribesByHisham.security;

import org.hisham.ScribesByHisham.models.User;
import org.hisham.ScribesByHisham.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return userPrincipal;
    }

    public UserDetails loadUserById(String userId) throws UsernameNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found wiht id" + userId));
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return userPrincipal;
    }
}