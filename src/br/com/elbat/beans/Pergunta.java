package br.com.elbat.beans;
/**
 * Classe responsavel por gerar os atributos para a pergunta que sera usada para recuperação de senha  <br>
 * 2.0 adicionado metodo compareTo
 * @author Brenno A. M. Oliveira
 * @version 2.0
 * @since 1.0
 */
public class Pergunta implements Comparable<Pergunta>{
private int cdPergunta;
private String pergunta;

public Pergunta() {
	
}
public Pergunta(int cdPergunta,String pergunta) {
	setCdPergunta(cdPergunta);
	setPergunta(pergunta);
}
public Pergunta(int cdPergunta) {
	setCdPergunta(cdPergunta);
}
/**
 * Metodo compara o codigo de 2 perguntas e retorna em ordem de menor para o maior
 * @author Victor R. Lima
 * @version 1.0
 * @since 1.0
 */
public int compareTo(Pergunta p) {
	if(this.cdPergunta<p.getCdPergunta())
		return -1;
	else if(this.cdPergunta>p.getCdPergunta())
		return 1;
	else
		return 0;
}
public int getCdPergunta() {
	return cdPergunta;
}
public void setCdPergunta(int cdPergunta) {
	this.cdPergunta = cdPergunta;
}
public String getPergunta() {
	return pergunta;
}
public void setPergunta(String pergunta) {
	this.pergunta = pergunta;
}
public void setAll(int cdPergunta,String pergunta) {
	setCdPergunta(cdPergunta);
	setPergunta(pergunta);
}
public String getAll() {
	return getCdPergunta()+" "+getPergunta();
}
}
