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
import br.com.poo.sysfi.conexaoMongo.ConnectionCliente;
import br.com.poo.sysfi.conexaoMongo.ConnectionConta;
import br.com.poo.sysfi.model.Banco;
import br.com.poo.sysfi.model.Cliente;
import br.com.poo.sysfi.model.Conta;

@SuppressWarnings("serial")
public class RemoverCliente extends JFrame {
	public RemoverCliente(Banco Nubank) {

		super("Remover cliente");
		Container frameC = this.getContentPane();
		frameC.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTextField JTnumero = new JTextField();
		JButton JBremover = new JButton();
		JLabel JLnumeroLabel = new JLabel();

		JTnumero.setText("");
		JBremover.setText("Remover");
		JLnumeroLabel.setText("CPF Cliente");

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
				Connection connectionCliente = new ConnectionCliente();
				ArrayList<Cliente> clientes = connectionCliente.retornarClientes();
				@SuppressWarnings("unused")
				int Numero;
				boolean validade = false;
				try {
					Numero = Integer.parseInt(JTnumero.getText());
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Eh preciso inserir um CPF");
					return;
				}
				while (!validade) {
					for (int i = 0; i < contas.size(); i++) {
						if (contas.get(i).getCpf().equals(JTnumero.getText())) {
							JOptionPane.showMessageDialog(null,
									"Não foi possível excluir, existe uma conta vinculada ao cliente");
							return;
						} else if (clientes.size() == i) {
							validade = true;
							break;
						}
					}
					break validade = true;
				}
				if (clientes.size() > 0) {
					for (int i = 0; i < clientes.size(); i++) {
						if (clientes.get(i).getCpf().equals(JTnumero.getText())) {
							connectionCliente.removerCliente(clientes.get(i));
							JOptionPane.showMessageDialog(null, "Cliente removido do banco de dados");

						} else if (i == clientes.size()) {
							JOptionPane.showMessageDialog(null, "Cliente inválido");
						}

					}

				} else if (clientes.size() == 0) {
					JOptionPane.showMessageDialog(null, "Não há contas para serem apagadas");
				}
			}

		});

		this.setSize(350, 200);
		this.setVisible(true);
	}
}
