package com.eyproject.myproject.interfaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.eyproject.myproject.classes.pojo.PersonaPojo;

import java.util.List;

@Repository
public interface iPersonaRepo extends JpaRepository<PersonaPojo,Integer> {
    @Query("SELECT p FROM PersonaPojo p JOIN p.residenza r WHERE r.indirizzo LIKE %:indirizzo%")
    List<PersonaPojo> findByIndirizzo(@Param("indirizzo")String indirizzo);
}
