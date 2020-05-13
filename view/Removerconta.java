package br.com.poo.sysfi.view;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.poo.sysfi.conexaoMongo.Connection;
import br.com.poo.sysfi.conexaoMongo.ConnectionConta;
import br.com.poo.sysfi.model.Banco;
import br.com.poo.sysfi.model.Conta;

@SuppressWarnings("serial")
public class Removerconta extends JFrame {

	public Removerconta(Banco Nubank) {

		super("Remover conta");
		Container frameC = this.getContentPane();
		frameC.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JTextField JTnumero = new JTextField();
		JButton JBremover = new JButton();
		JLabel JLnumeroLabel = new JLabel();

		JTnumero.setText("");
		JBremover.setText("Remover");
		JLnumeroLabel.setText("Numero da conta");

		JTnumero.setBounds(150, 30, 100, 30);
		JBremover.setBounds(150, 100, 100, 30);
		JLnumeroLabel.setBounds(50, 30, 100, 30);
		JTnumero.setColumns(20);

		frameC.add(JTnumero);
		frameC.add(JBremover);
		frameC.add(JLnumeroLabel);

		JBremover.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				Connection connectionConta = new ConnectionConta();
				ArrayList<Conta> contas = connectionConta.retornarContas();
				int Numero;
				try {
					Numero = Integer.parseInt(JTnumero.getText());
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Eh preciso inserir um numero em numero de conta");
					return;
				}
				if (contas.size() >= 1) {
					for (int i = 0; i < contas.size(); i++) {
						if (contas.get(i).getNumeroConta() == Numero) {
							connectionConta.removerConta(contas.get(i));
							JOptionPane.showMessageDialog(null, "Conta apagada");
							break;

						} else {
							JOptionPane.showMessageDialog(null, "Conta nao existe");
						}
					}
				}

				else {
					JOptionPane.showMessageDialog(null, "Não há contas para serem apagadas");
				}

			}
		});

		this.setSize(350, 250);
		this.setVisible(true);
	}
}
