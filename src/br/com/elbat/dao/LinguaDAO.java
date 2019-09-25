package br.com.elbat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.elbat.beans.Lingua;
import br.com.elbat.conexao.Conectar;

/**
 * Classe responsável por manipular a tabela T_AM_LINGUA
 * @author Victor R. de Lima
 * @version 1.0
 * @since 1.0
 * 
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * 
 * @see java.util.ArrayList
 * @see java.util.List
 * 
 * @see br.com.elbat.beans.Lingua
 * @see br.com.elbat.conexao.Conectar
 */

public class LinguaDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstm;
	public LinguaDAO() throws Exception{
		con=Conectar.conectar();
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que grava uma nova Lingua
	 * @param li
	 * @author Victor R. de Lima
	 * @return String com o resultado
	 * @throws Exception
	 */
	public String gravarLingua(Lingua li) throws Exception{
		pstm=con.prepareStatement("INSERT INTO T_AM_LINGUA(CD_LINGUA,DS_LINGUA,SGL_LINGUA) VALUES(?,?,?)");
		pstm.setInt(1, li.getCdLingua());
		pstm.setString(2, li.getLingua());
		pstm.setString(3,li.getSglLingua());
		pstm.executeUpdate();
		return "Lingua registrada";
	}
	/**
	 * Metodo responsavel por enviar um comando sql que consulta todas as Linguas
	 * @return List de linguas
	 * @author Victor R. de Lima
	 * @throws Exception
	 */
	public List<Lingua> consultarLingua()throws Exception{
		pstm=con.prepareStatement("SELECT * FROM T_AM_LINGUA");
		List<Lingua> listaLinguas=new ArrayList<Lingua>();
		rs=pstm.executeQuery();
		while(rs.next()) {
			listaLinguas.add(new Lingua(rs.getInt("CD_LINGUA"),rs.getString("DS_LINGUA"),rs.getString("SGL_LINGUA")));
		}
		return listaLinguas;
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que consulta uma lingua especifica
	 * @param pNumero
	 * @return Lingua
	 * @author Victor R. de Lima
	 * @throws Exception
	 */
	public Lingua consultasPorCd(int pNumero) throws Exception{
		Lingua li=new Lingua();
		pstm=con.prepareStatement("SELECT * FROM T_AM_LINGUA WHERE CD_LINGUA=?");
		pstm.setInt(1, pNumero);
		rs=pstm.executeQuery();
		if(rs.next()) {
			li.setAll(rs.getInt("CD_LINGUA"),rs.getString("DS_LINGUA"),rs.getString("SGL_LINGUA"));
		}
		return li;
	}
	/**
	 * Metodo que fecha a conexão com o banco
	 * @throws Exception
	 * @author Victor R. de Lima
	 */
	public void fecharConexao()throws Exception{
		con.close();
	}
}
