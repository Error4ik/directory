package com.directory.service;

import com.directory.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Detail service.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Component
public class DetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User user = this.userService.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("user"));
        new HashSet<>().add(new SimpleGrantedAuthority("user"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
