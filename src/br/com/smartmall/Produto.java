package br.com.smartmall;

import java.util.Calendar;


public class Produto {
	private int id_produto;
	private String nome_prodt;
	private String marca;
	private String numLote;
	private int quantidade;
	private Calendar dataEntradaEstoque;
	private String descricao;
	
	public Produto(){}
	
	public Produto(int id_produto){
		this.id_produto = id_produto;
	}
	
	
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public String getNome_prodt() {
		return nome_prodt;
	}
	public void setNome_prodt(String nome_prodt) {
		this.nome_prodt = nome_prodt;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getNumLote() {
		return numLote;
	}
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Calendar getDataEntradaEstoque() {
		return dataEntradaEstoque;
	}
	public void setDataEntradaEstoque(Calendar dataEntradaEstoque) {
		this.dataEntradaEstoque = dataEntradaEstoque;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
