package com.app.main.Aplicacao2.recursos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.app.main.Aplicacao2.multitenancy.TenantSupport;

@Entity
@Table(name = "pontodevenda")
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class PontoDeVenda implements TenantSupport{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cidade;
	private String endereco;
	private String cep;
	private String tenantId;
	
	public PontoDeVenda() {
    }

	public PontoDeVenda(String nome, String cidade, String endereco, String cep){
		this.nome = nome;
		this.cidade = cidade;
		this.endereco = endereco;
		this.cep = cep;
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String getTenantId() {
		return tenantId;
	}
	
	@Override
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
}
