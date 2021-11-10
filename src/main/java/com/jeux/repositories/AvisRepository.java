package com.jeux.repositories;

import com.jeux.models.Avis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends CrudRepository<Avis, Long> {
    @Override
    List<Avis> findAll();

    List<Avis> findByJeuId(Long jeuId);

    List<Avis> findTopByOrderByDateEnvoieDesc();
}
