package br.com.helpme.helpmecore.user.service;

import br.com.helpme.helpmecore.user.dto.PasswordDTO;
import br.com.helpme.helpmecore.user.dto.ProfileDTO;
import br.com.helpme.helpmecore.user.model.User;
import br.com.helpme.helpmecore.user.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User updateUserProfile(String currentEmail, ProfileDTO profileDTO){

        Optional<User> userOptional = findByEmail(currentEmail);
        User user = userOptional.get();

        user.setEmail(profileDTO.getEmail());
        user.setName(profileDTO.getName());

        return userRepository.save(user);
    }


    public void changePassword(final String email,final PasswordDTO passwordDTO) {

        Optional<User> userOptional = findByEmail(email);
        User user = userOptional.get();

        validatePasswordChange(passwordDTO, user);
        user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        userRepository.save(user);
    }

    private void validatePasswordChange(PasswordDTO passwordDTO, User user){
        if(!passwordEncoder.matches(passwordDTO.getCurrentPassword(), user.getPassword()))
            throw new RuntimeException("Current Password not match");

        if(!passwordDTO.getNewPassword().equalsIgnoreCase(passwordDTO.getConfirmNewPassword()))
            throw new RuntimeException("New Password Confirmation not match");
    }
}
