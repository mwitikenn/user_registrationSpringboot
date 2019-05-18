package com.datachip.apitest.Repositories;

import com.datachip.apitest.entities.RegistrationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RegistrationDetailsRepo extends JpaRepository<RegistrationDetails, Integer>{
    ArrayList<RegistrationDetails> findByEmailAndPassword(String email, String password);
}
