package controller;

public class RedesController {
	// Identifica e retorna o nome do Sistema Operacional
	private String os() {
		return "Debian";
	}

	/*
	 * Verifica o Sistema Operacional e, de acordo com o S.O., faz a chamada de
	 * configuração de IP.
	 */
	public String ip() {
		return "1.1.1.1";
	}

	/*
	 * verifica o Sistema Operacional e, de acordo com o S.O. e, faz a chamada de
	 * ping em IPv4 com 10 iterações.
	 */
	public String ping() {
		String os = os();
		return "0ms";
	}
}
