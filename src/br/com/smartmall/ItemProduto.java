package br.com.smartmall;

import java.util.Date;

public class ItemProduto {
	private int idItem;
	private String cdg_barras;
	private String tipo_produto;
	private Date dataValidade;
	private float valorVenda;
	private Produto produto;
	
	//construtores
	public ItemProduto(){}
	
	public ItemProduto(int idItem){
		this.idItem = idItem;
	}
	
	//gets e sets
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public String getCdg_barras() {
		return cdg_barras;
	}
	public void setCdg_barras(String cdg_barras) {
		this.cdg_barras = cdg_barras;
	}
	public String getTipo_produto() {
		return tipo_produto;
	}
	public void setTipo_produto(String tipo_produto) {
		this.tipo_produto = tipo_produto;
	}
	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	public float getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(float valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	


}
