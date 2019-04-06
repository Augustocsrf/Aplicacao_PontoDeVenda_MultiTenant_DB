package com.app.main.Aplicacao2.recursos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PDVRepositorio extends JpaRepository<PontoDeVenda, Integer> {
	List<PontoDeVenda> findByNome(String nome);   
	List<PontoDeVenda> findByCidade(String cidade);
	List<PontoDeVenda> findByCep(String cep);
	List<PontoDeVenda> findByTenantId(String tid);
}
