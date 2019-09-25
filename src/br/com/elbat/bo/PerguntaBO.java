package br.com.elbat.bo;

import java.util.List;

import br.com.elbat.beans.Pergunta;
import br.com.elbat.dao.PerguntaDAO;
/**
 * Classe responsavel pela aplicação das regras de negocio na pergunta
 * @author Alexandre J. M. Silva
 * @version 1.0
 * @since 1.0
 * @see br.com.elbat.beans.Pergunta
 * @see br.com.elbat.dao.PerguntaDAO
 * @see java.util.List
 */
public class PerguntaBO {
	private PerguntaDAO pDAO=null;
	/**
	 * Método responsável por:
	 * garantir que o atributo pergunta tenha no minimo 2 letras e no maximo 80
	 * garantir que pergunta seja diferente de nulo
	 * garantir que codigo da pergunta esteja entre 1 e 9
	 * registrar nova pergunta
	 *  @author Alexandre J. M. Silva
	 * @param p
	 * @return String
	 * @throws Exception valida a exceção checked
	 */
public String registrarNovaPergunta(Pergunta p)throws Exception{

	pDAO=new PerguntaDAO();
	if(p.getPergunta().length()<2||p.getPergunta().length()>80) {
		return "Violação de regra para perguntas";
	}
	if(pDAO.consultarPorCd(p.getCdPergunta())!=null||p.getCdPergunta()<1||p.getCdPergunta()>9 ) {
		return "Violação de chave primaria";
	}
	p.setPergunta(p.getPergunta().toUpperCase());
	pDAO.gravarNovaPergunta(p);
	pDAO.fecharConexao();
	return"Pergunta registrada";
}
public List<Pergunta> listaDePerguntas()throws Exception{
	/**
	 * Método responsável por criar um lista de consulta de Perguntas
	 *  @author Alexandre J. M. Silva
	 * @return listaPerguntas
	 * @throws Exception valida o exceção checked
	 */
	pDAO=new PerguntaDAO();
	List<Pergunta> listaPerguntas=pDAO.consultarPergunta();
	pDAO.fecharConexao();
	return listaPerguntas;
}
public Pergunta consultarPerguntasPorCd(int pNumero)throws Exception{
	/**
	 * Método responsável por consultar Perguntas por codigo
	 *  @author Alexandre J. M. Silva
	 * @return Pergunta
	 * @throws Exception valida o exceção checked
	 */
	pDAO=new PerguntaDAO();
	Pergunta p=new Pergunta();
	p=pDAO.consultarPorCd(pNumero);
	pDAO.fecharConexao();
	return p;
}
}
