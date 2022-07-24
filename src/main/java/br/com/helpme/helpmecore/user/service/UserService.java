package br.com.helpme.helpmecore.user.service;

import br.com.helpme.helpmecore.user.model.User;
import br.com.helpme.helpmecore.user.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByEmailAndPassword(User user){

        String digestPassword = new DigestUtils("SHA3-256").digestAsHex(user.getPassword());
        return userRepository.findByEmailAndPassword(user.getEmail() , digestPassword);
    }

}
