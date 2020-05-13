package br.com.poo.sysfi.conexaoMongo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import br.com.poo.sysfi.model.Conta;

public class ConnectionConta extends Connection {
	
	@SuppressWarnings("deprecation")
	public ConnectionConta() { 
		try {
			@SuppressWarnings("resource")
			MongoClient mongo = new MongoClient("localhost", 27017);
			BaseDados = mongo.getDB("BancoSid");
			collection = BaseDados.getCollection("Contas");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

	public void inserirConta(Conta conta) { /* Insere conta */
		DBCollection collection = BaseDados.getCollection("Contas");
		if (collection == null) {
			collection = BaseDados.createCollection("Contas", new BasicDBObject());
		}
		DCConta.append(Conta.CPF, conta.getCpf());
		DCConta.append(Conta.NUMEROCONTA, conta.getNumeroConta());
		DCConta.append(Conta.SALDO, conta.getSaldo());
		DCConta.append(Conta.SENHA, conta.getSenha());
		DCConta.append(Conta.TIPOCONTA, conta.getTipoConta());
		DCConta.append(Conta.CLIENTENOME, conta.getClienteNome());
		collection.insert(DCConta);
	}

	public ArrayList<Conta> retornarContas() {
		ArrayList<Conta> contas = new ArrayList<Conta>();
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			Conta conta = new Conta();
			conta.setSenha("" + dbObject.get(Conta.SENHA));
			conta.setCpf("" + dbObject.get(Conta.CPF));
			conta.setNumeroContaString("" + dbObject.get(Conta.NUMEROCONTA));
			conta.setSaldoString("" + dbObject.get(Conta.SALDO));
			conta.setTipoConta("" + dbObject.get(Conta.TIPOCONTA));
			conta.setClienteNome("" + dbObject.get(Conta.CLIENTENOME));
			
			contas.add(conta);
		}
		
		return contas; /* Retorna array list de contas */
	}

	public void atualizarConta(Conta contaVelha, Conta contaNova) {
		BasicDBObject DCContaOld = new BasicDBObject();

		DCContaOld.put(Conta.SALDO, contaVelha.getSaldo());
		DCContaOld.put(Conta.CPF, contaVelha.getCpf());
		DCContaOld.put(Conta.SENHA, contaVelha.getSaldo());
		DCContaOld.put(Conta.TIPOCONTA, contaVelha.getSaldo());
		DCContaOld.put(Conta.NUMEROCONTA, contaVelha.getSenha());

		BasicDBObject DCContaNew = new BasicDBObject();

		DCContaNew.put(Conta.SALDO, contaNova.getSaldo());
		DCContaNew.put(Conta.CPF, contaNova.getCpf());
		DCContaNew.put(Conta.SENHA, contaNova.getSenha());
		DCContaNew.put(Conta.TIPOCONTA, contaNova.getSaldo());
		DCContaNew.put(Conta.NUMEROCONTA, contaNova.getSenha());

		collection.findAndModify(DCContaOld, DCContaNew); /* Tenta substituir os valores de um dbobject, mas falha */
	}
	
	public void removerConta(Conta conta) { /* Apaga conta */
	/*	if (!(collection == null)) {
			
			collection = BaseDados.createCollection("Contas", new BasicDBObject());
		}*/
		DCConta.append(Conta.CPF, conta.getCpf());
		DCConta.append(Conta.NUMEROCONTA, conta.getNumeroConta());
		DCConta.append(Conta.SALDO, conta.getSaldo());
		DCConta.append(Conta.SENHA, conta.getSenha());
		DCConta.append(Conta.TIPOCONTA, conta.getTipoConta());
		DCConta.append(Conta.CLIENTENOME, conta.getClienteNome());
		collection.remove(DCConta);
	}
}
