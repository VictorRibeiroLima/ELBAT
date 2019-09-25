package br.com.elbat.bo;

import java.util.List;

import br.com.elbat.beans.ConfigUsuario;
import br.com.elbat.beans.Dificuldade;
import br.com.elbat.beans.Lingua;
import br.com.elbat.beans.Usuario;
import br.com.elbat.dao.ConfigUsuarioDAO;
/**
 * Classe responsavel pela aplica��o das regras de negocio no ConfigUsuario<br>
 * 2.0 Resolvido o bug de nullpointer no metodo  atualizarLingua
 * @author Alexandre J. M. Silva
 * @version 2.0
 * @since 1.0
 * @see br.com.elbat.beans.ConfigUsuario
 * @see br.com.elbat.beans.Dificuldade
 * @see br.com.elbat.beans.Lingua 
 * @see br.com.elbat.beans.Usuario
 * @see br.com.elbat.dao.ConfigUsuarioDAO
 * @see java.util.List
 */

public class ConfigUsuarioBO {
private UsuarioBO usBO=null;
private LinguaBO lingBO=null;
private DificuldadeBO difBO=null;
private ConfigUsuario confu=null;
private List<ConfigUsuario>listaDeConfiguracoes=null;
public ConfigUsuarioBO() {
	
}
/**
 * M�todo respons�vel por:
 * Validar consulta de usuario - codigo do usuario deve estar entre 1 e 99999<br>
 * N�o permitir repeti��o da configura��o do c�digo de usu�rio(viola��o da chave primaria)<br>
 * Validar a existencia do codigo da lingua<br> 
 * validar a op��o - getOpcao n�o pode ser diferente de 0 e 1 <br>
 * Validar consulta por dificuldade - codigo de dificuldade esta entre 1 � 3 
 * @author Alexandre J. M. Silva
 * @param confu
 * @return retorna String que foi enviada por congiUsuarioDAO
 * @throws Exception valida a exce��o checked
 */
public String registrarNovaConfig(ConfigUsuario confu)throws Exception{
	confu.setCdConfig(1);
	ConfigUsuarioDAO confDAO=new ConfigUsuarioDAO();
	usBO=new UsuarioBO();
	lingBO=new LinguaBO();
	difBO=new DificuldadeBO();
	if(usBO.consultarUsuarioPorCd(confu.getUsuario().getCdUsuario()).getCdUsuario()<=0||confu.getUsuario().getCdUsuario()>99999||confu.getUsuario().getCdUsuario()<0) {
		return "Viola��o de chave estrangeira/primaria";
	}
	if(confu.getCdConfig()>9||confu.getCdConfig()<0||confDAO.consultarConfigsPorUsuario(confu.getUsuario().getCdUsuario()).getLingua()!=null){
		return "Viola��o de chave primaria";
	}
	if(lingBO.consultarPorCd(confu.getLingua().getCdLingua()).getLingua()==null) {
		return "Viola��o de chave estrangeira";
	}
	if(confu.getOpcao()!=0 && confu.getOpcao()!=1) {
		return "Viola��o de op��o";
	}
	if(difBO.consultarDificuldadePorCd(confu.getDificuldade().getCdDificuldade()).getDificuldade()==null||confu.getDificuldade().getCdDificuldade()<1 || confu.getDificuldade().getCdDificuldade()>3) {
		return "Viola��o de dificuldade";
	}
	confDAO.gravarConfigUsuario(confu);
	confDAO.fecharConexao();
	return "Config registrada";
}
/**
 * M�todo responsavel:
 * Consultar configura��o por usuario 
 * @author Alexandre J. M. Silva
 * @param cdUsuario
 * @return configUsuario
 * @throws Exception 
 */
public ConfigUsuario consultarConfigPorUsuario(int cdUsuario)throws Exception{

	ConfigUsuarioDAO confDAO=new ConfigUsuarioDAO();
	confu=new ConfigUsuario();
	confu=confDAO.consultarConfigsPorUsuario(cdUsuario);
	confDAO.fecharConexao();
	return confu;
}
/**
 * Metodo responsavel por criar um lista de consulta por dificuldade
 * @author Alexandre J. M. Silva
 * @param cdDificuldade
 * @return List
 * @throws Exception 
 */
public List<ConfigUsuario> consultarConfigsPorDificuldade(int cdDificuldade)throws Exception{
	
	ConfigUsuarioDAO confDAO=new ConfigUsuarioDAO();
	listaDeConfiguracoes=confDAO.consultarConfigsPorDificuldade(cdDificuldade);
	confDAO.fecharConexao();
	return listaDeConfiguracoes;
}
/**
 * M�todo respons�vel por criar lista de consulta por lingua
 * @author Alexandre J. M. Silva
 * @param cdLingua
 * @return List
 * @throws Exception 
 */
public List<ConfigUsuario> consultarConfigsPorLingua(int cdLingua) throws Exception{

	ConfigUsuarioDAO confDAO=new ConfigUsuarioDAO();
	listaDeConfiguracoes=confDAO.consultarConfigsPorLingua(cdLingua);
	confDAO.fecharConexao();
	return listaDeConfiguracoes;
}
/**
 * M�todo responsavel por criar uma lista de consultas envolvendo todas as configura��es
 * @author Alexandre J. M. Silva
 * @return List
 * @throws Exception 
 */
public List<ConfigUsuario> consultarTodasAsConfigs()throws Exception{

	ConfigUsuarioDAO confDAO=new ConfigUsuarioDAO();
	listaDeConfiguracoes=confDAO.consultarConfigs();
	confDAO.fecharConexao();
	return listaDeConfiguracoes;
}
/**
 * M�todo responsavel por deletar configura��o por codigo 
 * @author Alexandre J. M. Silva
 * @param  cdUsuario
 * @return String
 * @throws Exception
 */
public String deletarConfigPorCd(int cdUsuario)throws Exception{

	ConfigUsuarioDAO confDAO=new ConfigUsuarioDAO();
	String resp=confDAO.deletarPorCd(cdUsuario);
	confDAO.fecharConexao();
	return resp;
}
/**
 * M�todo responsavel por atualizar a lingua configurada<br>
 * checar se a lingua esta resgistrada no sistema
 * @author Alexandre J. M. Silva
 * @param cdLingua, cdUsuario
 * @return String
 * @throws Exception
 */
public String atualizarLinguaConfig(int cdLingua,int cdUsuario)throws Exception{

	ConfigUsuarioDAO confDAO=new ConfigUsuarioDAO();
	lingBO = new LinguaBO();
	if(lingBO.consultarPorCd(cdLingua).getLingua()==null) {
		return "Chave estrangeira invalida";
	}
	String resp=confDAO.atualizarLingua(cdLingua, cdUsuario);
	confDAO.fecharConexao();
	return resp;
}
/**
 * M�todo responsavel por atualizar a dificuldade
 * verificar a existencia da chave estrangeira
 * @author Alexandre J. M. Silva
 * @param cdDificuldade, cdUsuario
 * @return String
 * @throws Exception
 */
public String atualizarDificuldade(int cdDificuldade,int cdUsuario)throws Exception{

	ConfigUsuarioDAO confDAO=new ConfigUsuarioDAO();
	difBO=new DificuldadeBO();
	if(difBO.consultarDificuldadePorCd(cdDificuldade).getDificuldade()==null) {
		return "Chave estrangeira invalida";
	}
	String resp=confDAO.atualizarDificuldade(cdDificuldade, cdUsuario);
	confDAO.fecharConexao();
	return resp;
}
/**
 * M�todo responsavel por configurar o padrao de usuario
 * @author Alexandre J. M. Silva
 * @param us
 * @return String
 * @throws Exception 
 */
public String configsDefault(Usuario us)throws Exception{

	String resp=registrarNovaConfig(new ConfigUsuario(1,us,new Lingua(1),new Dificuldade(1),0));
	return resp;
}
/**
 * M�todo responsavel por:
 * N�o permitir que numero da op��o seja diferente de 0 e 1
 * @author Alexandre J. M. Silva
 * @param nrOpcao, cdUsuario
 * @return String
 * @throws Exception 
 */
public String atualizarOpcao(int nrOpcao,int cdUsuario) throws Exception{

	ConfigUsuarioDAO confDAO=new ConfigUsuarioDAO();
	if(nrOpcao!=0 && nrOpcao!=1) {
		return "Op��o invalida";
	}

	String resp=confDAO.atualizarOpcao(nrOpcao, cdUsuario);
	return resp;
}
}
