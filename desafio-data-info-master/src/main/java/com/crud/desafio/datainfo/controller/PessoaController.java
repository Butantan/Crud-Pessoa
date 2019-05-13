package com.crud.desafio.datainfo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.crud.desafio.datainfo.model.Pessoa;
import com.crud.desafio.datainfo.repository.PessoaRepository;

@RestController
@RequestMapping(value = "/inicio")
@Transactional
public class PessoaController {

    PessoaRepository pessoaRepository;

    @Autowired
    public PessoaController(PessoaRepository pessoaRepository)
    {
        this.pessoaRepository=pessoaRepository;
    }

    @GetMapping
    public ModelAndView home()
    {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("pessoa", new Pessoa());
        return modelAndView;
    }

    @GetMapping("/listarTodos")
    public List<Pessoa> getAllElements()
    {
        return pessoaRepository.findAll();
    }

    @RequestMapping(value="deletar/{id}", method = RequestMethod.DELETE)
    public void deleteElement(@PathVariable String id)
    {
        pessoaRepository.delete(Integer.valueOf(id));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/adicionar")
    public Pessoa createElement(@RequestBody Pessoa pessoa)
    {
        System.out.println(pessoa.getPrimeiroNome());
        System.out.println(pessoa.getSobrenome());
        return pessoaRepository.save(pessoa);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/atualizar/{id}")
    public Pessoa update(@PathVariable String id, @RequestBody Pessoa pessoa) {
        Pessoa update = pessoaRepository.findOne(Integer.valueOf(id));
        update.setEmail(pessoa.getEmail());
        update.setPrimeiroNome(pessoa.getPrimeiroNome());
        update.setSobrenome(pessoa.getSobrenome());
        update.setCpf(pessoa.getCpf());
        update.setTelefone(pessoa.getTelefone());
        return pessoaRepository.save(update);
    }

}
