package br.com.helpme.helpmecore.improvement.repository;


import br.com.helpme.helpmecore.improvement.model.ClassificationPorcent;
import br.com.helpme.helpmecore.improvement.model.Improvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImprovementRepository extends JpaRepository<Improvement, Long> {

    List<Improvement> findTop5ByOrderByLikesDesc();
}
