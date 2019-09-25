package br.com.elbat.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * Classe respons�vel por facilitar a conex�o com o banco
 * @author Brenno A. M. Oliveira
 * @version 1.0
 * @since 1.0
 * @see java.sql.DriverManager
 */
public class Conectar {
	/**
	 * M�todo respons�vel por realizar conex�o  
	 * @return Connection
	 * @throws Exception
	 */
	public static Connection conectar()throws Exception {
		return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM77832", "110499");
	}
}
