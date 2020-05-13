package br.com.poo.sysfi.view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import br.com.poo.sysfi.conexaoMongo.Connection;
import br.com.poo.sysfi.conexaoMongo.ConnectionConta;
import br.com.poo.sysfi.model.Banco;
import br.com.poo.sysfi.model.Conta;
import java.awt.*;

/* Interface onde o cliente gerencia sua conta, permite depositos, saques, consulta de numero da conta, nome de usario e saldo */

@SuppressWarnings("serial")
public class ClienteUI extends JFrame {

	public ClienteUI(Banco Nubank, Conta conta, Connection connectionConta) {

		super("Cliente");

		Container frameC = this.getContentPane();
		frameC.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String saldo = Double.toString(conta.getSaldo());

		// Objetos que compõem a tela
		JLabel JLnumeroConta = new JLabel();
		JLabel JLsaldo = new JLabel();
		JButton JBsaque = new JButton();
		JButton Bdeposito = new JButton();
		// Define o que está escrito nos objetos
		JLnumeroConta.setText("Bem vindo " + conta.getClienteNome() + "  Conta número: " + conta.getNumeroConta());
		JLsaldo.setText("Saldo: R$" + saldo);
		JBsaque.setText("Sacar");
		Bdeposito.setText("Depositar");
		// Define as posições e espaços que os objetos irão ocupar
		JLnumeroConta.setBounds(15, 10, 300, 30);
		JLsaldo.setBounds(50, 30, 300, 30);
		JBsaque.setBounds(80, 100, 100, 30);
		Bdeposito.setBounds(80, 150, 100, 30);
		// Adicionar os elementos à tela
		frameC.add(JLnumeroConta);
		frameC.add(JLsaldo);
		frameC.add(JBsaque);
		frameC.add(Bdeposito);

		Bdeposito.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {

				JLsaldo.setText("Saldo: R$" + Deposito(conta));
			}
		});

		JBsaque.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {

				JLsaldo.setText("Saldo: R$" + Saque(conta));
			}
		});

		this.setSize(300, 320);
		this.setVisible(true);
	}
	// Clona a conta para fazer substituição de valores antigos por valores novos

	private Conta clone(Conta conta) {
		Conta contaVelha = new Conta();
		contaVelha.setCpf(conta.getCpf());
		contaVelha.setNumeroConta(conta.getNumeroConta());
		contaVelha.setSaldo(conta.getSaldo());
		contaVelha.setSenha(conta.getSenha());
		contaVelha.setTipoConta(conta.getTipoConta());
		contaVelha.setClienteNome(conta.getClienteNome());

		return contaVelha;
	}
	// Faz o depósito

	private String Deposito(Conta conta) {

		Connection connectionConta = new ConnectionConta(); // Instancia uma nova conexão com collection de contas
		double Deposito;
		Conta contaVelha = clone(conta);

		String DepositoEntrada = JOptionPane.showInputDialog("Quanto deseja depositar?");

		try {// Confere se o valor inserido é double
			Deposito = Double.parseDouble(DepositoEntrada);
		} catch (NumberFormatException a) {
			JOptionPane.showMessageDialog(null, "Eh preciso inserir um numero em numero de valor");
			String saldoAtual = Double.toString(conta.getSaldo());
			return saldoAtual;
		}

		conta.deposito(Deposito);

		connectionConta.removerConta(contaVelha);// Essas duas linhas fazem o update pois não consegui fazer o métood de
													// atualizar funcionar
		connectionConta.inserirConta(conta);

		String saldoAtual = Double.toString(conta.getSaldo()); // Transforma o saldo em String para mostrar na
																// UserInterface
		return saldoAtual;

	}
	// Faz o saque

	private String Saque(Conta conta) {
		Connection connectionConta = new ConnectionConta();
		Conta contaVelha = clone(conta);
		double Saque;

		String SaqueEntrada = JOptionPane.showInputDialog("Quanto deseja sacar?");

		try {// Confere se o valor inserido é double
			Saque = Double.parseDouble(SaqueEntrada);
		} catch (NumberFormatException a) {
			JOptionPane.showMessageDialog(null, "Eh preciso inserir um numero em numero de valor");
			String saldoAtual = Double.toString(conta.getSaldo());
			return saldoAtual;
		}

		conta.saque(Saque);

		connectionConta.removerConta(contaVelha); // Essas duas linhas fazem o update pois não consegui fazer o método
													// de atualizar funcionar
		connectionConta.inserirConta(conta);

		String saldoAtual = Double.toString(conta.getSaldo()); // Transforma o saldo em String para mostrar na
																// UserInterface
		return saldoAtual;
	}
}
