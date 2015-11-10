package br.com.smartmall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDao {
	private static final String INSERT = "insert into Endere�o" 
              + "(longradouro,bairro,numero,complemento,cep,cidade)"
			  + "values (?,?,?,?,?,?)";
	
	private static final String UPDATE = "update Endere�o set"
			  + "longradouro=?, bairro=?, numero=?, complemento=?, cep=?, cidade=?"
			  + "where idEndere�o =?";
	
	private static final String PESQUISAR = "select * from Endere�o" 
	                                               + "where idEndere�o=?";
	
	private static final String DELETE = "delete from Endereco where iEndere�o=?";
	
	private Connection con;
	
	
	public EnderecoDao(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public boolean add(Endereco endereco){
		boolean inserido = false;
		try {
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setString(1, endereco.getLongadouro());
			stmt.setString(2, endereco.getBairro());
			stmt.setString(3, endereco.getNumero());
			stmt.setString(4, endereco.getComplemento());
			stmt.setString(5, endereco.getCep());
			stmt.setString(6, endereco.getCidade());
			
			inserido = stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return inserido;
	}
	
	public boolean update(Endereco endereco){
		boolean update = false;
		try {
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, endereco.getLongadouro());
			stmt.setString(2, endereco.getBairro());
			stmt.setString(3, endereco.getNumero());
			stmt.setString(4, endereco.getComplemento());
			stmt.setString(5, endereco.getCep());
			stmt.setString(6, endereco.getCidade());
			stmt.setInt(7,endereco.getIdEndereco());
			
			update = stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return update;
	}
	
	public List<Endereco> pesquisar(int idEndereco){
		
		try {
			List<Endereco> enderecos = new ArrayList<Endereco>();
			PreparedStatement stmt = con.prepareStatement(PESQUISAR);
			stmt.setInt(1, idEndereco);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Endereco endereco = new Endereco();
				endereco.setLongadouro(rs.getString("longradouro"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setCep(rs.getString("cep"));
				endereco.setCidade(rs.getString("cidade"));
				enderecos.add(endereco);
			}
			rs.close();
			stmt.close();
			return enderecos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		
	}
	
	public boolean remove(int idEndereco){
		boolean removido = false;
		try{
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, idEndereco);
			
			removido = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return removido;
	}

}
