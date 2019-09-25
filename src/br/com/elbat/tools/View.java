package br.com.elbat.tools;

import javax.swing.JOptionPane;

/**
 * Classe repsonsável para retornar diferentes tipos de JOptionPane
 * @author Brenno A. M. Oliveira
 * @see javax.swing.JOptionPane
 */

public class View {
	
	public static String string(String msg) {
		return JOptionPane.showInputDialog(msg);
	}
	public static int inteiro(String msg) {
		return Integer.parseInt(string(msg));
	}
	public static float fl (String msg) {
		return Float.parseFloat(string(msg));
	}
	public static double db(String msg) {
		return Double.parseDouble(string(msg));
	}
	public static int opcao(String msg) {
		return JOptionPane.showConfirmDialog(null, msg,"Favor escolher",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
	}
	public static boolean bl(String msg) {
		if(opcao(msg)==0) {
			return true;
		}
		else
			return false;
	}
	public static short shrt(String msg) {
		return Short.parseShort(string(msg));
	}
	public static byte bty(String msg) {
		return Byte.parseByte(string(msg));
	}
	public static long lng(String msg) {
		return Long.parseLong(string(msg));
	}
	public static int opcaoGenerica(String[] opcoes,String msg) {
		return JOptionPane.showOptionDialog(null, msg, "Favor escolher", 0,JOptionPane.QUESTION_MESSAGE, null, opcoes, 0);
	}
	public static char chr(String msg) {
		return string(msg).toUpperCase().charAt(0);
	}
}
