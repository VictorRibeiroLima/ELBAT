package br.com.elbat.beans;

/**
 * Classe responsavel por estabelecer os atributos de usuário.<br>
 * 2.0 adicionado novo atributo (adm) que diz se esse usuario é ou não um adm<br>
 * 3.0 adicionado metodo compareTo
 * @author Brenno A. M. Oliveira
 * @version 3.0
 * @since 1.0
 */

public class Usuario implements Comparable<Usuario>{
private int cdUsuario;
private Pergunta pergunta;
private String email;
private char genero;
private String nome;
private String apelido;
private String senha;
private String resposta;
public char adm;
public Usuario() {
	
}
public Usuario(int cdUsuario,Pergunta pergunta,String email,char genero,String nome,String apelido,String senha,String resposta,char adm) {
	setCdUsuario(cdUsuario);
	setPergunta(pergunta);
	setEmail(email);
	setGenero(genero);
	setNome(nome);
	setApelido(apelido);
	setSenha(senha);
	setResposta(resposta);
	setAdm(adm);
} 
/**
 * Metodo compara o codigo de 2 usuarios e retorna em ordem de menor para o maior
 * @author Victor R. Lima
 * @version 1.0
 * @since 1.0
 */
public int compareTo(Usuario u) {
	if(this.cdUsuario<u.getCdUsuario())
		return-1;
	else if(this.cdUsuario>u.getCdUsuario())
		return 1;
	else
		return 0;
}
public int getCdUsuario() {
	return cdUsuario;
}
public void setCdUsuario(int cdUsuario) {
	this.cdUsuario = cdUsuario;
}
public Pergunta getPergunta() {
	return pergunta;
}
public void setPergunta(Pergunta pergunta) {
	this.pergunta = pergunta;
}
public void setAdm(char adm) {
	this.adm=adm;
}
public char getAdm() {
	return adm;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public char getGenero() {
	return genero;
}
public void setGenero(char genero) {
	this.genero = genero;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getApelido() {
	return apelido;
}
public void setApelido(String apelido) {
	this.apelido = apelido;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}
public String getResposta() {
	return resposta;
}
public void setResposta(String resposta) {
	this.resposta = resposta;
}
public void setAll(int cdUsuario,Pergunta pergunta,String email,char genero,String nome,String apelido,String senha,String resposta,char admin) {
	setCdUsuario(cdUsuario);
	setPergunta(pergunta);
	setEmail(email);
	setGenero(genero);
	setNome(nome);
	setApelido(apelido);
	setSenha(senha);
	setResposta(resposta);
	setAdm(admin);
}

/**
 * Método responsável por pedir somente as informações necessárias para o cadastro
 * @author Brenno A. M. Oliveira
 */
public void setFront(Pergunta pergunta,String email,char genero,String nome,String apelido,String senha,String resposta,char admin) {
	setPergunta(pergunta);
	setEmail(email);
	setGenero(genero);
	setNome(nome);
	setApelido(apelido);
	setSenha(senha);
	setResposta(resposta);
	setAdm(admin);
}
public String getAll() {
	return getPergunta().getAll()+" "+getEmail()+" "+getGenero()+" "+getNome()+" "+getApelido()+" "+getSenha()+" "+getResposta();
}
public String getFront() {
	return getNome()+" "+getApelido();
}
}
