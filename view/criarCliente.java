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
import br.com.poo.sysfi.model.Banco;
import br.com.poo.sysfi.model.Cliente;

@SuppressWarnings("serial")
public class criarCliente extends JFrame {

	public criarCliente(Banco Nubank) {
		super("Criar Cliente");

		Container frameC = this.getContentPane();
		frameC.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JTextField JTnome = new JTextField();
		JTextField JTcpf = new JTextField();
		JTextField JTendereco = new JTextField();
		JButton JBcriarConta = new JButton();
		JLabel JLnome = new JLabel();
		JLabel JLcpf = new JLabel();
		JLabel JLenderec = new JLabel();

		JTnome.setText("");
		JTcpf.setText("");
		JTendereco.setText("");
		JBcriarConta.setText("Cadastrar");
		JLnome.setText("Nome");
		JLcpf.setText("CPF");
		JLenderec.setText("Endereço");

		JTnome.setColumns(20);
		JTcpf.setColumns(20);
		JTendereco.setColumns(20);

		JTnome.setBounds(150, 30, 100, 30);
		JTcpf.setBounds(150, 110, 100, 30);
		JTendereco.setBounds(150, 150, 100, 30);
		JBcriarConta.setBounds(150, 240, 100, 30);
		JLnome.setBounds(100, 30, 100, 30);
		JLcpf.setBounds(120, 110, 100, 30);
		JLenderec.setBounds(90, 150, 100, 30);

		frameC.add(JTnome);
		frameC.add(JTcpf);
		frameC.add(JTendereco);
		frameC.add(JBcriarConta);
		frameC.add(JLnome);
		frameC.add(JLcpf);
		frameC.add(JLenderec);

		JBcriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				makeCliente(JTnome.getText(), JTcpf.getText(), JTendereco.getText());
			}
		});

		this.setSize(400, 350);
		this.setVisible(true);
	}

	private void makeCliente(String nome, String cpf, String endereco) {
		Connection connectionClientes = new ConnectionCliente();
		ArrayList<Cliente> clientes = connectionClientes.retornarClientes();
		boolean validade = false;

		while (!validade) { //confere se o cpf do cliente já é cadastrado
			if (clientes.size() > 0) {

				for (int i = 0; i < clientes.size(); i++) {
					if (clientes.get(i).getCpf().equals(cpf)) {
						JOptionPane.showMessageDialog(null, "Usuario já cadastrado");
						return;
					} else {
						validade = true;
					}
				}
			} else {
				validade = true;
			}
		}

		if (nome.isEmpty() || cpf.isEmpty() || endereco.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nao pode deixar espaços vazios!");
			return;
		}
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setEndereco(endereco);
		cliente.setNumeroConta(null);
		connectionClientes.inserirCliente(cliente);

		JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
		dispose();
	}
}
