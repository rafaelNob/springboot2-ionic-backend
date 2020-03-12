package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
