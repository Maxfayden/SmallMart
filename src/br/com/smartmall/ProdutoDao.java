package br.com.smartmall;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




public class ProdutoDao {
	private static final String PESQUISAR = "select * from produto where idProduto = ?";
	private Connection con;
	
	public ProdutoDao(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	//Metodo para inserir o produto no banco
	public boolean add(Produto produto){
		boolean inserido = false;
		try{
			PreparedStatement stmt = this.con.prepareStatement("insert into Produto "
					+ "(idProduto,nome,marca,numLote,quantidade,descricao,dataEntradaEstoque)"
					+ " values(?,?,?,?,?,?,?)");
			stmt.setInt(1,produto.getId_produto());
			stmt.setString(2,produto.getNome_prodt());
			stmt.setString(3, produto.getMarca());
			stmt.setString(4, produto.getNumLote());
			stmt.setInt(5, produto.getQuantidade());
			stmt.setString(6, produto.getDescricao());
			stmt.setDate(7, new Date(produto.getDataEntradaEstoque().getTimeInMillis()));
			
			inserido = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return inserido;
	}
	

	//Metodo que atualiza os dados de um produto
	public boolean update(Produto produto){
		boolean alterado = false;
		try{
			String sql = "update Produto set "
					+ "nome=?, marca=?, numLote=?, quantidade=?, descricao=? , dataEntradaEstoque=? " +
		             "where idProduto=?";
			PreparedStatement stmt = this.con.prepareStatement(sql);
			stmt.setString(1, produto.getNome_prodt());
			stmt.setString(2, produto.getMarca());
			stmt.setString(3, produto.getNumLote());
			stmt.setInt(4, produto.getQuantidade());
			stmt.setString(5, produto.getDescricao());
			stmt.setDate(6, new Date(produto.getDataEntradaEstoque().getTimeInMillis()));
			stmt.setInt(7, produto.getId_produto());
			
			alterado = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return alterado;
	}
	
	public List<Produto> pesquisar(int idProduto){
		try{
			List<Produto> produtos = new ArrayList<Produto>();
			PreparedStatement stmt = con.prepareStatement(PESQUISAR);
			stmt.setInt(1, idProduto);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Produto produto = new Produto();
				
				produto.setId_produto(rs.getInt("idProduto"));
				produto.setNome_prodt(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setMarca(rs.getString("marca"));
				produto.setNumLote(rs.getString("numLote"));
				produto.setQuantidade(rs.getInt("quantidade"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEntradaEstoque"));
				produto.setDataEntradaEstoque(data);
			}
			rs.close();
			stmt.close();
			return produtos;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
	}
	
	
}

