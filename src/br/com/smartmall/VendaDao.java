package br.com.smartmall;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDao {
	private static final String INSERT= "insert into Venda" 
              + "(quantItem,totalVenda,dataDaVenda)" + "(?,?,?)";
	
	private static final String UPDATE= "update Venda set" 
	          + "quantItem=?,totalVenda=?,dataDaVenda=?" + "where idVenda=?";

	private static final String PESQUISAR = "select * from Venda where idVenda=?";

	private Connection con;
	
	public VendaDao(){
		con = new ConnectionFactory().getConnection();
	}
	
	public boolean add(Venda venda){
		boolean inserido = false;
		try{
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setInt(1, venda.getQuantItem());
			stmt.setFloat(2, venda.getTotalVenda());
			stmt.setDate(3, new Date(venda.getDataDaVenda().getTime()));;
			
			inserido = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return inserido;
	}
	
	//REVER METODO DE ATUALIZAR
	public boolean update(Venda venda){
		boolean alterado = false;
		try{
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setInt(1, venda.getQuantItem());
			stmt.setFloat(2, venda.getTotalVenda());
			stmt.setDate(3, new Date(venda.getDataDaVenda().getTime()));
			stmt.setInt(4, venda.getIdVenda());
			
			alterado = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return alterado;
	}
	
	public List<Venda> pesquisar(int idVenda){
		
		try{
			List<Venda> vendas = new ArrayList<Venda>();
			PreparedStatement stmt = con.prepareStatement(PESQUISAR);
			stmt.setInt(1, idVenda);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Venda venda = new Venda();
				venda.setIdVenda(rs.getInt("idVenda"));
				venda.setDataDaVenda(new Date(rs.getDate("dataDaVenda").getTime()));
				venda.setQuantItem(rs.getInt("quantItem"));
				venda.setTotalVenda(rs.getFloat("totalVenda"));
			}
			rs.close();
			stmt.close();
			return vendas;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
	}
	

	
	
	

}
