package br.com.poo.sysfi.view;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.poo.sysfi.model.*;
import br.com.poo.sysfi.conexaoMongo.*;

@SuppressWarnings("serial")
public class Login extends JFrame {

	public Login(String admin, String senhaMestre, Banco banco) /* Entram os dados de login na interface de login */ {

		super("Login");
		Container frameC = this.getContentPane();
		frameC.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComboBox<String> select = new JComboBox<String>(); /* caixa de sele��o */
		JTextField JTusuario = new JTextField(); /* campo de login */
		JTextField JTsenha = new JPasswordField(); /* campo de senha */
		JButton JBlogin = new JButton();
		/* bot�o e r�tulo */
		JButton JBsair = new JButton();
		JLabel JLusuario = new JLabel();
		JLabel JLsenha = new JLabel();
		/* box de sele��o */
		select.addItem("Cliente");
		select.addItem("Funcionario");
		select.addItem("Admin");

		JTusuario.setText("");
		JTsenha.setText("");
		JBlogin.setText("Login");

		JBsair.setText("Sair");
		JLusuario.setText("Usuario");
		JLsenha.setText("Senha");

		select.setBounds(280, 30, 100, 30);
		JTusuario.setBounds(150, 70, 100, 30);
		JTusuario.setColumns(20);
		JTsenha.setBounds(150, 110, 100, 30);
		JTsenha.setColumns(20);
		JBlogin.setBounds(150, 140, 100, 30);

		JBsair.setBounds(210, 240, 100, 30);
		JLusuario.setBounds(100, 70, 100, 30);
		JLsenha.setBounds(110, 110, 100, 30);

		frameC.add(select);
		frameC.add(JTusuario);
		frameC.add(JTsenha);
		frameC.add(JBlogin);

		frameC.add(JBsair);
		frameC.add(JLusuario);
		frameC.add(JLsenha);

		JBsair.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				System.exit(0);
			}
		});

		JBlogin.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {
				JBLoginF(admin, senhaMestre, banco, JTusuario.getText(), JTsenha.getText(),
						select.getSelectedItem().toString());
			}
		});

		this.setSize(400, 350);
		this.setVisible(true);
	}

	private void JBLoginF(String admin, String senhaMestre, Banco banco, String usuario, String senha, String select) {
		Connection connectionConta = new ConnectionConta();
		Connection connectionUnidades = new ConnectionUnidade();
		ArrayList<Banco> unidades = connectionUnidades.retornarUnidades();
		ArrayList<Conta> contas = connectionConta.retornarContas();

		if (select.contentEquals("Cliente")) {
			for (int i = 0; i < contas.size(); i++) {
				if (contas.get(i).getSenha().equals(senha) && contas.get(i).getCpf().equals(usuario)) {
					JOptionPane.showMessageDialog(null, "Acesso Permitido");
					new ClienteUI(banco, contas.get(i), connectionConta);
					dispose();
					return;

				}
			}
			JOptionPane.showMessageDialog(null, "Login invalido");

		} else if (select.contentEquals("Funcionario")) {

			if (unidades.size() == 0) {
				JOptionPane.showMessageDialog(null, "N�o existem unidades cadastradas");
				return;
			}
			for (int i = 0; i < unidades.size(); i++) {
				if (unidades.get(i).getSenha().equals(senha) && unidades.get(i).getNomeUnidade().equals(usuario)) {
					new MainAdmin("Nubank", banco, unidades.get(i).getNomeUnidade());
					dispose();
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Acesso inv�lido");
				}
			}
		} else if (select.contentEquals("Admin")) {
			if (senhaMestre.equals(senha) && admin.equals(usuario)) {
				new MainAdmin("Nubank", banco, "Admin");
				dispose();
			}

		}
	}

}
