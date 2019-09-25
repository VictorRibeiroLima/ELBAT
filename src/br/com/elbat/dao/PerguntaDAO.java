package br.com.elbat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.elbat.beans.Pergunta;
import br.com.elbat.conexao.Conectar;

/**
 * Classe responsável por manipular a tabela T_AM_PERGUNTA
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
 *@see br.com.elbat.beans.Pergunta
 *@see br.com.elbat.conexao.Conectar
 */

public class PerguntaDAO {
private Connection con;
private PreparedStatement pstm;
private ResultSet rs;
private Pergunta p=new Pergunta();
public PerguntaDAO() throws Exception{
	con=Conectar.conectar();
}
/**
 * Metodo responsavel por enviar um comando sql ao banco que grava uma nova Pergunta
 * @param p
 * @author Victor R. de Lima
 * @return String com o resultado
 * @throws Exception
 */
public String gravarNovaPergunta(Pergunta p)throws Exception{
	pstm=con.prepareStatement("INSERT INTO T_AM_PERGUNTA (CD_PERGUNTA,DS_PERGUNTA) VALUES(?,?)");
	pstm.setInt(1, p.getCdPergunta());
	pstm.setString(2, p.getPergunta());
	pstm.executeUpdate();
	return "pergunta registrada";
}
/**
 * Metodo responsavel por enviar um comando sql ao banco que consulta uma Pergunta especifica
 * @param pNumero
 * @author Victor R. de Lima
 * @return Pergunta 
 * @throws Exception
 */
public Pergunta consultarPorCd(int pNumero) throws Exception{
	pstm=con.prepareStatement("SELECT CD_PERGUNTA,DS_PERGUNTA FROM T_AM_PERGUNTA WHERE CD_PERGUNTA=?");
	pstm.setInt(1, pNumero);
	rs=pstm.executeQuery();
	if(rs.next()) {
		p.setCdPergunta(rs.getInt("CD_PERGUNTA"));
		p.setPergunta(rs.getString("DS_PERGUNTA"));
	}
	return p;
}
/**
 * Metodo responsavel por enviar um comando sql ao banco que consulta todas as Perguntas
 * @author Victor R. de Lima
 * @return List de Perguntas
 * @throws Exception
 */
public List<Pergunta> consultarPergunta() throws Exception{
	pstm=con.prepareStatement("SELECT * FROM T_AM_PERGUNTA");
	List <Pergunta> listaPerguntas=new ArrayList<Pergunta>();
	rs=pstm.executeQuery();
	while(rs.next()) {
		listaPerguntas.add(new Pergunta(rs.getInt("CD_PERGUNTA"),rs.getString("DS_PERGUNTA")));
	}
	return listaPerguntas;
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
