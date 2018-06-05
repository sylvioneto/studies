package br.com.spedroza.casadocodigo.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.spedroza.casadocodigo.loja.model.Produto;

//this annotation is to spring recognize this class
@Repository
@Transactional
public class ProdutoDAO {

	//this annotation is to spring persistence injection
	@PersistenceContext  
	private EntityManager manager;
	
	public void insert(Produto produto){
		manager.persist(produto);
	}
}
