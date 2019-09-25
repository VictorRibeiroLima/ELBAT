package br.com.elbat.beans;
/**
 * Classe responsavel por gerar os atributos para a pergunta que sera usada para recuperação de senha<br>  
 * 2.0 adicionado o metodo compareTo
 * @author Brenno A. M. Oliveira
 * @version 2.0
 * @since 1.0
 */
public class Dificuldade implements Comparable<Dificuldade>{
private int cdDificuldade;
private String dificuldade;

public Dificuldade() {
	
}
public Dificuldade(int cdDificuldade) {
	setCdDificuldade(cdDificuldade);
}
public Dificuldade(int cdDificuldade,String dificuldade) {
	setCdDificuldade(cdDificuldade);
	setDificuldade(dificuldade);
}
/**
 * Metodo compara o codigo de 2 dificuldades e retorna em ordem de menor para o maior
 * @author Victor R. Lima
 * @version 1.0
 * @since 1.0
 */
public int compareTo(Dificuldade d) {
	if(this.cdDificuldade<d.getCdDificuldade()) {
		return -1;
	}else if(this.cdDificuldade>d.getCdDificuldade()) {
		return 1;
	}else
		return 0;
}
public int getCdDificuldade() {
	return cdDificuldade;
}
public void setCdDificuldade(int cdDificuldade) {
	this.cdDificuldade = cdDificuldade;
}
public String getDificuldade() {
	return dificuldade;
}
public void setDificuldade(String dificuldade) {
	this.dificuldade = dificuldade;
}
public void setAll(int cdDificuldade,String dificuldade) {
	setCdDificuldade(cdDificuldade);
	setDificuldade(dificuldade);
}

}
