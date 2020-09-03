package com.logistic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistic.domain.T;

@Repository
public interface PedidoRepository extends JpaRepository<T, Integer>{

}
