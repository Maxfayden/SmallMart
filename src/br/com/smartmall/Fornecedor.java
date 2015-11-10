package br.com.smartmall;

public class Fornecedor {

	private String nome_fornecedor;
	private String cnpj;
	private Endereco endereco;
	private String telefone;
	
	//construtore
	public Fornecedor(){}
	
	public Fornecedor(String cnpj){
		this.cnpj = cnpj;
	}
	
	//gets e sets
	public String getNome_fornecedor() {
		return nome_fornecedor;
	}
	public void setNome_fornecedor(String nome_fornecedor) {
		this.nome_fornecedor = nome_fornecedor;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
