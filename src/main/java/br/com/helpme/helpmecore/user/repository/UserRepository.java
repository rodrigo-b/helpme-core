package br.com.helpme.helpmecore.user.repository;

import br.com.helpme.helpmecore.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

     Optional<User> findByEmailAndPassword(String email, String password);
     Optional<User> findByEmail(String email);

}
