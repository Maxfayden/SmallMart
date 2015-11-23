package br.com.smartmall;

import java.util.List;

public class Controle_de_Fornecedor {

	private Fornecedor fornecedor;
	private FornecedorDao fornecedordao;
        private EnderecoDao enderecodao;
        
        //Construtor
        public Controle_de_Fornecedor() {
            fornecedor = new Fornecedor();
            fornecedordao = new FornecedorDao();
            enderecodao = new EnderecoDao();
        }
        
	public Fornecedor getFornecedor() {
	    return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
	    this.fornecedor = fornecedor;
	}
	
        public Endereco getEndereco() {
	    return fornecedor.getEndereco();
	}
	public void setEndereco(Endereco endereco) {
	    this.fornecedor.setEndereco(endereco);
	}
        
	public boolean cadastrarFornecedor(){
            return fornecedordao.add(fornecedor);
	}
	public boolean atualizarFornecedor(){
	    return fornecedordao.update(fornecedor);
	}
	
	public List<Fornecedor> pesquisarFornecedor(){
            return fornecedordao.pesquisar(fornecedor.getCnpj());
	}
	public boolean excluirFornecedor(){
	    return fornecedordao.remove(fornecedor.getCnpj());
	}
        
        public boolean cadastrarEndereco(){
            return enderecodao.add(fornecedor.getEndereco());
        }
        
        public boolean atualizarEndereco(){
        return enderecodao.update(fornecedor.getEndereco());
        }
        
        public List<Endereco> pesquisarEndereco(){
            return enderecodao.pesquisar(fornecedor.getEndereco().getIdEndereco());
	}
        
        public boolean excluirEndereco(){
        return enderecodao.remove(fornecedor.getEndereco().getIdEndereco());
        }
	
}