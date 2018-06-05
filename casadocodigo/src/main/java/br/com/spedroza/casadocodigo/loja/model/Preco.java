package br.com.spedroza.casadocodigo.loja.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

//this annotation means it is into the product and will be persisted
@Embeddable
public class Preco {

	private BigDecimal valor;
	private TipoPreco tipo;
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoPreco getTipo() {
		return tipo;
	}
	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
		
}
