package com.softwared.banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softwared.banco.modelo.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Optional<Authority> findByName(String name);
}
