package br.com.helpme.helpmecore.improvement.repository;


import br.com.helpme.helpmecore.improvement.model.Improvement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static br.com.helpme.helpmecore.improvement.repository.ImprovementQueries.FIND_ALL_BY_USER_ID;

@Repository
public interface ImprovementRepository extends JpaRepository<Improvement, Long> {

    List<Improvement> findTop5ByOrderByLikesDesc();

    @Query(value=FIND_ALL_BY_USER_ID)
    Page<Improvement> findAllByUserId(long userId, Pageable pageable);
}
