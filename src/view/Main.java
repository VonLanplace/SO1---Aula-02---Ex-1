package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public Main() {
		super();
	}

	/*
	 * A Classe Main.java deve dar as opções de chamadas do método ip ou do método
	 * ping com JOptionPane e, dependendo da escolha, instanciar a Classe
	 * RedesController.java e chamar o método escolhido. A opção de finalizar a
	 * aplicação também deve estar disponível.
	 */
	public static void main(String[] args) {
		boolean loop = true;
		RedesController RC = new RedesController();

		while (loop) {
			String[] options = { "Close", "Ip", "Ping" };
			int opc = JOptionPane.showOptionDialog(null, "Controle de Redes", null, 0, 0, null, options, options[0]);
			switch (opc) {
			case 1:
				JOptionPane.showMessageDialog(null, RC.ip());
				break;

			case 2:
				JOptionPane.showMessageDialog(null, RC.ping());
				break;

			case 0:
			case -1:
				loop = false;
				break;

			default:
				JOptionPane.showMessageDialog(null, "Unexpected value: " + opc);
			}
		}
	}
}
