package com.crud.desafio.datainfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crud.desafio.datainfo.repository.PessoaRepository;

@SpringBootApplication
public class CrudApplication {

	@Autowired
	PessoaRepository pessoaRepository;
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}
}
