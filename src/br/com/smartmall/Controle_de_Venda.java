package br.com.smartmall;

public class Controle_de_Venda {
	private ItemProduto item;
	private Venda venda;
	private CupomFiscal cupom_fiscal;
	private float valorRecebido;
	private float valorTroco;
	
	public ItemProduto getItem() {
		return item;
	}
	public void setItem(ItemProduto item) {
		this.item = item;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public CupomFiscal getCupom_fiscal() {
		return cupom_fiscal;
	}
	public void setCupom_fiscal(CupomFiscal cupom_fiscal) {
		this.cupom_fiscal = cupom_fiscal;
	}
	public float getValorRecebido() {
		return valorRecebido;
	}
	public void setValorRecebido(float valorRecebido) {
		this.valorRecebido = valorRecebido;
	}
	public float getValorTroco() {
		return valorTroco;
	}
	public void setValorTroco(float valorTroco) {
		this.valorTroco = valorTroco;
	}
	
	public static void calcularTotalVenda(ItemProduto itemp,Venda venda){
		
	}
	
    public static void calcularTroco(float vReceido,Venda venda){
		
	}

    public static void geraCupomFiscal(CupomFiscal cupFisc,Venda venda){
	
    }

}
