package br.com.poo.sysfi.conexaoMongo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import br.com.poo.sysfi.model.Banco;
import br.com.poo.sysfi.model.Unidade;

public class ConnectionUnidade extends Connection {
	

	@SuppressWarnings("deprecation")
	public ConnectionUnidade() { 
		try {
			@SuppressWarnings("resource")
			MongoClient mongo = new MongoClient("localhost", 27017);
			BaseDados = mongo.getDB("BancoSid");
			collection = BaseDados.getCollection("Unidades");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Falha na conexão");

		}
	}

	public void inserirUnidade(Banco unidade) {/* Insere unidade */
		DBCollection collection = BaseDados.getCollection("Unidades");
		if (collection == null) {
			JOptionPane.showMessageDialog(null, "teste 1");
			collection = BaseDados.createCollection("Unidades", new BasicDBObject());
		}
		DCUnidade.append(Unidade.NOMEUNIDADE, unidade.getNomeUnidade());
		DCUnidade.append(Unidade.SENHA, unidade.getSenha());
		collection.insert(DCUnidade);
	}

	public ArrayList<Banco> retornarUnidades() {
		ArrayList<Banco> unidades = new ArrayList<Banco>();
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			Banco unidade = new Unidade();
			unidade.setSenha("" + dbObject.get(Unidade.SENHA));
			unidade.setNomeUnidade("" + dbObject.get(Unidade.NOMEUNIDADE));

			unidades.add(unidade);
		}
		return unidades; /* Retorna array list de unidades */
	}

	public void atualizarUnidade(Banco unidadeVelha, Banco unidadeNova) {
		BasicDBObject DCUnidadeOld = new BasicDBObject();

		DCUnidadeOld.put(Unidade.SENHA, unidadeVelha.getNomeUnidade());
		DCUnidadeOld.put(Unidade.NOMEUNIDADE, unidadeVelha.getSenha());

		BasicDBObject DCUnidadeNew = new BasicDBObject();

		DCUnidadeNew.put(Unidade.SENHA, unidadeNova.getNomeUnidade());
		DCUnidadeNew.put(Unidade.NOMEUNIDADE, unidadeNova.getSenha());

		collection.findAndModify(DCUnidadeOld, DCUnidadeNew); /* Tenta atualizar os dados de um obj, mas falha */
	}
	
	public void removerUnidade(Banco unidade) {/* Remove uma unidade */
		DCUnidade.append(Unidade.NOMEUNIDADE, unidade.getNomeUnidade());
		DCUnidade.append(Unidade.SENHA, unidade.getSenha());
		collection.remove(DCUnidade);
	}

}
