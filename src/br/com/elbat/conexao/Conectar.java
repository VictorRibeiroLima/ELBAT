package br.com.elbat.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * Classe responsável por facilitar a conexão com o banco
 * @author Brenno A. M. Oliveira
 * @version 1.0
 * @since 1.0
 * @see java.sql.DriverManager
 */
public class Conectar {
	/**
	 * Método responsável por realizar conexão  
	 * @return Connection
	 * @throws Exception
	 */
	public static Connection conectar()throws Exception {
		return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM77832", "110499");
	}
}
