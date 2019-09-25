package br.com.elbat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.elbat.beans.Dificuldade;
import br.com.elbat.conexao.Conectar;

/**
 * Classe reponsável por manipular a tabela T_AM_DIFICULDADE
 * @author Victor R. de Lima
 * @version 1.0
 * @since 1.0
 * 
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * @see java.util.ArrayList
 * @see java.util.List
 * 
 * @see br.com.elbat.beans.Dificuldade
 * @see br.com.elbat.conexao.Conectar
 */

public class DificuldadeDAO {
private Connection con;
private PreparedStatement pstm;
private ResultSet rs;
private Dificuldade d=new Dificuldade();
public DificuldadeDAO()throws Exception {
	con=Conectar.conectar();
}
/**
 * Metodo responsavel por enviar um comando sql ao banco que grava uma nova dificuldade
 * @param d
 * @author Victor R. de Lima
 * @return String com o resultado
 * @throws Exception
 */
public String gravarDificuldade(Dificuldade d)throws Exception{
	pstm=con.prepareStatement("INSERT INTO T_AM_DIFICULDADE (CD_DIFICULDADE,DS_DIFICULDADE) VALUES(?,?)");
	pstm.setInt(1, d.getCdDificuldade());
	pstm.setString(2, d.getDificuldade());
	pstm.executeUpdate();
	return "Dificuldade Registrada";
}
/**
 * Metodo responsavel por enviar um comando sql ao banco que consulta uma dificuldade especifica
 * @param pNumero
 * @author Victor R. de Lima
 * @return Dificuldade
 * @throws Exception
 */
public Dificuldade consultarPorCd(int pNumero) throws Exception{
	pstm=con.prepareStatement("SELECT * FROM T_AM_DIFICULDADE WHERE CD_DIFICULDADE=?");
	pstm.setInt(1, pNumero);
	rs=pstm.executeQuery();
	if(rs.next()) {
		d=new Dificuldade(rs.getInt("CD_DIFICULDADE"),rs.getString("DS_DIFICULDADE"));
	}
	return d;
}
/**
 *  Metodo responsavel por enviar um comando sql ao banco que consulta todas as dificuldade
 * @return List de Dificuldades
 * @author Victor R. de Lima
 * @throws Exception
 */
public List<Dificuldade> consultarDificuldades()throws Exception{
	pstm=con.prepareStatement("SELECT * FROM T_AM_DIFICULDADE");
	List<Dificuldade> listaDificuldades=new ArrayList<Dificuldade>();
	rs=pstm.executeQuery();
	while(rs.next()) {
		listaDificuldades.add(new Dificuldade(rs.getInt("CD_DIFICULDADE"),rs.getString("DS_DIFICULDADE")));
	}
	return listaDificuldades;
}
/**
 * Metodo que fecha a conexão com o banco
 * @author Victor R. de Lima
 * @throws Exception
 */
public void fecharConexao()throws Exception{
	con.close();
}
}
