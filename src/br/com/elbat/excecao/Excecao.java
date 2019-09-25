package br.com.elbat.excecao;

/**
 * Classe reponsavel pelo tratamento de exceção
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
		return "Esperava-se um String maior para a comparação ";
	}
	else if(e.getClass().toString().equals("class java.lang.NullPointerException")) {
		return "O metodo esta com valor null ";
	}
	else if(e.getClass().toString().equals("class java.sql.SQLSyntaxErrorException")) {
		return "identificador invalido";
	}
	else if(e.getClass().toString().equals("class java.sql.SQLIntegrityConstraintViolationException")) {
		return "Não se pode deletar essa linha porque ela é a chave estrangeira de outra";
	}
	else if(e.getClass().toString().equals("class java.sql.SQLDataException")) {
		return "valor maior que a precisão especificada usado para esta coluna";
	}
	else if(e.getClass().toString().equals("class java.sql.SQLIntegrityConstraintViolationException")) {
		return "restrição de verificação violada" ;
				
	}
	else {
		return "erro desconhecido";
	}
}
}
