package com.meigetsu.arisu.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meigetsu.arisu.calendar.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String> {
}
