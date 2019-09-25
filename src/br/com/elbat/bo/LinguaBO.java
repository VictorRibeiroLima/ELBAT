package br.com.elbat.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.elbat.beans.Lingua;
import br.com.elbat.dao.LinguaDAO;
/**
 * Classe responsavel pela aplicação das regras de negocio na Lingua
 * @author Victor R. de Lima
 * @version 1.0
 * @since 1.0
 * @see br.com.elbat.beans.Lingua
 * @see br.com.elbat.dao.LinguaDAO
 * @see java.util.ArrayList
 * @see java.util.List
 */


public class LinguaBO {
	/**
	 * Método responsável por:
	 * validar cdLingua - cdLingua não pode ser maior que 9, menor que 0 e nulo<br>
	 * garantir que o valor de lingua seja ou 'portugues' ou 'ingles'<br>
	 * garantir que a singla da lingua se por portugues a sigla deve ser PRT e se for ingles a sigla deve ser ING
	 * @author Alexandre J. M. Silva
	 * @param l
	 * @return String
	 * @throws Exception
	 */
public String preencherNovaLingua(Lingua l)throws Exception{

	LinguaDAO lDAO=new LinguaDAO();
	l.setLingua(l.getLingua().toUpperCase());
	l.setSglLingua(l.getSglLingua().toUpperCase());
	if(l.getCdLingua()>9 || l.getCdLingua()<0||lDAO.consultasPorCd(l.getCdLingua()).getLingua()!=null) {
		return "violçao de regra de chave unica";
	}
	if(l.getLingua().equals("PORTUGUES") || l.getLingua().equals("INGLES")) {
		return "violação de regra de descrição de lingua";
	}
	if(l.getLingua().equals("PORTUGUES") && !l.getSglLingua().equals("PRT")|| l.getLingua().equals("INGLES") && !l.getSglLingua().equals("ING") )
		return "violação de regra de sigla de lingua";
	lDAO.gravarLingua(l);
	lDAO.fecharConexao();
	return "Registro feito com sucesso";
}
/**
 * Método responsavel por consultar e criar lista de Linguas
 *  @author Alexandre J. M. Silva
 * @return lista
 * @throws Exception valida a exceção checked
 */
public List<Lingua> consultarLinguas()throws Exception{

	LinguaDAO lDAO=new LinguaDAO();
	List<Lingua> lista=new ArrayList<Lingua>();
	lista=lDAO.consultarLingua();
	lDAO.fecharConexao();
	return lista;
}
/**
 * Método responsavel por consultar por código
 *  @author Alexandre J. M. Silva
 * @param pNumero - int
 * @return Lingua
 * @throws Exception valida a exceção checked
 */
public Lingua consultarPorCd(int pNumero) throws Exception{

	LinguaDAO lDAO=new LinguaDAO();
	Lingua l=new Lingua();
	l=lDAO.consultasPorCd(pNumero);
	lDAO.fecharConexao();
	return l;
	
}

}
