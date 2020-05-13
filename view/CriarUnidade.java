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
import br.com.poo.sysfi.conexaoMongo.ConnectionUnidade;
import br.com.poo.sysfi.model.Banco;
import br.com.poo.sysfi.model.Unidade;

@SuppressWarnings("serial")
public class CriarUnidade extends JFrame {
	public CriarUnidade(Banco Nubank) {

		super("Criar Unidade");
		Container frame = this.getContentPane();
		frame.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JTextField JTnomeUnidade = new JTextField();
		JTextField JTsenhaUnidade = new JTextField();
		JButton JBcriar = new JButton();
		JLabel JLnomeUnidade = new JLabel();
		JLabel JLsenhaUnidade = new JLabel();

		JTnomeUnidade.setText("");
		JTsenhaUnidade.setText("");
		JBcriar.setText("Criar");
		JLnomeUnidade.setText("Numero da conta");
		JLsenhaUnidade.setText("Senha");

		JTnomeUnidade.setColumns(20);
		JTsenhaUnidade.setColumns(20);

		JTnomeUnidade.setBounds(150, 30, 100, 30);
		JTsenhaUnidade.setBounds(150, 70, 100, 30);
		JBcriar.setBounds(150, 120, 100, 30);
		JLnomeUnidade.setBounds(50, 30, 100, 30);
		JLsenhaUnidade.setBounds(50, 70, 100, 30);

		frame.add(JTnomeUnidade);
		frame.add(JTsenhaUnidade);
		frame.add(JBcriar);
		frame.add(JLnomeUnidade);
		frame.add(JLsenhaUnidade);

		JBcriar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evento) {

				conectarUnidade(JTnomeUnidade.getText(), JTsenhaUnidade.getText());
			}
		});

		this.setSize(350, 200);
		this.setVisible(true);
	}

	private void conectarUnidade(String nome, String senha) {
		Connection connectionUnidades = new ConnectionUnidade();
		ArrayList<Banco> unidades = connectionUnidades.retornarUnidades();

		if (unidades.size() > 0) { //Confere se já existe uma unidade com mesmo nome
			for (int i = 0; i < unidades.size(); i++) {
				if (unidades.get(i).getNomeUnidade().equals(nome)) {
					JOptionPane.showMessageDialog(null, "Unidade já existe");
					return;
				}
			}
		}
		Banco unidade = new Unidade();
		unidade.setNomeUnidade(nome);
		unidade.setSenha(senha);

		connectionUnidades.inserirUnidade(unidade);

		dispose();
	}
}
