package br.com.elbat.bo;

import java.util.List;
import br.com.elbat.beans.Dificuldade;
import br.com.elbat.dao.DificuldadeDAO;
/**
 * Classe responsavel pela aplicação das regras de negocio na Dificuldade
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
 * Método responsável por:
 * Garantir que dificuldade não será nulo e não será maior que 9
 * Garantir que descrição de dificuldade poderá ter somente um desses valores "basico", "intermediário" e "avançado" 
 * @author Alexandre J. M. Silva
 * @param d
 * @return String
 * @throws Exception válida a exceção checked
 */
public String registrarNovaDificuldade(Dificuldade d)throws Exception{

	DificuldadeDAO difDAO=new DificuldadeDAO(); 
	if(difDAO.consultarPorCd(d.getCdDificuldade()).getDificuldade()!=null||d.getCdDificuldade()>9) {
		return "Violação de chave primaria";
	}
	if(!d.getDificuldade().toUpperCase().equals("BASICO")&&!d.getDificuldade().toUpperCase().equals("INTERMEDIARIO")&&!d.getDificuldade().toUpperCase().equals("AVANCADO")) {
		return "Violção de descrição";
	}
	d.setDificuldade(d.getDificuldade().toUpperCase());
	difDAO.gravarDificuldade(d);
	difDAO.fecharConexao();
	return "Dificuldade registrada";
}
/**
 * Método responsavel por consultar a dificuldade pelo código
 * @author Alexandre J. M. Silva
 * @param cdDificuldade
 * @return Dificuldade
 * @throws Exception válida a exceção checked
 */
public Dificuldade consultarDificuldadePorCd(int cdDificuldade)throws Exception{

	DificuldadeDAO difDAO=new DificuldadeDAO();
	Dificuldade d=new Dificuldade();
	d=difDAO.consultarPorCd(cdDificuldade);
	difDAO.fecharConexao();
	return d;
}
/**
 * Método responsavel por consultr e criar uma lista com todas as dificuldades
 * @author Alexandre J. M. Silva
 *@return listaDificuldade
 *@throws Exception valida a opção checked
 */
public List<Dificuldade> consultarTodasAsDificuldades() throws Exception{

	DificuldadeDAO difDAO=new DificuldadeDAO();
	List<Dificuldade> listaDificuldades=difDAO.consultarDificuldades();
	difDAO.fecharConexao();
	return listaDificuldades;
}
}
