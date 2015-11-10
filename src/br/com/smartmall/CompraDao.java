package br.com.smartmall;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CompraDao {
	private static final String INSERT = "insert into compra " +
            "(quant_lote,dataDaCompra,dataDeEntrega,valorTotalCompra,Fornecedor_cnpj)" +
            " values (?,?,?,?,?,?)";

	private static final String PESQUISAR = "select * from compra where cod_compra = ?";

	private static final String UPDATE = "update compra set" 
	                + "quant_lote=?,dataDaCompra=?,dataDeEntrega=?,valorTotalCompra=?"
			        + "where cod_compra=?";
		
	private Connection con;
	
	public CompraDao(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public boolean add(Compra compra){		
		boolean inserido = false;
		 try {
	         // prepared statement para inserção
	         PreparedStatement stmt = con.prepareStatement(INSERT);
	         // seta os valores
	         stmt.setInt(1, compra.getQuant_lote());
	         stmt.setDate(2, new Date(compra.getDataDaCompra().getTime()));
	         stmt.setDate(3, new Date(compra.getDataDeEntrega().getTime()));
	         stmt.setFloat(4, compra.getValorTotalCompra());
	         stmt.setString(5, compra.getFornecedor().getCnpj());
	         // executa
	        inserido =  stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }finally{
	    	 ConnectionFactory.closeConnection(con);
	     }
		 
		 return inserido;
	}
	
	public List<Compra> pesquisar(int cod_compra){
		try{
			List<Compra> compras = new ArrayList<Compra>();
			PreparedStatement stmt = con.prepareStatement(PESQUISAR);
			stmt.setInt(1, cod_compra);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Compra compra = new Compra();
				compra.setCod_compra(rs.getInt("cod_compra"));
				compra.setDataDaCompra(new Date(rs.getDate("dataDaCompra").getTime()));
				compra.setDataDeEntrega(new Date(rs.getDate("dataDeEntrega").getTime()));
				compra.setQuant_lote(rs.getInt("quant_lote"));
				compra.setValorTotalCompra(rs.getFloat("valorTotalCompra"));
				compra.setFornecedor(new Fornecedor(rs.getString("Fornecedor_cnpj")));
				
				compras.add(compra);
			}
			rs.close();
			stmt.close();
			return compras;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
	}
	
	public boolean update(Compra compra){
		boolean alterado = false;
		try{
			 PreparedStatement stmt = con.prepareStatement(UPDATE);
			 stmt.setInt(1, compra.getQuant_lote());
	         stmt.setDate(2, new Date(compra.getDataDaCompra().getTime()));
	         stmt.setDate(3, new Date(compra.getDataDeEntrega().getTime()));
	         stmt.setFloat(4, compra.getValorTotalCompra());
	         stmt.setInt(5, compra.getCod_compra());
	         
	         alterado = stmt.execute();
	         stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		return alterado;
	}
	

}
