package com.app.main.Aplicacao2.multitenancy;

import com.app.main.Aplicacao2.recursos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class PDVService{

	private final PDVRepositorio pdvRep;
  
  	@PersistenceContext
  	public EntityManager entityManager;

  	@Autowired
  	public PDVService(PDVRepositorio pdvRep) {
	  this.pdvRep = pdvRep;
  	}

  	@Transactional
  	public PontoDeVenda save(PontoDeVenda pdv) {
	  return pdvRep.save(pdv);
  	}

	@Transactional
	public List<PontoDeVenda> findAll() {
		  return pdvRep.findAll();
	}

  	@Transactional
	public List<PontoDeVenda> findByCidade(String cidade) {
		return pdvRep.findByCidade(cidade);
	}
  	
	@Transactional
	public List<PontoDeVenda> findByCep(String cep) {
		return pdvRep.findByCep(cep);
	}
	
	@Transactional
	public List<PontoDeVenda> findByNome(String nome) {
		return pdvRep.findByNome(nome);
	}
	
	@Transactional
	public List<PontoDeVenda> findByTenantId(String tid) {
		return pdvRep.findByTenantId(tid);
	}
}
