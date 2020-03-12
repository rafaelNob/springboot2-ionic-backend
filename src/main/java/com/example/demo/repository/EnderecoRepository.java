package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
