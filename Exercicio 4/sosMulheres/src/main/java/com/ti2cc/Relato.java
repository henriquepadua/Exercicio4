package com.ti2cc;

import java.io.Serializable;
import java.util.Date;

public class Relato implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private String quantidade;
	
	public Relato() {
		id = "";
		nome = "";
		quantidade = "";
	}

	public Relato(String id, String nome,String quantidade) {
		setId(id);
		setNome(nome);
		setQuantidade(Quantidade);
	}		
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if (nome.length() >= 3)
			this.nome = nome;
	}

	public Date getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Relato: " + nome + "  Quantidade " + quantidade + "  id: " + id;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Relato) obj).getId());
	}	
}