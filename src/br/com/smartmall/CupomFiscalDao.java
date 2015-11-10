package br.com.smartmall;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CupomFiscalDao {
	private static final String INSERT = "insert into CupomFiscal" 
            + "(VendaItemProduto_Venda_idVenda,VendaItemProduto_ItemProduto_idItemProduto)"
			  + "values (?,?)";
	private static final String PESQUISAR ="";
	private Connection con;
	
	public CupomFiscalDao(){
		con = new ConnectionFactory().getConnection();
	}
	
	public boolean add(CupomFiscal cupomFiscal){
		boolean inserido = false;
		try {
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setInt(1, cupomFiscal.getVenda().getIdVenda());
			stmt.setInt(2, cupomFiscal.getVenda().getItemProduto().getIdItem());
			
			inserido = stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
		return inserido;
	}
	
	public List<CupomFiscal> gerarCupom(){
		
		try{
			List<CupomFiscal> cupom = new ArrayList<CupomFiscal>();
			PreparedStatement stmt = con.prepareStatement(PESQUISAR);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Venda venda = new Venda();
				ItemProduto itemProduto = new ItemProduto();
				Produto produto = new Produto();
				CupomFiscal cupomFiscal = new CupomFiscal();
				
				produto.setDescricao(rs.getString("descricao"));
				itemProduto.setProduto(produto);
				
				itemProduto.setCdg_barras(rs.getString("cod_barras"));
				itemProduto.setValorVenda(rs.getFloat("valorVenda"));
				venda.setItemProduto(itemProduto);
				
				venda.setDataDaVenda(new Date(rs.getDate("dataDaVenda").getTime()));
				venda.setIdVenda(rs.getInt("idVenda"));
				venda.setQuantItem(rs.getInt("quantItem"));
				venda.setTotalVenda(rs.getFloat("totalVenda"));
				cupomFiscal.setVenda(venda);
				cupomFiscal.setIdCumpom(rs.getInt("idCupom"));
				
			}
			rs.close();
			stmt.close();
			return cupom;
		}catch (SQLException e){
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.closeConnection(con);
		}
		
	}

}
