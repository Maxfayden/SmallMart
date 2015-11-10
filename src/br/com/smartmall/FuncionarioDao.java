package br.com.smartmall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioDao {
	private static final String INSERT = "insert into Funcionario"
            + "(cpf,nome,login,senha,telefone,Endereço_idEndereço,gerente,Venda_idVenda)" 
			+ "(?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE = "update Funcionario set" 
	        + "cpf=?,nome=?,login=?,senha=?,telefone=?" 
			+ "where cpf=?";
	
	private static final String PESQUISAR = "select cpf,nome,login,senha,telefone,gerente,"
			+ "endereço.longradouro,endereço.bairro,endereço.numero,endereço.complemento,"
			+ "endereço.cep,endereço.cidade,venda.idVenda "
			+ "from funcionario inner join endereço "
			+ "on funcionario.Endereço_idEndereço = idEndereço "
			+ "inner join Venda on funcionario.Venda_idVenda = idVenda where cpf = ?";
	
	private static final String DELETE = "delete from Funcionario where cpf=?";
	private static final String PESQUISA_CPF = "select * from Funcionario where cpf=?";
	private static final String PESQUISA_LOGIN = "select * from Funcionario where login=? and senha=?";
	
	private Connection con;
	
	public FuncionarioDao(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public boolean add(Funcionario funcionario){
		boolean inserido = false;
		try{
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setString(1, funcionario.getCpf());
			stmt.setString(2, funcionario.getNome_funcionario());
			stmt.setString(3, funcionario.getLogin());
			stmt.setString(4, funcionario.getSenha());
			stmt.setString(5, funcionario.getTelefone());
			stmt.setInt(6, funcionario.getEndereco().getIdEndereco());
			stmt.setBoolean(7, funcionario.isGerente());
			stmt.setInt(8, funcionario.getVenda().getIdVenda());
			
			inserido = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return inserido;
	}
	
	public boolean update(Funcionario funcionario){
		boolean alterado = false;
		try{
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, funcionario.getCpf());
			stmt.setString(2, funcionario.getNome_funcionario());
			stmt.setString(3, funcionario.getLogin());
			stmt.setString(4, funcionario.getSenha());
			stmt.setString(5, funcionario.getTelefone());
			stmt.setString(6, funcionario.getCpf());
			
			alterado = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return alterado;
	}
	
	public List<Funcionario> pesquisar(String cpf){
		try{
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();
			PreparedStatement stmt = con.prepareStatement(PESQUISAR);
			stmt.setString(1, cpf);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				Endereco endereco = new Endereco();
				Venda venda = new Venda();
				
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setNome_funcionario(rs.getString("nome"));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setTelefone(rs.getString("telefone"));
				
				endereco.setLongadouro(rs.getString("longradouro"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCep(rs.getString("cep"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setCidade(rs.getString("cidade"));
				funcionario.setEndereco(endereco);
				
				venda.setIdVenda(rs.getInt("idVenda"));
				funcionario.setVenda(venda);
				
			}
			
			rs.close();
			stmt.close();
			return funcionarios;
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		
	}
	
	public boolean remove(String cpf){
		boolean removido = false;
		try{
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setString(1, cpf);
			
			removido = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return removido;
	}
	
	public boolean pesquisaCPF(String cpf){
		boolean encontrado = false;
		
		try{
			PreparedStatement stmt = con.prepareStatement(PESQUISA_CPF);
			stmt.setString(1, cpf);
			
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
	
	public boolean pesquisaLogin(String login, String senha){
		boolean encontrado = false;
		
		try{
			PreparedStatement stmt = con.prepareStatement(PESQUISA_LOGIN);
			stmt.setString(1, login);
			stmt.setString(1, senha);
			
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

