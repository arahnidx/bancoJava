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
public class MainAdmin extends JFrame {

	public MainAdmin(String banco, Banco Nubank, String unidade) {
		super(banco);

		Container frame = this.getContentPane();
		frame.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton JBadicionarConta = new JButton();
		JButton JBremoverConta = new JButton();
		JButton JBdepositar = new JButton();
		JButton JBsacar = new JButton();
		JButton JBsair = new JButton();
		JButton JBcriarUnidade = new JButton();
		JButton JBadicionarCliente = new JButton();
		JButton JBremoverCliente = new JButton();
		JButton JBatualizarTabelas = new JButton();
		JButton JBeditarCliente = new JButton();
		JLabel JLboasvindas = new JLabel();

		JBadicionarConta.setText("Adicionar Conta");
		JBremoverConta.setText("Remover Conta");
		JBdepositar.setText("Depositar");
		JBsacar.setText("Sacar");
		JBsair.setText("Sair");
		JBcriarUnidade.setText("Criar Unidade");
		JBadicionarCliente.setText("Adicionar Cliente");
		JBremoverCliente.setText("Remover Cliente");
		JLboasvindas.setText("Bem vindo à unidade " + unidade);
		JBatualizarTabelas.setText("Atualizar tabelas");
		JBeditarCliente.setText("Editar cliente");

		JBadicionarConta.setBounds(50, 30, 150, 30);
		JBremoverConta.setBounds(210, 30, 150, 30);
		JBdepositar.setBounds(370, 30, 100, 30);
		JBsacar.setBounds(480, 30, 100, 30);
		JBsair.setBounds(480, 550, 100, 30);
		JBcriarUnidade.setBounds(50, 290, 115, 30);
		JBadicionarCliente.setBounds(172, 290, 135, 30);
		JBremoverCliente.setBounds(312, 290, 130, 30);
		JLboasvindas.setBounds(30, 1, 200, 20);
		JBatualizarTabelas.setBounds(50, 550, 130, 30);
		JBeditarCliente.setBounds(448,290,130,30);

		frame.add(JBadicionarConta);
		frame.add(JBremoverConta);
		frame.add(JBdepositar);
		frame.add(JBsacar);
		frame.add(JBsair);
		frame.add(JBcriarUnidade);
		frame.add(JBadicionarCliente);
		frame.add(JBremoverCliente);
		frame.add(JLboasvindas);
		frame.add(JBatualizarTabelas);
		frame.add(JBeditarCliente);

		JTableContas(frame, 0);
		JTableClientes(frame, 0);
		
		JBeditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				JOptionPane.showMessageDialog(null, "Função desabilitada, será habilitada na versão 0.2");
			}
		});
		
		JBatualizarTabelas.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				JTableContas(frame, 1);
				JTableClientes(frame, 1);
			}
		});

		JBadicionarConta.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				new criarConta(Nubank);
			}
		});
		JBremoverConta.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				new Removerconta(Nubank);
			}
		});
		JBremoverCliente.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				new RemoverCliente(Nubank);
			}
		});

		JBdepositar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				new Depositar(Nubank);
			}
		});

		JBsacar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				new Sacar(Nubank);
			}
		});

		JBsair.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				System.exit(0);
			}
		});

		JBcriarUnidade.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				new CriarUnidade(Nubank);
			}
		});
		JBadicionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				new criarCliente(Nubank);
			}
		});

		this.setSize(700, 650);
		this.setVisible(true);

	}

	private void JTableContas(Container frame, int opt) {
		Connection connectionConta = new ConnectionConta();
		ArrayList<Conta> contas = connectionConta.retornarContas();
		String[][] dados = new String[contas.size()][5];
		if (contas.size() > 0) {
			for (int i = 0; i < contas.size(); i++) {

				for (int j = 0; j < 5; j++) {

					if (j == 0) {
						dados[i][j] = contas.get(i).getClienteNome();
					} else if (j == 1) {
						dados[i][j] = contas.get(i).getCpf();
					} else if (j == 2) {
						dados[i][j] = contas.get(i).getTipoConta();
					} else if (j == 3) {
						dados[i][j] = Integer.toString(contas.get(i).getNumeroConta());
					} else if (j == 4) {
						dados[i][j] = "R$  " + Double.toString(contas.get(i).getSaldo());
					}
				}
			}
		}
		String[] colunas = { "Nome", "CPF", "Tipo", "Numero", "Saldo" };
		JTable tabelaContas = new JTable(dados, colunas);
		tabelaContas.setPreferredScrollableViewportSize(new Dimension(530, 30));
		tabelaContas.setFillsViewportHeight(true);
		tabelaContas.setBounds(50, 80, 530, 200);
		JScrollPane scrollpaneContas = new JScrollPane(tabelaContas);
		scrollpaneContas.setBounds(50, 80, 530, 200);

		if (opt == 0) {
			frame.add(scrollpaneContas);
		} else {
			frame.remove(scrollpaneContas);
			frame.add(scrollpaneContas);
		}

	}

	private void JTableClientes(Container frame, int opt) {
		Connection connectionCliente = new ConnectionCliente();
		ArrayList<Cliente> clientes = connectionCliente.retornarClientes();
		String[][] dados = new String[clientes.size()][3];
		if (clientes.size() > 0) {
			for (int i = 0; i < clientes.size(); i++) {

				for (int j = 0; j < 3; j++) {

					if (j == 0) {
						dados[i][j] = clientes.get(i).getNome();
					} else if (j == 1) {
						dados[i][j] = clientes.get(i).getCpf();

					} else if (j == 2) {
						dados[i][j] = clientes.get(i).getEndereco();
					}
				}
			}
		}
		String[] colunas = { "Nome", "CPF", "Endereco" };

		JTable tabelaClientes = new JTable(dados, colunas);
		tabelaClientes.setPreferredScrollableViewportSize(new Dimension(530, 30));
		tabelaClientes.setFillsViewportHeight(true);
		tabelaClientes.setBounds(50, 340, 530, 200);
		JScrollPane scrollpaneClientes = new JScrollPane(tabelaClientes);
		scrollpaneClientes.setBounds(50, 340, 530, 200);
		if (opt == 0) {
			frame.add(scrollpaneClientes);
		} else {
			frame.remove(scrollpaneClientes);
			frame.add(scrollpaneClientes);
		}
	}

}