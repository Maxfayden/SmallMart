package br.com.smartmall;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaItemProdutoDao {
	private static final String INSERT= "insert into Venda_has_ItemProduto" 
            + "(Venda_idVenda,ItemProduto_idItemProduto)" + "(?,?)";
	
	private static final String PESQUISAR= "select produto.descricao,venda.quantItem,itemproduto.valorVenda," 
	       + "venda.dataDaVenda,venda.totalVenda "
	       + "from venda_has_itemproduto inner join venda "
	       + "on Venda_idVenda = idVenda inner join itemproduto "
	       + "on ItemProduto_idItemProduto = idItemProduto "
	       + "inner join produto on Produto_idProduto = idProduto;";
	
	private static final String RELATORIO= "select venda.idVenda,produto.descricao,venda.quantItem,"
	      + "produto.numLote,itemproduto.valorVenda,venda.dataDaVenda,"
	      + "venda.totalVenda from venda_has_itemproduto" 
	      + "inner join venda on Venda_idVenda = idVenda" 
	      + "inner join itemproduto on ItemProduto_idItemProduto = idItemProduto" 
	      + "inner join produto on Produto_idProduto = idProduto where dataDaVenda = ?;";
	
	private Connection con;
	
	public VendaItemProdutoDao(){
		con = new ConnectionFactory().getConnection();
	}
	
	public boolean add(Venda venda){
		boolean inserido = false;
		
		try{
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setInt(1, venda.getIdVenda());
			stmt.setInt(2, venda.getItemProduto().getIdItem());
			
			inserido = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return inserido;
	}
	
	//Pesquisa todas as vendas ja feita
	public List<Venda> pesquisar(){
		
		try{
			List<Venda> vendas = new ArrayList<Venda>();
			PreparedStatement stmt = con.prepareStatement(PESQUISAR);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Venda venda = new Venda();
				ItemProduto itemProduto = new ItemProduto();
				Produto produto = new Produto();
				
				produto.setDescricao(rs.getString("descricao"));
				itemProduto.setProduto(produto);
				
				itemProduto.setValorVenda(rs.getFloat("valorVenda"));
				venda.setItemProduto(itemProduto);
				
				venda.setQuantItem(rs.getInt("quantItem"));
				venda.setTotalVenda(rs.getFloat("totalVenda"));
				venda.setDataDaVenda(new Date(rs.getDate("dataDaVenda").getTime()));
				vendas.add(venda);
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
	
	//Pesquisa que retorna os dados do relatorio
	public List<Venda> relatorio(Date dataVenda){
		
		try{
			List<Venda> vendas = new ArrayList<Venda>();
			PreparedStatement stmt = con.prepareStatement(RELATORIO);
			stmt.setDate(1, new Date(dataVenda.getTime()));
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Venda venda = new Venda();
				ItemProduto itemProduto = new ItemProduto();
				Produto produto = new Produto();
				
				produto.setDescricao(rs.getString("descricao"));
				produto.setNumLote(rs.getString("numLote"));
				itemProduto.setProduto(produto);
				
				itemProduto.setValorVenda(rs.getFloat("valorVenda"));
				venda.setItemProduto(itemProduto);
				
				venda.setIdVenda(rs.getInt("idVenda"));
				venda.setQuantItem(rs.getInt("quantItem"));
				venda.setTotalVenda(rs.getFloat("totalVenda"));
				venda.setDataDaVenda(new Date(rs.getDate("dataDaVenda").getTime()));
				
				vendas.add(venda);
				
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
