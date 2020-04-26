package org.example.springboot.security;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.userprofile.UserProfile;
import org.example.springboot.domain.userprofile.UserProfileRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserProfileDetailsService implements UserDetailsService {
    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfileDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile findProfile = userProfileRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username is not found"));
        UserProfileDetails userProfileDetails = new UserProfileDetails(findProfile);
        return userProfileDetails;
    }
}
