package com.eyproject.myproject.interfaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.eyproject.myproject.classes.pojo.ResidenzaPojo;

import java.util.Optional;

@Repository
public interface iResidenzaRepo extends JpaRepository<ResidenzaPojo,Integer> {
/*
    @Query("SELECT r FROM ResidenzaPojo r WHERE r.indirizzo = :indirizzo AND r.citta = :citta AND r.cap = :cap")
    Optional<ResidenzaPojo> findByIndirizzoCittaCap(@Param("indirizzo") String indirizzo, @Param("citta") String citta, @Param("cap") Integer cap);
*/
}
