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

@SuppressWarnings("serial") //commment
public class Depositar extends JFrame {
	public Depositar(Banco Nubank) {

		super("Depositar");
		Container frameC = this.getContentPane();
		frameC.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JTextField JTnumero = new JTextField();
		JTextField JTvalor = new JTextField();
		JButton JBdepositar = new JButton();
		JLabel JLnumeroLabel = new JLabel();
		JLabel JLvalorLabel = new JLabel();

		JTnumero.setText("");
		JTvalor.setText("");
		JBdepositar.setText("Depositar");
		JLnumeroLabel.setText("Numero da conta");
		JLvalorLabel.setText("Valor");

		JTnumero.setColumns(20);
		JTvalor.setColumns(20);

		JTnumero.setBounds(150, 30, 100, 30);
		JTvalor.setBounds(150, 70, 100, 30);
		JBdepositar.setBounds(150, 150, 100, 30);
		JLnumeroLabel.setBounds(50, 30, 100, 30);
		JLvalorLabel.setBounds(110, 70, 100, 30);

		frameC.add(JTnumero);
		frameC.add(JTvalor);
		frameC.add(JBdepositar);
		frameC.add(JLnumeroLabel);
		frameC.add(JLvalorLabel);

		JBdepositar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				depositar(JTnumero.getText(), JTvalor.getText());

			}
		});

		this.setSize(350, 250);
		this.setVisible(true);
	}

	private void depositar(String JTnumero, String JTvalor) {
		Connection connectionConta = new ConnectionConta();
		ArrayList<Conta> contas = connectionConta.retornarContas();
		int Numero;
		double Deposito;
		boolean valid = true;

		try {
			Numero = Integer.parseInt(JTnumero);
		} catch (NumberFormatException a) {
			JOptionPane.showMessageDialog(null, "Eh preciso inserir um numero em numero de conta");
			return;
		}

		try {
			Deposito = Double.parseDouble(JTvalor);
		} catch (NumberFormatException a) {
			JOptionPane.showMessageDialog(null, "Eh preciso inserir um numero em numero de valor");
			return;
		}

		for (int i = 0; i < contas.size(); i++) {
			if (contas.get(i).getNumeroConta() == Numero) {
				Conta contaVelha = clone(contas.get(i));
				contas.get(i).deposito(Deposito);
				connectionConta.removerConta(contaVelha);
				connectionConta.inserirConta(contas.get(i));
				JOptionPane.showMessageDialog(null, "Saldo atual � de: " + contas.get(i).getSaldo());
				valid = true;
				dispose();
				break;

			} else {
				valid = false;

			}

		}
		if (!valid) {
			JOptionPane.showMessageDialog(null, "Conta inexistente");
		}
	}

	private Conta clone(Conta conta) { //Clona os dados da conta para busca posterior no banco de dados
		Conta contaVelha = new Conta();
		contaVelha.setCpf(conta.getCpf());
		contaVelha.setNumeroConta(conta.getNumeroConta());
		contaVelha.setSaldo(conta.getSaldo());
		contaVelha.setSenha(conta.getSenha());
		contaVelha.setTipoConta(conta.getTipoConta());
		contaVelha.setClienteNome(conta.getClienteNome());

		return contaVelha;
	}

}
