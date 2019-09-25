package br.com.elbat.beans;
/**
 * Classe responsavel por Gerar os atributos da lingua em que o chatbot ira falar  <br>
 * 2.0 adicionado metodo compareTo
 * @author Brenno A. M. Oliveira
 * @version 1.0
 * @since 1.0
 */
public class Lingua implements Comparable<Lingua>{
	private int cdLingua;
	private String lingua;
	private String sglLingua;
	public Lingua() {
		
	}
	public Lingua(int cdLingua) {
		setCdLingua(cdLingua);
	}
	public Lingua(int cdLingua,String lingua,String sglLingua) {
		setCdLingua(cdLingua);
		setLingua(lingua);
		setSglLingua(sglLingua);
	}
	/**
	 * Metodo compara o codigo de 2 dificuldades e retorna em ordem de menor para o maior
	 * @author Victor R. Lima
	 * @version 1.0
	 * @since 1.0
	 */
	public int compareTo(Lingua l) {
		if(this.cdLingua<l.getCdLingua())
			return -1;
		else if(this.cdLingua>l.cdLingua)
			return 1;
		else
			return 0;
	}
	public int getCdLingua() {
		return cdLingua;
	}
	public void setCdLingua(int cdLingua) {
		this.cdLingua = cdLingua;
	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public String getSglLingua() {
		return sglLingua;
	}
	public void setSglLingua(String sglLingua) {
		this.sglLingua = sglLingua;
	}
	public void setAll(int cdLingua,String lingua,String sglLingua) {
		setCdLingua(cdLingua);
		setLingua(lingua);
		setSglLingua(sglLingua);
	}
	public String getAll() {
		return getCdLingua()+getLingua()+getSglLingua();
	}
}
