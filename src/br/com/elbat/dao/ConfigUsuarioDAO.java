package br.com.elbat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.elbat.beans.ConfigUsuario;
import br.com.elbat.beans.Dificuldade;
import br.com.elbat.beans.Lingua;
import br.com.elbat.beans.Pergunta;
import br.com.elbat.beans.Usuario;
import br.com.elbat.conexao.Conectar;
/**
 * Classe responsável por manipular a tabela T_AM_CONFIG_USUARIO<br>
 * 2.0 adicionado os metodos que consultam as linhas por chaves estrangeiras de usuario,lingua,dificuldade<br>
 * 3.0 melhora de perfomance nos métodos de consulta
 * 
 *@author Victor R. de Lima
 *@version 3.0
 *@since 1.0
 *
 *@see java.sql.Connection
 *@see java.sql.PreparedStatement
 *@see java.sql.ResultSet
 *@see java.util.ArrayList
 *@see java.util.List
 *
 *@see br.com.elbat.beans.ConfigUsuario
 *@see br.com.elbat.beans.Dificuldade
 *@see br.com.elbat.beans.Lingua
 *@see br.com.elbat.beans.Pergunta
 *@see br.com.elbat.beans.Usuario
 *@see br.com.elbat.conexao.Conectar
 *
 */
public class ConfigUsuarioDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstm;
private ConfigUsuario conf=new ConfigUsuario();
	
	private List<ConfigUsuario> listaConfigs=null;
	public ConfigUsuarioDAO() throws Exception{
		con=Conectar.conectar();
	}
	/**
	 * Metodo responsavel por enviar os comandos sql de registro ao banco de dados
	 * @author Victor R. de Lima
	 * @param conf
	 * @return String com confirmação de registro
	 * @throws Exception
	 */
	public String gravarConfigUsuario(ConfigUsuario conf) throws Exception{
		pstm=con.prepareStatement("INSERT INTO T_AM_CONFIG_USUARIO(CD_CONFIG,CD_USUARIO,CD_LINGUA,CD_DIFICULDADE,NR_OPCAO_RECEBERM,DT_CONFIG) VALUES(?,?,?,?,?,sysdate)");
		pstm.setInt(1,conf.getCdConfig());
		pstm.setInt(2,conf.getUsuario().getCdUsuario());
		pstm.setInt(3, conf.getLingua().getCdLingua());
		pstm.setInt(4, conf.getDificuldade().getCdDificuldade());
		pstm.setInt(5, conf.getOpcao());
		pstm.executeUpdate();
		return "pergunta registrada";
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que faz uma consulta por Usuarios configurados com uma lingua em especifico
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return List de configs de usuarios registrados com a lingua 
	 * @throws Exception
	 */
	public List<ConfigUsuario> consultarConfigsPorLingua(int pNumero) throws Exception{
		pstm=con.prepareStatement("SELECT *  FROM T_AM_CONFIG_USUARIO INNER JOIN T_AM_LINGUA  ON (T_AM_CONFIG_USUARIO.CD_LINGUA=T_AM_LINGUA.CD_LINGUA)   INNER JOIN T_AM_DIFICULDADE  ON (T_AM_CONFIG_USUARIO.CD_DIFICULDADE=T_AM_DIFICULDADE.CD_DIFICULDADE)INNER JOIN T_AM_USUARIO ON(T_AM_CONFIG_USUARIO.CD_USUARIO=T_AM_USUARIO.CD_USUARIO)  INNER JOIN T_AM_PERGUNTA  ON(T_AM_USUARIO.CD_pergunTA=T_AM_PERGUNTA.CD_PERGUNTA) where T_AM_CONFIG_USUARIO.CD_LINGUA=?");
		pstm.setInt(1, pNumero);
		rs=pstm.executeQuery();
		listaConfigs=new ArrayList<ConfigUsuario>();
		while(rs.next()) {
			listaConfigs.add(new ConfigUsuario(rs.getInt("CD_CONFIG"),
					new Usuario(rs.getInt("CD_USUARIO"),
							new Pergunta(rs.getInt("CD_PERGUNTA"),rs.getString("DS_PERGUNTA")),
							rs.getString("DS_EMAIL"),rs.getString("SGL_GENERO").charAt(0),rs.getString("DS_NOME"),rs.getString("DS_APELIDO"),rs.getString("DS_SENHA"),rs.getString("DS_RESP_PERGUNTA"),rs.getString("ADM_SISTEMA").charAt(0))
,new Lingua(rs.getInt("CD_LINGUA"),rs.getString("DS_LINGUA"),rs.getString("SGL_LINGUA")),
new Dificuldade(rs.getInt("CD_DIFICULDADE"),rs.getString("DS_DIFICULDADE")),
rs.getInt("NR_OPCAO_RECEBERM")));
		}
		return listaConfigs;
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que faz uma consulta por Usuarios configurados com uma Dificuldade em especifico
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return List de configs de usuarios registrados com a Dificuldade 
	 * @throws Exception
	 */
	public List<ConfigUsuario> consultarConfigsPorDificuldade (int pNumero) throws Exception{
		pstm=con.prepareStatement("SELECT *  FROM T_AM_CONFIG_USUARIO INNER JOIN T_AM_LINGUA  ON (T_AM_CONFIG_USUARIO.CD_LINGUA=T_AM_LINGUA.CD_LINGUA)   INNER JOIN T_AM_DIFICULDADE  ON (T_AM_CONFIG_USUARIO.CD_DIFICULDADE=T_AM_DIFICULDADE.CD_DIFICULDADE)INNER JOIN T_AM_USUARIO ON(T_AM_CONFIG_USUARIO.CD_USUARIO=T_AM_USUARIO.CD_USUARIO)  INNER JOIN T_AM_PERGUNTA  ON(T_AM_USUARIO.CD_pergunTA=T_AM_PERGUNTA.CD_PERGUNTA) where T_AM_CONFIG_USUARIO.CD_DIFICULDADE=?");
		pstm.setInt(1, pNumero);
		rs=pstm.executeQuery();
		listaConfigs=new ArrayList<ConfigUsuario>();
		while(rs.next()) {
			listaConfigs.add(new ConfigUsuario(rs.getInt("CD_CONFIG"),
					new Usuario(rs.getInt("CD_USUARIO"),
							new Pergunta(rs.getInt("CD_PERGUNTA"),rs.getString("DS_PERGUNTA")),
							rs.getString("DS_EMAIL"),rs.getString("SGL_GENERO").charAt(0),rs.getString("DS_NOME"),rs.getString("DS_APELIDO"),rs.getString("DS_SENHA"),rs.getString("DS_RESP_PERGUNTA"),rs.getString("ADM_SISTEMA").charAt(0))
,new Lingua(rs.getInt("CD_LINGUA"),rs.getString("DS_LINGUA"),rs.getString("SGL_LINGUA")),
new Dificuldade(rs.getInt("CD_DIFICULDADE"),rs.getString("DS_DIFICULDADE")),
rs.getInt("NR_OPCAO_RECEBERM")));
		}
		return listaConfigs;
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que faz uma consulta da configuração utilizado por um usuario especifico
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return ConfigUsuario do usuario pesquisado
	 * @throws Exception
	 */
	public ConfigUsuario consultarConfigsPorUsuario(int pNumero) throws Exception{
		pstm=con.prepareStatement("SELECT *  FROM T_AM_CONFIG_USUARIO INNER JOIN T_AM_LINGUA  ON (T_AM_CONFIG_USUARIO.CD_LINGUA=T_AM_LINGUA.CD_LINGUA)   INNER JOIN T_AM_DIFICULDADE  ON (T_AM_CONFIG_USUARIO.CD_DIFICULDADE=T_AM_DIFICULDADE.CD_DIFICULDADE)INNER JOIN T_AM_USUARIO ON(T_AM_CONFIG_USUARIO.CD_USUARIO=T_AM_USUARIO.CD_USUARIO)  INNER JOIN T_AM_PERGUNTA  ON(T_AM_USUARIO.CD_pergunTA=T_AM_PERGUNTA.CD_PERGUNTA) where T_AM_CONFIG_USUARIO.cd_usuario=?");
		pstm.setInt(1, pNumero);
		rs=pstm.executeQuery();
		if(rs.next()) {
			conf=new ConfigUsuario(rs.getInt("CD_CONFIG"),
					new Usuario(rs.getInt("CD_USUARIO"),
							new Pergunta(rs.getInt("CD_PERGUNTA"),rs.getString("DS_PERGUNTA")),
							rs.getString("DS_EMAIL"),rs.getString("SGL_GENERO").charAt(0),rs.getString("DS_NOME"),rs.getString("DS_APELIDO"),rs.getString("DS_SENHA"),rs.getString("DS_RESP_PERGUNTA"),rs.getString("ADM_SISTEMA").charAt(0))
,new Lingua(rs.getInt("CD_LINGUA"),rs.getString("DS_LINGUA"),rs.getString("SGL_LINGUA")),
new Dificuldade(rs.getInt("CD_DIFICULDADE"),rs.getString("DS_DIFICULDADE")),
rs.getInt("NR_OPCAO_RECEBERM"));
		}
		return conf;
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que faz uma consulta de todas as configs de Usuario
	 * @return ConfigUsuario de todos os usuarios
	 * @author Victor R. de Lima
	 * @throws Exception
	 */
	public List<ConfigUsuario> consultarConfigs()throws Exception{
		pstm=con.prepareStatement("SELECT *  FROM T_AM_CONFIG_USUARIO INNER JOIN T_AM_LINGUA  ON (T_AM_CONFIG_USUARIO.CD_LINGUA=T_AM_LINGUA.CD_LINGUA)   INNER JOIN T_AM_DIFICULDADE  ON (T_AM_CONFIG_USUARIO.CD_DIFICULDADE=T_AM_DIFICULDADE.CD_DIFICULDADE)INNER JOIN T_AM_USUARIO ON(T_AM_CONFIG_USUARIO.CD_USUARIO=T_AM_USUARIO.CD_USUARIO)  INNER JOIN T_AM_PERGUNTA  ON(T_AM_USUARIO.CD_pergunTA=T_AM_PERGUNTA.CD_PERGUNTA)");
		listaConfigs=new ArrayList<ConfigUsuario>();
		rs=pstm.executeQuery();
		while(rs.next()) {
			listaConfigs.add(new ConfigUsuario(rs.getInt("CD_CONFIG"),
					new Usuario(rs.getInt("CD_USUARIO"),
							new Pergunta(rs.getInt("CD_PERGUNTA"),rs.getString("DS_PERGUNTA")),
							rs.getString("DS_EMAIL"),rs.getString("SGL_GENERO").charAt(0),rs.getString("DS_NOME"),rs.getString("DS_APELIDO"),rs.getString("DS_SENHA"),rs.getString("DS_RESP_PERGUNTA"),rs.getString("ADM_SISTEMA").charAt(0))
,new Lingua(rs.getInt("CD_LINGUA"),rs.getString("DS_LINGUA"),rs.getString("SGL_LINGUA")),
new Dificuldade(rs.getInt("CD_DIFICULDADE"),rs.getString("DS_DIFICULDADE")),
rs.getInt("NR_OPCAO_RECEBERM")));
		}
		return listaConfigs;
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que faz o deleção de uma ConfigUsuario
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return String com resultado
	 * @throws Exception
	 */
	public String deletarPorCd(int pNumero	) throws Exception{
		pstm=con.prepareStatement("DELETE FROM T_AM_CONFIG_USUARIO WHERE  CD_USUARIO=?");
		pstm.setInt(1, pNumero);
		if(pstm.executeUpdate()>0) {
			return "linhas deletadas";
		}else
			return "linha não encontrada";
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que faz o deleção de todas as configs
	 * @return String com resultado
	 * @author Victor R. de Lima
	 * @throws Exception
	 */
	public String deleteAll()throws Exception{
		pstm=con.prepareStatement("DELETE FROM T_AM_CONFIG");
		if(pstm.executeUpdate()>0) {
			return "Todas a linhas apagadas";
		}
			else
				return"tabela não encontrada";
		
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que atualiza a lingua de uma config
	 * @param cdLingua
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return String com o resultado
	 * @throws Exception
	 */
	public String atualizarLingua(int cdLingua,int pNumero)throws Exception{
		pstm=con.prepareStatement("UPDATE T_AM_CONFIG_USUARIO SET CD_LINGUA=? WHERE CD_CONFIG=1 AND CD_USUARIO=?");
		pstm.setInt(1, cdLingua);
		pstm.setInt(2, pNumero);
		if(pstm.executeUpdate()>0)
			return "Atualização concluida";
		return "Linha não encontrada";
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que atualiza a dificuldade de uma config
	 * @param cdDificuldade
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return String com o resultado
	 * @throws Exception
	 */
	public String atualizarDificuldade(int cdDificuldade,int pNumero)throws Exception{
		pstm=con.prepareStatement("UPDATE T_AM_CONFIG_USUARIO SET CD_DIFICULDADE=? WHERE CD_CONFIG=1 AND CD_USUARIO=?");
		pstm.setInt(1, cdDificuldade);
		pstm.setInt(2, pNumero);
		if(pstm.executeUpdate()>0)
			return "Atualização concluida";
		return "Linha não encontrada";
	}
	/**
	 * Metodo responsavel por enviar um comando sql ao banco que atualiza a opcao de uma config
	 * @param nrOpcao
	 * @param pNumero
	 * @author Victor R. de Lima
	 * @return String com o resultado
	 * @throws Exception
	 */
	public String atualizarOpcao(int nrOpcao,int pNumero)throws Exception{
		pstm=con.prepareStatement("UPDATE T_AM_CONFIG_USUARIO SET NR_OPCAO_RECEBERM=? WHERE CD_CONFIG=1 AND CD_USUARIO=?");
		pstm.setInt(1, nrOpcao);
		pstm.setInt(2, pNumero);
		if(pstm.executeUpdate()>0)
			return"Atualização concluida";
		return "Linha não encontrada";
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
