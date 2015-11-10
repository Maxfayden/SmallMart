package br.com.smartmall;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompraHasProdutoDao {
	private static final String INSERT = "insert into Compra_has_Produto" 
            + "(Compra_cod_compra,Produto_idProduto)" 
		    + "values (?,?)";
	
	//COLOCAR A QUERY
	private static final String RELATORIO = "select compra.dataDaCompra,compra.dataDeEntrega,"
	+ "compra.quant_lote,compra.valorTotalCompra,compra.valorLote,"
	+ "produto.nome,produto.numLote,fornecedor.cnpj,fornecedor.nome "
	+ "from compra_has_produto inner join compra on compra_cod_compra = cod_compra "
	+ "inner join fornecedor on fornecedor_cnpj = cnpj "
	+ "inner join produto on produto_idProduto = idProduto "
	+ "where (dataDaCompra >= ?) and (dataDaCompra <= ?)";
	
	private Connection con;
	
	public CompraHasProdutoDao(){
		con = new ConnectionFactory().getConnection();
	}
	
	public boolean add(Compra compra){
		boolean inserido = false;
		try{
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setInt(1, compra.getCod_compra());
			stmt.setInt(2, compra.getProduto().getId_produto());
			
			inserido = stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return inserido;
	}
	
	public List<Compra> relatorio(Date dataInicio, Date dataFim){
	
		try{
			List<Compra> compras = new ArrayList<Compra>();
			PreparedStatement stmt = con.prepareStatement(RELATORIO);
			stmt.setDate(1, new Date(dataInicio.getTime()));
			stmt.setDate(2, new Date(dataFim.getTime()));
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Compra compra = new Compra();
				Fornecedor fornecedor = new Fornecedor();
				Produto produto = new Produto();
				
				produto.setNome_prodt(rs.getString("nome"));
				produto.setNumLote(rs.getString("numLote"));
				compra.setProduto(produto);
				
				compra.setDataDaCompra(new Date(rs.getDate("dataDaCompra").getTime()));
				compra.setDataDeEntrega(new Date(rs.getDate("dataDeEntrega").getTime()));
				compra.setValorTotalCompra(rs.getFloat("valorTotalCompra"));
				compra.setQuant_lote(rs.getInt("quant_lote"));
				
				fornecedor.setCnpj(rs.getString("cnpj"));
				fornecedor.setNome_fornecedor(rs.getString("nome"));
				compra.setFornecedor(fornecedor);
			}
			
			rs.close();
			stmt.close();
			return compras;
		}catch (SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
	}
	

}
