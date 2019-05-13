package com.crud.desafio.datainfo.repository;

import org.springframework.data.repository.CrudRepository;

import com.crud.desafio.datainfo.model.Pessoa;

import java.util.List;

public interface PessoaRepository extends CrudRepository<Pessoa,Integer> {
    List<Pessoa> findAll();
    void deleteByPrimeiroNome(String primeiroNome);
}
