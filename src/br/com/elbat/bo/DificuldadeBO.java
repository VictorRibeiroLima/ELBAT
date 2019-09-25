package br.com.elbat.bo;

import java.util.List;
import br.com.elbat.beans.Dificuldade;
import br.com.elbat.dao.DificuldadeDAO;
/**
 * Classe responsavel pela aplica��o das regras de negocio na Dificuldade
 * @author Alexandre J. M. Silva
 * @version 1.0
 * @since 1.0
 * @see br.com.elbat.beans.Dificuldade
 * @see br.com.elbat.dao.DificuldadeDAO
 */

public class DificuldadeBO {
public DificuldadeBO() {
	
}
/**
 * M�todo respons�vel por:
 * Garantir que dificuldade n�o ser� nulo e n�o ser� maior que 9
 * Garantir que descri��o de dificuldade poder� ter somente um desses valores "basico", "intermedi�rio" e "avan�ado" 
 * @author Alexandre J. M. Silva
 * @param d
 * @return String
 * @throws Exception v�lida a exce��o checked
 */
public String registrarNovaDificuldade(Dificuldade d)throws Exception{

	DificuldadeDAO difDAO=new DificuldadeDAO(); 
	if(difDAO.consultarPorCd(d.getCdDificuldade()).getDificuldade()!=null||d.getCdDificuldade()>9) {
		return "Viola��o de chave primaria";
	}
	if(!d.getDificuldade().toUpperCase().equals("BASICO")&&!d.getDificuldade().toUpperCase().equals("INTERMEDIARIO")&&!d.getDificuldade().toUpperCase().equals("AVANCADO")) {
		return "Viol��o de descri��o";
	}
	d.setDificuldade(d.getDificuldade().toUpperCase());
	difDAO.gravarDificuldade(d);
	difDAO.fecharConexao();
	return "Dificuldade registrada";
}
/**
 * M�todo responsavel por consultar a dificuldade pelo c�digo
 * @author Alexandre J. M. Silva
 * @param cdDificuldade
 * @return Dificuldade
 * @throws Exception v�lida a exce��o checked
 */
public Dificuldade consultarDificuldadePorCd(int cdDificuldade)throws Exception{

	DificuldadeDAO difDAO=new DificuldadeDAO();
	Dificuldade d=new Dificuldade();
	d=difDAO.consultarPorCd(cdDificuldade);
	difDAO.fecharConexao();
	return d;
}
/**
 * M�todo responsavel por consultr e criar uma lista com todas as dificuldades
 * @author Alexandre J. M. Silva
 *@return listaDificuldade
 *@throws Exception valida a op��o checked
 */
public List<Dificuldade> consultarTodasAsDificuldades() throws Exception{

	DificuldadeDAO difDAO=new DificuldadeDAO();
	List<Dificuldade> listaDificuldades=difDAO.consultarDificuldades();
	difDAO.fecharConexao();
	return listaDificuldades;
}
}
