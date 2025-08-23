package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public Main() {
		super();
	}

	public static void main(String[] args) {
		RedesController RC = new RedesController();

		JOptionPane.showMessageDialog(null, RC.ip());
	}
}
