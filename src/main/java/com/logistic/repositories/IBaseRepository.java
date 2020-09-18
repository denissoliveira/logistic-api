package com.logistic.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.logistic.domain.BaseEntity;


/**
 * O repositório base da aplicação.
 *
 * @param <E>  A entidade.
 * @param <K> O tipo da identificação das entidades.
 */
@NoRepositoryBean
public interface IBaseRepository<E extends BaseEntity, K extends Serializable> extends JpaRepository<E, K> {
}
