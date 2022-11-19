package br.com.helpme.helpmecore.improvement.repository;

import br.com.helpme.helpmecore.improvement.model.UserImprovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImprovementRepository extends JpaRepository<UserImprovement,Long> {

    Optional<UserImprovement> findByIdUserAndIdImprovement(long idUser, long idImprovement);

}
