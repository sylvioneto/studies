package br.com.spedroza.casadocodigo.loja.dao;

import java.util.List;

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
		System.out.println("Inside "+this.getClass().getName());
		manager.persist(produto);
	}

	public List<Produto> getAll() {
		System.out.println("Inside "+this.getClass().getName());
		System.out.println("Querying produtos... ");
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto getById(int id) {
		//return manager.find(Produto.class, id);
		return manager.createQuery("select distinct(p) from Produto p left join fetch p.precos precos where p.id = :id", Produto.class).setParameter("id", id).getSingleResult();
	}
}
