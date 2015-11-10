package br.com.smartmall;

import java.util.Date;

public class Venda {
	private int idVenda;
	private ItemProduto itemProduto;
	private int quantItem;
	private float totalVenda;
	private Date dataDaVenda;
	
	//construtores
	public Venda(){}
	
	public Venda(int idVenda){
		this.idVenda = idVenda;
	}
    
	//sets e gets
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public ItemProduto getItemProduto() {
		return itemProduto;
	}
	public void setItemProduto(ItemProduto itemProduto) {
		this.itemProduto = itemProduto;
	}
	public int getQuantItem() {
		return quantItem;
	}
	public void setQuantItem(int quantItem) {
		this.quantItem = quantItem;
	}
	public float getTotalVenda() {
		return totalVenda;
	}
	public void setTotalVenda(float totalVenda) {
		this.totalVenda = totalVenda;
	}
	public Date getDataDaVenda() {
		return dataDaVenda;
	}
	public void setDataDaVenda(Date dataDaVenda) {
		this.dataDaVenda = dataDaVenda;
	}

	
	

}
