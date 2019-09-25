package br.com.elbat.bo;

import java.util.List;

import br.com.elbat.beans.Usuario;
import br.com.elbat.dao.UsuarioDAO;
/**
 * Classe responsavel pela aplicação das regras de negocio no usuario<br>
 * 2.0 alteração no metodo fazerLoginUsuario,onde ela passo a retornar usuario<br>
 * 3.0 adicionado o metodo para que o super adm delete qualquer usuario<br>
 * 4.0 adicionado o metodo para que se consulte um Usuario por email
 * @author Alexandre J. M. Silva
 * @version 4.0
 * @since 1.0                                
 */
public class UsuarioBO {
public UsuarioBO() {
	
}
/**
 * Método responsável por:
 * Registrar um usuario ao sistema<br>
 * estabelecer o limite de 99999 para o código do usuário<br>
 * garantir que pergunta seja registrada no banco<br>
 * garantir que email esteja entre 2 a 100 caracteres e possua o caractere especial @<br>
 * garantir que genero só poderá receber três valores específico, F, M ou I<br>
 * garantir que nome tenha no mínimo 2 e no máximo 60 caracteres<br>
 * garantir que apelido tenha no mínimo 2 e no máximo 40 caracteres<br>
 * garantir que senha tenha no minimo 2 e no maximo 10 caracteres<br>
 * impedir que resposta tenha um valor de caracteres maior que 50
 * @param u
 * @return String
 * @throws Exception
 */
public String gravarNovoUsuario(Usuario u)throws Exception{

	UsuarioDAO usDAO=new UsuarioDAO();
	PerguntaBO perBO=new PerguntaBO();
	ConfigUsuarioBO confBO=new ConfigUsuarioBO();
	u.setCdUsuario(usDAO.maiorCd()+1);
	if(u.getCdUsuario()>99999) {
		return"Banco atingiu a capacidade maxima";
	}
	if(perBO.consultarPerguntasPorCd(u.getPergunta().getCdPergunta()).getPergunta()==null) {
		return "Violação de chave estrangeira";
	}
	if(u.getEmail().indexOf("@")<2 || u.getEmail().length()>100||consultarUsuarioPorEmail(u.getEmail()).getNome()!=null) {
		return "Violação do email";
	}
	if(!String.valueOf(u.getGenero()).toUpperCase().equals("F")&&!String.valueOf(u.getGenero()).toUpperCase().equals("M")&&!String.valueOf(u.getGenero()).toUpperCase().equals("I")) {
		return "Violação de genero";
	}
	if(u.getNome().length()<2||u.getNome().length()>60) {
		return "Violação de nome";
	}
	if(usDAO.consultarPorApelido(u.getApelido()).getNome()!=null||u.getApelido().length()<2||u.getApelido().length()>40) {
		return "Violação de apelido";
	}
	if(u.getSenha().length()<2||u.getSenha().length()>10) {
		return "Violação de senha";
	}
	if(u.getResposta().length()>50) {
		return "Violação de Resposta";
	}
	if(!String.valueOf(u.getAdm()).equals("1")&&!String.valueOf(u.getAdm()).equals("0")&&!String.valueOf(u.getAdm()).equals("2")) {
		return "Violação de adm";
	}
	u.setEmail(u.getEmail().toLowerCase());
	u.setGenero(String.valueOf(u.getGenero()).toUpperCase().charAt(0));
	u.setNome(u.getNome().toUpperCase());
	u.setResposta(u.getResposta().toUpperCase());
	usDAO.gravarUsuario(u);
	String a=confBO.configsDefault(u);
	usDAO.fecharConexao();
	return a;
}
/**
 * Método responsável por consultar usuario utilizando o código
 *  @author Alexandre J. M. Silva
 * @param cdUsuario
 * @return consultasPorCd
 * @throws Exception valida a exceção checked
 */
public Usuario consultarUsuarioPorCd(int cdUsuario) throws Exception{

	UsuarioDAO usDAO=new UsuarioDAO();
	Usuario us=usDAO.consultasPorCd(cdUsuario);
	usDAO.fecharConexao();
	return us;
}
/**
 * Método responsável por consultar usuario por apelido 
 *  @author Alexandre J. M. Silva
 * @param apelido
 * @return Usuario
 * @throws Exception valida a exceção checked
 */
public Usuario consultarUsuarioPorApelido(String apelido)throws Exception{

	UsuarioDAO usDAO=new UsuarioDAO();
	Usuario us=usDAO.consultarPorApelido(apelido);
	usDAO.fecharConexao();
	return us;
}
/**
 * Método responsável por consultar usuario por email
 *  @author Alexandre J. M. Silva
 * @param email
 * @return Usuario
 * @throws Exception valida a exceção checked
 */
public Usuario consultarUsuarioPorEmail(String email) throws Exception{
	email=email.toLowerCase();
	UsuarioDAO usDAO=new UsuarioDAO();
	Usuario us=usDAO.consultarPorEmail(email);
	usDAO.fecharConexao();
	return us;
}
/**
 * Método responsável por criar lista de consultas de usuário por pergunta 
 *  @author Alexandre J. M. Silva
 * @param cdPergunta
 * @return List
 * @throws Exception valida a exceção checked
 */
public List<Usuario> consultarUsuarioPorPergunta(int cdPergunta)throws Exception{

	UsuarioDAO usDAO=new UsuarioDAO();
	List<Usuario> listaUsuarios=usDAO.consultarUsuarioPorPergunta(cdPergunta);
	usDAO.fecharConexao();
	return listaUsuarios;
}
/**
 * Método responsável por consultar todos os usuarios
 *  @author Alexandre J. M. Silva
 * @return List
 * @throws Exception valida a exceção checked
 */
public List<Usuario> consultarTodosOsUsuarios()throws Exception{

	UsuarioDAO usDAO=new UsuarioDAO();
	List<Usuario> listaUsuarios=usDAO.consultarUsuarios();
	usDAO.fecharConexao();
	return listaUsuarios;
}
/**
 * Método responsável por deletar usuario por codigo <br>
 * caso esse usuario não seja um adm
 *  @author Alexandre J. M. Silva
 * @param cdUsuario
 * @return String
 * @throws Exception valida a exceção checked
 */
public String deletarUsuarioPorCd(int cdUsuario)throws Exception{

	UsuarioDAO usDAO=new UsuarioDAO();
	ConfigUsuarioBO confBO=new ConfigUsuarioBO();
	if(String.valueOf(consultarUsuarioPorCd(cdUsuario).getAdm()).equals("1")) {
		usDAO.fecharConexao();
		return "Você não pode deletar um adm";
	}
	else {
		confBO.deletarConfigPorCd(cdUsuario);
		String resp=usDAO.deletarPorCd(cdUsuario);
		usDAO.fecharConexao();
		return resp;
	}
	
}
/**
 * Método responsável por:
 * garantir que resposta seja diferente da senha anterior <br>
 * trocar senha 
 *  @author Alexandre J. M. Silva
 * @param senhaAnterior
 * @param novaSenha
 * @param cdUsuario
 * @return String
 * @throws Exception valida a exceção checked
 */
public String trocarSenha(String senhaAnterior,String novaSenha,int cdUsuario)throws Exception{

	UsuarioDAO usDAO=new UsuarioDAO();
	if(!consultarUsuarioPorCd(cdUsuario).getSenha().equals(senhaAnterior)) {
		return "senha incorreta";
	}
	String resp=usDAO.trocarSenha(novaSenha, cdUsuario);
	usDAO.fecharConexao();
	return resp;
}
/**
 * Método responsável por:
 * garantir que resposta seja diferente de resposta da pergunta<br>
 * recuperar senha
 *  @author Alexandre J. M. Silva
 * @param respPergunta
 * @param novaSenha
 * @param cdUsuario
 * @return String, resp
 * @throws Exception
 */ 
public String recuperarSenha(String respPergunta,String novaSenha,int cdUsuario)throws Exception{

	UsuarioDAO usDAO=new UsuarioDAO();
	if(!consultarUsuarioPorCd(cdUsuario).getResposta().equals(respPergunta)) {
		return "Resposta incorreta";
	}
	String resp=usDAO.trocarSenha(novaSenha, cdUsuario);
	usDAO.fecharConexao();
	return resp;
}
/**
 * Metodo responsavel para permitir que o superAdm delete qualquer usuario
 * confere se foi dado um superAdm valido
 * @param cdAdm
 * @param cdUsuario
 * @return String
 * @throws Exception
 */
public String deletarUsuarioSuperAdm(Usuario us,int cdUsuario) throws Exception{
	
	if(!String.valueOf(us.getAdm()).equals("2")) {
		return "esse usuario não é um super adm";
	}
	else {
		UsuarioDAO usDAO=new UsuarioDAO();
		if(String.valueOf(consultarUsuarioPorCd(cdUsuario).getAdm()).equals("2")) {
			usDAO.fecharConexao();
			return "Você não pode deletar um Superadm";
		}
		ConfigUsuarioBO confBO=new ConfigUsuarioBO();
		confBO.deletarConfigPorCd(cdUsuario);
		String resp=usDAO.deletarPorCd(cdUsuario);
		usDAO.fecharConexao();
		return resp;
	}
}
/**
 * Método responsável por realizar login de usuário  pelo apelido
 *  @author Alexandre J. M. Silva
 * @param apelido
 * @param senha
 * @return us
 * @throws Exception valida a exceção checked
 */
public Usuario fazerLoginUsuario(String apelido,String senha)throws Exception{	
	Usuario us=consultarUsuarioPorApelido(apelido);
	if(!us.getSenha().equals(senha)) {
		return null;
	}
	return us;
}
/**
 * Método responsável por realizar login de usuário pelo email
 *  @author Alexandre J. M. Silva
 * @param email
 * @param senha
 * @return us
 * @throws Exception valida a exceção checked
 */
public Usuario fazerLoginUsuarioEmail(String email,String senha)throws Exception{	
	Usuario us=consultarUsuarioPorEmail(email);
	if(!us.getSenha().equals(senha)) {
		return null;
	}
	return us;
}
}
