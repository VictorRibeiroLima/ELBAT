package br.com.elbat.beans;
/**
 * Classe responsavel por gerar os atributos para configurar os dados do usuário  <br>
 * 2.0 adicionado o metodo compareTo
 * @author Brenno A. M. Oliveira
 * @version 2.0
 * @since 1.0
 */
public class ConfigUsuario implements Comparable<ConfigUsuario>{
private int cdConfig;
private Usuario usuario;
private Lingua lingua;
private Dificuldade dificuldade;
private int opcao;
public ConfigUsuario() {
	
}
public ConfigUsuario(int cdConfig,Usuario usuario,Lingua lingua,Dificuldade dificuldade,int opcao) {
	setCdConfig(cdConfig);
	setUsuario(usuario);
	setLingua(lingua);
	setDificuldade(dificuldade);
	setOpcao(opcao);
}
/**
 * Metodo compara o codigo de usuario de 2 configs e retorna em ordem de menor para o maior
 * @author Victor R. Lima
 * @version 1.0
 * @since 1.0
 */
public int compareTo(ConfigUsuario c) {
	if(this.usuario.getCdUsuario()<c.getUsuario().getCdUsuario()) {
		return -1;
	}else if(this.usuario.getCdUsuario()>c.getUsuario().getCdUsuario()) {
		return 1;
	}else {
		return 0;
	}
}
public void setDificuldade(Dificuldade dificuldade) {
	this.dificuldade=dificuldade;
}
public Dificuldade getDificuldade() {
	return dificuldade;
}
public int getCdConfig() {
	return cdConfig;
}
public void setCdConfig(int cdConfig) {
	this.cdConfig = cdConfig;
}
public Usuario getUsuario() {
	return usuario;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
public Lingua getLingua() {
	return lingua;
}
public void setLingua(Lingua lingua) {
	this.lingua = lingua;
}
public int getOpcao() {
	return opcao;
}
public void setOpcao(int opcao) {
	this.opcao = opcao;
}
public void setAll(int cdConfig,Usuario usuario,Lingua lingua,Dificuldade dificuldade,int opcao) {
	setCdConfig(cdConfig);
	setUsuario(usuario);
	setLingua(lingua);
	setDificuldade(dificuldade);
	setOpcao(opcao);
}
}
