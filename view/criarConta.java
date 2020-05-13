package br.com.poo.sysfi.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import br.com.poo.sysfi.conexaoMongo.Connection;
import br.com.poo.sysfi.conexaoMongo.ConnectionCliente;
import br.com.poo.sysfi.conexaoMongo.ConnectionConta;
import br.com.poo.sysfi.model.*;

@SuppressWarnings("serial")
public class criarConta extends JFrame {

	public criarConta(Banco Nubank) {

		super("Criar conta");

		Container frameC = this.getContentPane();
		frameC.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JComboBox<String> JCselect = new JComboBox<String>();
		JCselect.addItem("Conta Corrente");
		JCselect.addItem("Conta Poupanca");

		JTextField JTsenha = new JPasswordField();
		JTextField JTcpf = new JTextField();
		JTextField JTdepositoInicial = new JTextField();
		JButton JBcriarConta = new JButton();
		JLabel JLpass = new JLabel();
		JLabel JLcpf = new JLabel();
		JLabel JLdeposit = new JLabel();

		JTsenha.setText("");
		JTcpf.setText("");
		JTdepositoInicial.setText("");
		JBcriarConta.setText("Criar Conta");
		JLcpf.setText("CPF");
		JLdeposit.setText("Deposito inicial");
		JLpass.setText("Senha");

		JTsenha.setColumns(20);
		JTcpf.setColumns(20);
		JTdepositoInicial.setColumns(20);

		JCselect.setBounds(280, 30, 100, 30);
		JTsenha.setBounds(150, 110, 100, 30);
		JTcpf.setBounds(150, 70, 100, 30);
		JTdepositoInicial.setBounds(150, 190, 100, 30);
		JBcriarConta.setBounds(150, 240, 100, 30);
		JLpass.setBounds(110, 110, 100, 30);
		JLcpf.setBounds(120, 70, 100, 30);
		JLdeposit.setBounds(60, 190, 100, 30);

		frameC.add(JCselect);
		frameC.add(JTsenha);
		frameC.add(JTcpf);
		frameC.add(JTdepositoInicial);
		frameC.add(JBcriarConta);
		frameC.add(JLpass);
		frameC.add(JLcpf);
		frameC.add(JLdeposit);

		JBcriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				makeConta(JTsenha.getText(), JTcpf.getText(), JCselect.getSelectedItem().toString(),
						JTdepositoInicial.getText(), Nubank);
			}
		});

		this.setSize(400, 350);
		this.setVisible(true);
	}

	private void makeConta(String senha, String cpf, String tipoConta, String deposito, Banco Nubank) {
		Connection connectionConta = new ConnectionConta();
		Connection connectionClientes = new ConnectionCliente();
		ArrayList<Cliente> clientes = connectionClientes.retornarClientes();
		ArrayList<Conta> contas = connectionConta.retornarContas();

		double Deposito = -1;
		boolean validade = false;
		String ClienteNome = null;
		while (!validade) { //Confere se já existe uma conta cadastrada com esse cpf
			if (contas.size() > 0) {
				for (int i = 0; i < contas.size(); i++) {
					if (contas.get(i).getCpf().equals(cpf)) {
						JOptionPane.showMessageDialog(null, "Usuario já tem uma conta");
						return;
					} else {
						validade = true;
					}

				}
			} else {
				validade = true;
			}
		}

		validade = false;

		while (!validade) { //Confere se existe um cliente cadastrado para este cpf, se não existir não pode criar uma conta
			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.size() > 0) {
					if (clientes.get(i).getCpf().equals(cpf)) {
						ClienteNome = clientes.get(i).getNome();
						validade = true;
						break;
					} else if (i >= clientes.size()) {
						JOptionPane.showMessageDialog(null, "É preciso cadastrar o cliente antes de criar uma conta");
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Não existem clientes cadastrados");
				}
			}

		}
		validade = false;
		while (!validade) { 
			try {  //Confere se foi inserido um número no campo de deposito inicial
				Deposito = Double.parseDouble(deposito);
			} catch (NumberFormatException a) {
				JOptionPane.showMessageDialog(null, "Eh preciso inserir um numero em deposito inicial");
				return;
			}
			if (Deposito < 0) {
				JOptionPane.showMessageDialog(null, "Saldo inicial deve ser maior ou igual a zero");
				return;
			} else if (Deposito >= 0) {
				validade = true;
			}
		}

		if (senha.isEmpty() || cpf.isEmpty() || deposito.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nao pode deixar espaços vazios!");
			return;
		}
		if (tipoConta.equalsIgnoreCase("Conta corrente")) {
			Conta conta = new ContaC(cpf, senha);
			conta.setSaldo(Deposito);
			conta.setClienteNome(ClienteNome);
			connectionConta.inserirConta(conta);
		} else if (tipoConta.equalsIgnoreCase("Conta poupanca")) {
			Conta conta = new ContaP(cpf, senha);
			conta.setSaldo(Deposito);
			conta.setClienteNome(ClienteNome);
			connectionConta.inserirConta(conta);
		}

		JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");

		dispose();
	}
}