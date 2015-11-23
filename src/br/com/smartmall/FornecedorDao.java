package br.com.smartmall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDao {
	private static final String INSERT = "insert into Fornecedor" 
            + "(cnpj,nome,telefone,Endereço_idEndereço)" 
		    + "values (?,?,?,?)";
	
	private static final String UPDATE = "update Fornecedor set" 
	             + "cnpj=?,nome=?,telefone=?" 
			     + "where cnpj=?";
	
	private static final String PESQUISAR = "select nome,cnpj,telefone,"
	    + "endereço.longradouro,endereço.bairro,endereço.numero,"
	    + "endereço.complemento,endereço.cep,endereço.cidade "
	    + "from fornecedor inner join endereço "
	    + "on Endereço_idEndereço = idEndereço where cnpj=?";
	
	private static final String DELETE = "delete from Fornecedor where cnpj=?";
	private static final String PESQUISA_CNPJ = "select * from Fornecedor where cnpj = ?";
	
	private Connection con;
	
	
	public FornecedorDao(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public boolean add(Fornecedor fornecedor){
		boolean inserido = false;
		try {
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setString(1, fornecedor.getCnpj());
			stmt.setString(2, fornecedor.getNome_fornecedor());
			stmt.setString(3, fornecedor.getTelefone());
			stmt.setInt(4, fornecedor.getEndereco().getIdEndereco());
			
			inserido = stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return inserido;
	}
	
	public boolean update(Fornecedor fornecedor){
		boolean alterado = false;
		try{
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, fornecedor.getCnpj());
			stmt.setString(2, fornecedor.getNome_fornecedor());
			stmt.setString(3, fornecedor.getTelefone());
			stmt.setString(4, fornecedor.getCnpj());
			
			alterado = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return alterado;
	}
	
	public List<Fornecedor> pesquisar(String cnpj){
		
		try{
			List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
			PreparedStatement stmt = con.prepareStatement(PESQUISAR);
			stmt.setString(1, cnpj);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setCnpj(rs.getString("cnpj"));
				fornecedor.setNome_fornecedor(rs.getString("nome"));
				fornecedor.setTelefone(rs.getString("telefone"));
				fornecedor.setEndereco(new Endereco(rs.getInt("Endereço_idEndereço")));
				
				fornecedores.add(fornecedor);
			}
			
			rs.close();
			stmt.close();
			return fornecedores;
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		
	}
	
	public boolean remove(String cnpj){
		boolean removido;
		try{
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setString(1, cnpj);
			
			removido = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return removido;
	}
	
	public boolean pesquisaCNPJ(String cnpj){
		boolean encontrado = false;
		
		try{
			PreparedStatement stmt = con.prepareStatement(PESQUISA_CNPJ);
			stmt.setString(1, cnpj);
			
			ResultSet rs = stmt.executeQuery();
			
			
				if(rs.next()){
					encontrado = true;
				}
			
			rs.close();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return encontrado;
	}


}
