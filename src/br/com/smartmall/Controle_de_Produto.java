package br.com.smartmall;

public class Controle_de_Produto {
	private Produto produto;
	private ItemProduto item_de_produto;
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public ItemProduto getItem_de_produto() {
		return item_de_produto;
	}
	public void setItem_de_produto(ItemProduto item_de_produto) {
		this.item_de_produto = item_de_produto;
	}
	
	public static void geraRelatorio(Produto produto,ItemProduto item_de_produto){
		
	}
	public static String cadastrarProduto(Produto produto)
	{
		return null;
	}
	public static String atualizarProduto(Produto produto)
	{
		return null;
	}
	public static String pesquisarProduto(Produto produto)
	{
		return null;
	}
	public static String excluirProduto(Produto produto)
	{
		return null;
	}
	public static String verificarProduto(Produto produto)
	{
		return null;
	}
	public static Produto verificarValidade(Produto produto)
	{
		return null;
	}
	public static Produto verificarQuantidade(Produto produto)
	{
		return null;
	}
}
