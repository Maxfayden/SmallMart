package br.com.smartmall;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class ItemProdutoDao {
	//Eu criei as querys aqui pq eu acho que assim deixa o código organizado
	private static final String INSERT = "insert into ItemProduto" 
            + "(cod_barras,tipo_prod,dataValidade,valorVenda,Produto_idProduto)"
			+ "values (?,?,?,?,?)";
	
	private static final String UPDATE = "update ItemProduto set" 
	        + "cod_barras=?, tipo_prod=?,dataValidade=?,valorVenda=?" 
			+ "where idItemProduto=?";
	
	private static final String PESQUISAR = "select *,produto.* from itemproduto" 
	         + "inner join produto on Produto_idProduto = idProduto "
	         + "where cod_barras=?;";
	
	private static final String RELATORIO = "select tipo_prod,valorVenda,dataValidade,"
	    + "produto.nome,produto.quantidade,produto.numLote,"
	    + "produto.dataEntradaEstoque from itemProduto "
	    + "inner join produto on itemproduto.Produto_idProduto = idProduto "
	    + "where (dataEntradaEstoque >= ?) and (dataEntradaEstoque <= ?)";


	private static final String PESQUISA_CODIGO = "select * from ItemProduto where cod_barras=?";
	
	private Connection con;
	
	public ItemProdutoDao(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public boolean add(ItemProduto itemProduto){
		boolean inserido = false;
		try{
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setString(1, itemProduto.getCdg_barras());
			stmt.setString(2, itemProduto.getTipo_produto());
			stmt.setDate(3, new Date(itemProduto.getDataValidade().getTime()));
			stmt.setFloat(4, itemProduto.getValorVenda());
			stmt.setInt(5, itemProduto.getProduto().getId_produto());
			
			inserido = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return inserido;
	}
	
	public boolean update(ItemProduto itemProduto){
		boolean alterado = false;
		try{
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, itemProduto.getCdg_barras());
			stmt.setString(2, itemProduto.getTipo_produto());
			stmt.setDate(3, new Date(itemProduto.getDataValidade().getTime()));
			stmt.setFloat(4, itemProduto.getValorVenda());
			stmt.setInt(5, itemProduto.getIdItem());
			
			alterado = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return alterado;
	}
	
	
	public List<ItemProduto> relatorio(Date dataInicio, Date dataFim){
		
		try{
			List<ItemProduto> itens = new ArrayList<ItemProduto>();
			PreparedStatement stmt = con.prepareStatement(RELATORIO);
			stmt.setDate(1, new Date(dataInicio.getTime()));
			stmt.setDate(2, new Date(dataFim.getTime()));
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Produto produto = new Produto();
				ItemProduto itemProduto = new ItemProduto();
				
				produto.setNome_prodt(rs.getString("nome"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setNumLote(rs.getString("numLote"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEntradaEstoque"));
				produto.setDataEntradaEstoque(data);
				itemProduto.setProduto(produto);
				
				itemProduto.setTipo_produto(rs.getString("tipo_prod"));
				itemProduto.setValorVenda(rs.getFloat("valorVenda"));
				itemProduto.setDataValidade(rs.getDate("dataValidade"));
				
				itens.add(itemProduto);
			}
			
			rs.close();
			stmt.close();
			return itens;
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		
	}
	
	public List<ItemProduto> pesquisar(String codigoBarras){
		
		try{
			List<ItemProduto> itens = new ArrayList<ItemProduto>();
			PreparedStatement stmt = con.prepareStatement(PESQUISAR);
			stmt.setString(1, codigoBarras);
			
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				ItemProduto itemProduto = new ItemProduto();
				Produto produto = new Produto();
				
				produto.setId_produto(rs.getInt("idProduto"));
				produto.setNome_prodt(rs.getString("nome"));
				produto.setMarca(rs.getString("marca"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setNumLote(rs.getString("numLote"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEntradaEstoque"));
				produto.setDataEntradaEstoque(data);
				itemProduto.setProduto(produto);
				
				itemProduto.setIdItem(rs.getInt("idItemProduto"));
				itemProduto.setCdg_barras(rs.getString("cod_barras"));
				itemProduto.setTipo_produto(rs.getString("tipo_prod"));
				itemProduto.setDataValidade(rs.getDate("dataValidade"));
				itemProduto.setValorVenda(rs.getFloat("valorVenda"));
				
			}
			
			rs.close();
			stmt.close();
			return itens;
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		
	}
	
	public boolean pesquisaCodigoBarras(String codBarras){
		boolean encontrado = false;
		try{
			PreparedStatement stmt = con.prepareStatement(PESQUISA_CODIGO);
			stmt.setString(1, codBarras);
			
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
