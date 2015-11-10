package br.com.smartmall;

import java.util.Date;

public class Compra {
	private int cod_compra;	
	private Produto produto;
	private int quant_lote;
	private Fornecedor fornecedor;
	private Date dataDaCompra;
	private Date dataDeEntrega;
	private float valorTotalCompra;
	
	//construtores
	public Compra(){}
	
	public Compra(int cod_compra){
		this.cod_compra = cod_compra;
	}
	
	//gets e sets
	public int getCod_compra() {
		return cod_compra;
	}
	public void setCod_compra(int cod_compra) {
		this.cod_compra = cod_compra;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuant_lote() {
		return quant_lote;
	}
	public void setQuant_lote(int quant_lote) {
		this.quant_lote = quant_lote;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Date getDataDaCompra() {
		return dataDaCompra;
	}
	public void setDataDaCompra(Date dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}
	public Date getDataDeEntrega() {
		return dataDeEntrega;
	}
	public void setDataDeEntrega(Date dataDeEntrega) {
		this.dataDeEntrega = dataDeEntrega;
	}
	public float getValorTotalCompra() {
		return valorTotalCompra;
	}
	public void setValorTotalCompra(float valorTotalCompra) {
		this.valorTotalCompra = valorTotalCompra;
	}
	
	

}
