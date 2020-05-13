package br.com.poo.sysfi.conexaoMongo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Banco;
import br.com.poo.sysfi.model.Cliente;
import br.com.poo.sysfi.model.Conta;

@SuppressWarnings({ "deprecation", "unused" })
public class Connection {
	DB BaseDados;
	DBCollection collection;
	/* Documentos que serão usados posteriormente */
	BasicDBObject DCCliente = new BasicDBObject();
	BasicDBObject DCConta = new BasicDBObject();
	BasicDBObject DCUnidade = new BasicDBObject();

	
	/* Conecta ao mongo DB */
	public Connection() { 
		try {
			@SuppressWarnings("resource")
			MongoClient mongo = new MongoClient("localhost", 27017);
			BaseDados = mongo.getDB("BancoSid");
			collection = BaseDados.getCollection("Contas");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Falha na conexão");

		}
	}

	public void inserirConta(Conta conta) {

	}

	public void inserirCliente(Cliente cliente) {

	}

	public void inserirUnidade(Banco unidade) {

	}

	public ArrayList<Conta> retornarContas() {
		return null;
	}

	public ArrayList<Cliente> retornarClientes() {
		return null;
	}

	public ArrayList<Banco> retornarUnidades() {
		return null;
	}

	public void atualizarConta(Conta contaVelha, Conta contaNova) {

	}

	public void atualizarCliente(Conta clienteVelho, Conta clienteNovo) {

	}
	
	public void removerConta(Conta conta) {
	}
	public void removerCliente(Cliente cliente) {
	}
	public void removerUnidade(Banco unidade) {
	}

}
