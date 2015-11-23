package br.com.smartmall;

public class Endereco {
	private int idEndereco;
	private String bairro;
	private String numero;
	private String complemento;
	private String cep;	
	private String cidade;
	private String longadouro;
	
	//construtores
	public Endereco(){}
	
	public Endereco(int idEndereco){
		this.idEndereco = idEndereco;
	}
	
	//gets e sets
	public int getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getLongadouro() {
		return longadouro;
	}
	public void setLongadouro(String longadouro) {
		this.longadouro = longadouro;
	}
	
}
