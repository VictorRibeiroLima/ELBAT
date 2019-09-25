package br.com.elbat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.elbat.beans.Pergunta;
import br.com.elbat.beans.Usuario;
import br.com.elbat.conexao.Conectar;

/**
 * Classe responsável por manipular a tabela T_AM_USUARIO<BR>
 * 2.0 adicionado metodo consultarPorEmail
 *@author Victor R. de Lima
 * @version 2.0
 * @since 1.0
 * 
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * 
 * @see java.util.ArrayList
 * @see java.util.List
 *
 * @see br.com.elbat.beans.Pergunta
 * @see br.com.elbat.beans.Usuario
 * @see br.com.elbat.conexao.Conectar
 */

public class UsuarioDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstm;
	private Usuario us=null;
	private List<Usuario> listaUsuarios=null;
	public UsuarioDAO() throws Exception{
		con=Conectar.conectar();
	}
	/**
	 * Metodo responsavel por enviar os comandos sql de registro ao banco de dados
	 * @param  us
	 * @author Victor R. de Lima
	 * @return String com confirmação de registro
	 * @throws Exception
	 */
	public String gravarUsuario(Usuario us) throws Exception{
		pstm=con.prepareStatement("INSERT INTO T_AM_USUARIO(CD_USUARIO,CD_PERGUNTA,DS_EMAIL,SGL_GENERO,DS_NOME,DS_APELIDO,DS_SENHA,DS_RESP_PERGUNTA,ADM_SISTEMA) VALUES(?,?,?,?,?,?,?,?,?)");
		pstm.setInt(1,us.getCdUsuario());
		pstm.setInt(2, us.getPergunta().getCdPergunta());
		pstm.setString(3, us.getEmail());
		pstm.setString(4, String.valueOf(us.getGenero()));
		pstm.setString(5, us.getNome());
		pstm.setString(6, us.getApelido());
		pstm.setString(7, us.getSenha());
		pstm.setString(8, us.getResposta());
		pstm.setString(9, String.valueOf(us.getAdm()));
		pstm.executeUpdate();
		return "Usuario registrada";
	}
	/**
	 * Metodo responsavel por enviar os comandos sql ao banco para realizar uma consulta de todos os usuarios
	 * @author Victor R. de Lima
	 * @return List de usuarios
	 * @throws Exception
	 */
	public List<Usuario> consultarUsuarios()throws Exception{
		pstm=con.prepareStatement("SELECT T_AM_USUARIO.*,T_AM_PERGUNTA.* FROM T_AM_USUARIO INNER JOIN T_AM_PERGUNTA ON T_AM_USUARIO.CD_PERGUNTA=T_AM_PERGUNTA.CD_PERGUNTA");
		listaUsuarios=new ArrayList<Usuario>();
		rs=pstm.executeQuery();
		while(rs.next()) {
			listaUsuarios.add(new Usuario(rs.getInt("CD_USUARIO"),new Pergunta(rs.getInt("CD_PERGUNTA"),rs.getString("DS_PERGUNTA")) , rs.getString("DS_EMAIL"), rs.getString("SGL_GENERO").charAt(0),
					rs.getString("DS_NOME"), rs.getString("DS_APELIDO"), rs.getString("DS_SENHA"), rs.getString("DS_RESP_PERGUNTA"),rs.getString("ADM_SISTEMA").charAt(0)));
		}
		return listaUsuarios;
	}
	/**
	 * Metodo responsavel por enviar os comandos sql ao banco para realizar uma consulta de todos os usuarios registrados com uma pergunta especifica 
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return List de Usuarios
	 * @throws Exception
	 */
	public List<Usuario> consultarUsuarioPorPergunta(int pNumero)throws Exception{
		pstm=con.prepareStatement("SELECT T_AM_USUARIO.*,T_AM_PERGUNTA.* FROM T_AM_USUARIO INNER JOIN T_AM_PERGUNTA ON T_AM_USUARIO.CD_PERGUNTA=T_AM_PERGUNTA.CD_PERGUNTA WHERE T_AM_USUARIO.CD_PERGUNTA=?");
		pstm.setInt(1, pNumero);
		rs=pstm.executeQuery();
		listaUsuarios=new ArrayList<Usuario>();
		while(rs.next()) {
			listaUsuarios.add(new Usuario(rs.getInt("CD_USUARIO"),new Pergunta(rs.getInt("CD_PERGUNTA"),rs.getString("DS_PERGUNTA")) , rs.getString("DS_EMAIL"), rs.getString("SGL_GENERO").charAt(0),
					rs.getString("DS_NOME"), rs.getString("DS_APELIDO"), rs.getString("DS_SENHA"), rs.getString("DS_RESP_PERGUNTA"),rs.getString("ADM_SISTEMA").charAt(0)));
		}
		return listaUsuarios;
	}
	/**
	 * Metodo responsavel por enviar os comandos sql ao banco para realizar uma consulta de um Usuario especifico 
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return Usuario 
	 * @throws Exception
	 */
	public Usuario consultasPorCd(int pNumero) throws Exception{
		pstm=con.prepareStatement("SELECT T_AM_USUARIO.*,T_AM_PERGUNTA.* FROM T_AM_USUARIO INNER JOIN T_AM_PERGUNTA ON T_AM_USUARIO.CD_PERGUNTA=T_AM_PERGUNTA.CD_PERGUNTA WHERE T_AM_USUARIO.CD_USUARIO=?");
		pstm.setInt(1, pNumero);
		rs=pstm.executeQuery();
		us=new Usuario();
		if(rs.next()) {
			us=new Usuario(rs.getInt("CD_USUARIO"),new Pergunta(rs.getInt("CD_PERGUNTA"),rs.getString("DS_PERGUNTA")) , rs.getString("DS_EMAIL"), rs.getString("SGL_GENERO").charAt(0),
					rs.getString("DS_NOME"), rs.getString("DS_APELIDO"), rs.getString("DS_SENHA"), rs.getString("DS_RESP_PERGUNTA"),rs.getString("ADM_SISTEMA").charAt(0));
		}
		return us;
	}
	/**
	 * Metodo responsavel por enviar os comandos sql ao banco para realizar uma consulta de um Usuario especifico por email
	 * @param email
	 * @author Victor R. de Lima
	 * @return Usuario
	 * @throws Exception
	 */
	public Usuario consultarPorEmail(String email) throws Exception{
		pstm=con.prepareStatement("SELECT T_AM_USUARIO.*,T_AM_PERGUNTA.* FROM T_AM_USUARIO INNER JOIN T_AM_PERGUNTA ON T_AM_USUARIO.CD_PERGUNTA=T_AM_PERGUNTA.CD_PERGUNTA WHERE T_AM_USUARIO.DS_EMAIL=?");
		pstm.setString(1, email);
		rs=pstm.executeQuery();
		us=new Usuario();
		if(rs.next()) {
			us=new Usuario(rs.getInt("CD_USUARIO"),new Pergunta(rs.getInt("CD_PERGUNTA"),rs.getString("DS_PERGUNTA")) , rs.getString("DS_EMAIL"), rs.getString("SGL_GENERO").charAt(0),
					rs.getString("DS_NOME"), rs.getString("DS_APELIDO"), rs.getString("DS_SENHA"), rs.getString("DS_RESP_PERGUNTA"),rs.getString("ADM_SISTEMA").charAt(0));
		}
		return us;
	}
	/**
	 * Metodo responsavel por enviar os comandos sql ao banco para deletar um Usuario especifico
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return String
	 * @throws Exception
	 */
	public String deletarPorCd(int pNumero) throws Exception{
		pstm=con.prepareStatement("DELETE FROM T_AM_USUARIO WHERE CD_USUARIO=?");
		pstm.setInt(1, pNumero);
		if(pstm.executeUpdate()>0) {
			return "linhas deletadas";
		}else
			return "linha não encontrada";
	}
	/**
	 * Metodo responsavel por enviar os comandos sql ao banco para deletar todos os Usuario
	 * @return String
	 * @author Victor R. de Lima
	 * @throws Exception
	 */
	public String deleteAll()throws Exception{
		pstm=con.prepareStatement("DELETE FROM T_AM_USUARIO");
		if(pstm.executeUpdate()>0) {
			return "Todas a linhas apagadas";
		}
			else
				return"tabela não encontrada";
		
	}
	/**
	 * Metodo responsavel por enviar os comandos sql ao banco para atualizar a senha de um usuario
	 * @param senha
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return String 
	 * @throws Exception
	 */
	public String trocarSenha(String senha,int pNumero)throws Exception{
		pstm=con.prepareStatement("UPDATE T_AM_USUARIO SET DS_SENHA=? WHERE CD_USUARIO=?");
		pstm.setString(1, senha);
		pstm.setInt(2, pNumero);
		if(pstm.executeUpdate()>0)
			return "Senha atualizada";
		return"Linha não encontrada";
	}
	/**
	 * Metodo responsavel por enviar os comandos sql ao banco para realizar um Usuario por apelido
	 * @param apelido
	 * @author Victor R. de Lima
	 * @return Usuario
	 * @throws Exception
	 */
	public Usuario consultarPorApelido(String apelido) throws Exception{
		pstm=con.prepareStatement("SELECT T_AM_USUARIO.*,T_AM_PERGUNTA.* FROM T_AM_USUARIO INNER JOIN T_AM_PERGUNTA ON T_AM_USUARIO.CD_PERGUNTA=T_AM_PERGUNTA.CD_PERGUNTA WHERE T_AM_USUARIO.DS_APELIDO=?");
		pstm.setString(1, apelido);
		rs=pstm.executeQuery();
		us=new Usuario();
		if(rs.next()) {
			us=new Usuario(rs.getInt("CD_USUARIO"),new Pergunta(rs.getInt("CD_PERGUNTA"),rs.getString("DS_PERGUNTA")) , rs.getString("DS_EMAIL"), rs.getString("SGL_GENERO").charAt(0),
					rs.getString("DS_NOME"), rs.getString("DS_APELIDO"), rs.getString("DS_SENHA"), rs.getString("DS_RESP_PERGUNTA"),rs.getString("ADM_SISTEMA").charAt(0));
		}
		return us;
	}
	/**
	 * Metodo responsavel por enviar os comandos sql ao banco para recuperar o maiorCd registrado do banco
	 * @return int
	 * @author Victor R. de Lima
	 * @throws Exception
	 */
	public int maiorCd()throws Exception{
		int maxId=0;
		pstm=con.prepareStatement("SELECT MAX(CD_USUARIO) FROM T_AM_USUARIO");
		rs=pstm.executeQuery();
		if(rs.next()) {
			maxId=rs.getInt(1);
		}
		return maxId;
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
