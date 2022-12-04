package br.com.helpme.helpmecore.user.service;

import br.com.helpme.helpmecore.user.dto.PasswordDTO;
import br.com.helpme.helpmecore.user.dto.ProfileDTO;
import br.com.helpme.helpmecore.user.dto.UserDTO;
import br.com.helpme.helpmecore.user.model.User;
import br.com.helpme.helpmecore.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


        Optional<User> newEmailSearched = findByEmail(profileDTO.getEmail());

        if(newEmailSearched.isPresent())
            throw new RuntimeException("Email already Exists");

        Optional<User> userOptional = findByEmail(currentEmail);
        User user = userOptional.get();

        user.setEmail(profileDTO.getEmail());
        user.setName(profileDTO.getName());

        return userRepository.save(user);
    }


    public void changePassword(final String email,final PasswordDTO passwordDTO) {

        Optional<User> userOptional = findByEmail(email);
        User user = userOptional.get();

        checkPasswordEquality(passwordDTO,user);
        user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        userRepository.save(user);
    }

    private void checkPasswordEquality(PasswordDTO passwordDTO, User user){

        if(!passwordEncoder.matches(passwordDTO.getCurrentPassword(), user.getPassword()))
            throw new RuntimeException("Current Password not match");

        if(!arePasswordEquals(passwordDTO.getNewPassword(), passwordDTO.getConfirmNewPassword()))
            throw new RuntimeException("New Password Confirmation not match");
    }

    private boolean arePasswordEquals(String password, String passwordToCheck) {
        if(password.equalsIgnoreCase(passwordToCheck))
                return true;

        return false;
    }

    public void register(UserDTO userDTO) {

        Optional<User> newEmailSearched = findByEmail(userDTO.getEmail());

        if(newEmailSearched.isPresent())
            throw new RuntimeException("Email already Exists");

        if(!arePasswordEquals(userDTO.getNewPassword(), userDTO.getConfirmNewPassword()))
            throw new RuntimeException("Passwords not match, please digit again");

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));
        user.setCreation(LocalDateTime.now());

        userRepository.save(user);
    }
}
