package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

	// Identifica e retorna o nome do Sistema Operacional
	private String os() {
		return System.getProperty("os.name");
	}

	/*
	 * Verifica o Sistema Operacional e, de acordo com o S.O., faz a chamada de
	 * configuração de IP.
	 */
	public String ip() {
		String os = os();
		if (os.contains("Windows")) {
			String out = ipWindows();
			if (out != null)
				return out;

		} else if (os.contains("Linux") || os.contains("Mac")) {
			String out = ipLinux();
			if (out != null)
				return out;

		} else {
			return "Unknown OS";
		}
		return "ERRO";

	}

	private String ipWindows() {
		// Create Process
		Process process = callProcess("IPCONFIG");

		if (process == null)
			return null;

		InputStreamReader reader = new InputStreamReader(process.getInputStream());
		try (BufferedReader buffer = new BufferedReader(reader)) {
			String line = buffer.readLine();
			StringBuffer text = new StringBuffer();
			text.append("# IPCONFIG");
			text.append("\r\n");

			while (line != null) {
				if (line.contains(":") && !line.contains(". . .")) {
					text.append(line.trim());
					text.append("\r\n");
				} else if (line.contains("IPv4")) {
					String[] linha = line.trim().split(":");
					text.append("- ");
					text.append(linha[1].trim());
					text.append("\r\n");
				}
				line = buffer.readLine();
			}
			if (process.isAlive()) {
				process.destroy();
			}
			return text.toString();
		} catch (Exception e) {
			// TODO: handle exception
			String msg = e.getMessage();
			System.err.println(msg);
		}

		return null;
	}

	/*
	 * private void killProcess(Long PID) { StringBuffer cmdPid = new
	 * StringBuffer(); cmdPid.append("TASKKILL /PID"); cmdPid.append(PID);
	 * callProcess(cmdPid.toString()); }
	 */
	private String ipLinux() {
		// Create Process
		boolean ifConfig = false, ipAdd = false;
		Process process = callProcess("ifconfig");
		// process = null;// Teste de ip addr
		if (process != null) {
			ifConfig = true;

		} else {
			process = callProcess("ip addr");

			if (process != null) {
				ipAdd = true;

			}
		}

		// Return String
		if (ifConfig) {
			String out = linuxReadIfConfig(process);
			// killProcess();
			return out;

		} else if (ipAdd) {
			String out = linuxReadIpAddr(process);
			return out;
		}
		return null;
	}

	private String linuxReadIfConfig(Process process) {
		InputStreamReader reader = new InputStreamReader(process.getInputStream());
		try (BufferedReader buffer = new BufferedReader(reader)) {
			String line = buffer.readLine();
			StringBuffer text = new StringBuffer();
			text.append("# ifconfig");
			text.append("\r\n");

			while (line != null) {

				if (line.contains("mtu")) {
					String[] linha = line.trim().split(" ");
					text.append(linha[0].trim());
					text.append("\r\n");

				} else if (line.contains("inet") && !line.contains("inet6")) {
					String[] linha = line.trim().split(" ");
					text.append("- ");
					text.append(linha[1].trim());
					text.append("\r\n");
				}

				line = buffer.readLine();
			}

			return text.toString();

		} catch (Exception e) {
			// TODO: handle exception
			String msg = e.getMessage();
			return msg;

		}

	}

	private String linuxReadIpAddr(Process process) {
		InputStreamReader reader = new InputStreamReader(process.getInputStream());
		try (BufferedReader buffer = new BufferedReader(reader)) {
			String line = buffer.readLine();
			StringBuffer text = new StringBuffer();
			text.append("# ip addr");
			text.append("\r\n");

			while (line != null) {

				if (line.contains("mtu")) {
					String[] linha = line.trim().split(" ");
					text.append(linha[1].trim());
					text.append("\r\n");

				} else if (line.contains("inet") && !line.contains("inet6")) {
					String[] linha = line.trim().split(" ");
					String[] len = linha[1].trim().split("/");
					text.append("- ");
					text.append(len[0].trim());
					text.append("\r\n");
				}

				line = buffer.readLine();
			}

			return text.toString();

		} catch (Exception e) {
			// TODO: handle exception
			String msg = e.getMessage();
			return msg;
		}
	}

	private Process callProcess(String proc) {
		try {
			String[] command = proc.split(" ");
			Process process = Runtime.getRuntime().exec(command);
			return process;

		} catch (Exception e) {
			String msg = e.getMessage();

			if (msg.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(proc);
				return callProcess(buffer.toString());

			} else {
				System.err.println(msg);
			}
		}
		return null;
	}

	/*
	 * verifica o Sistema Operacional e, de acordo com o S.O. e, faz a chamada de
	 * ping em IPv4 com 10 iterações.
	 */
	public String ping() {
		String os = os();
		if (os.contains("Windows")) {
			String out = pingWindows("www.google.com.br");
			if (out != null)
				return out;
		} else if (os.contains("Linux") || os.contains("Mac")) {
			String out = pingLinux("www.google.com.br");
			if (out != null)
				return out;
		} else {
			return "Unknown OS";
		}
		return "Erro";
	}

	private String pingWindows(String site) {
		StringBuffer text = new StringBuffer();
		text.append("ping -4 -n 10 ");
		text.append(site);
		Process ping = callProcess(text.toString());

		if (ping == null)
			return null;

		InputStreamReader reader = new InputStreamReader(ping.getInputStream());
		try (BufferedReader buffer = new BufferedReader(reader)) {
			String line = buffer.readLine();
			text = new StringBuffer();
			text.append("# Ping");
			text.append("\r\n");

			while (line != null) {
				if (line.contains("ms,")) {
					String[] linha = line.trim().split("=");
					text.append("Tempo Medio: ");
					text.append(linha[3].trim());
					text.append("\r\n");

				}
				line = buffer.readLine();
			}
			reader.close();
			return text.toString();
		} catch (Exception e) {
			String msg = e.getMessage();
			return msg;
		}

	}

	private String pingLinux(String site) {
		StringBuffer text = new StringBuffer();
		text.append("ping -4 -c 10 ");
		text.append(site);
		Process ping = callProcess(text.toString());

		if (ping == null)
			return null;

		InputStreamReader reader = new InputStreamReader(ping.getInputStream());
		try (BufferedReader buffer = new BufferedReader(reader)) {
			String line = buffer.readLine();
			text = new StringBuffer();
			text.append("# Ping");
			text.append("\r\n");

			while (line != null) {
				if (line.contains("rtt")) {
					String[] linha = line.trim().split("/");
					text.append("Tempo Medio: ");
					text.append(linha[4]);
					text.append("\r\n");

				}
				line = buffer.readLine();
			}
			reader.close();
			return text.toString();
		} catch (Exception e) {
			String msg = e.getMessage();
			return msg;
		}
	}
}
