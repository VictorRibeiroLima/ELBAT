package br.com.elbat.excecao;

/**
 * Classe reponsavel pelo tratamento de exce��o
 * @author Victor R. de Lima
 * @version 1.0
 * @since 1.0
 */

public class Excecao extends Exception{
private static final long serialVersionUID = 1L;

public static String tratamentoExcecao(Exception e) {
	if(e.getClass().toString().equals("class java.lang.NumberFormatException")) {
		return "Tipo de dado invalido ";
	}
	else if(e.getClass().toString().equals("class java.lang.ArithmeticException")) {
		return "Erro em conta matematica ";
	}
	else if(e.getClass().toString().equals("class java.lang.StringIndexOutOfBoundsException")) {
		return "Esperava-se um String maior para a compara��o ";
	}
	else if(e.getClass().toString().equals("class java.lang.NullPointerException")) {
		return "O metodo esta com valor null ";
	}
	else if(e.getClass().toString().equals("class java.sql.SQLSyntaxErrorException")) {
		return "identificador invalido";
	}
	else if(e.getClass().toString().equals("class java.sql.SQLIntegrityConstraintViolationException")) {
		return "N�o se pode deletar essa linha porque ela � a chave estrangeira de outra";
	}
	else if(e.getClass().toString().equals("class java.sql.SQLDataException")) {
		return "valor maior que a precis�o especificada usado para esta coluna";
	}
	else if(e.getClass().toString().equals("class java.sql.SQLIntegrityConstraintViolationException")) {
		return "restri��o de verifica��o violada" ;
				
	}
	else {
		return "erro desconhecido";
	}
}
}
