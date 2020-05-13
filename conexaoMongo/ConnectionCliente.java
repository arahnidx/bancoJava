package br.com.poo.sysfi.conexaoMongo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import br.com.poo.sysfi.model.Cliente;

public class ConnectionCliente extends Connection {
	
	@SuppressWarnings("deprecation")
	public ConnectionCliente() { 
		try {
			@SuppressWarnings("resource")
			MongoClient mongo = new MongoClient("localhost", 27017);
			BaseDados = mongo.getDB("BancoSid");
			collection = BaseDados.getCollection("Clientes");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Falha na conexão");

		}
	}

	public void inserirCliente(Cliente cliente) {

		DBCollection collection = BaseDados.getCollection("Clientes");
		if (collection == null) {    //Se não existir uma collection cliente, é criada uma
			collection = BaseDados.createCollection("Cliente", new BasicDBObject());
		}
		DCCliente.append(Cliente.CPF, cliente.getCpf());
		DCCliente.append(Cliente.NOME, cliente.getNome());
		DCCliente.append(Cliente.ENDERECO, cliente.getEndereco());
		DCCliente.append(Cliente.NUMEROCONTA, cliente.getContaNumber());
		collection.insert(DCCliente);
	}

	public ArrayList<Cliente> retornarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			Cliente cliente = new Cliente();
			cliente.setCpf("" + dbObject.get(Cliente.CPF));
			cliente.setNome("" + dbObject.get(Cliente.NOME));
			cliente.setEndereco("" + dbObject.get(Cliente.ENDERECO));
			cliente.setNumeroConta("" + dbObject.get(Cliente.NUMEROCONTA));
			clientes.add(cliente);
		}
		return clientes; /* Retorna array list de clientes */
	}

	public void atualizarCliente(Cliente clienteVelho, Cliente clienteNovo) {
		BasicDBObject DCClienteOld = new BasicDBObject();


		DCClienteOld.append(Cliente.CPF, clienteVelho.getCpf());
		DCClienteOld.append(Cliente.NOME, clienteVelho.getNome());
		DCClienteOld.append(Cliente.ENDERECO, clienteVelho.getEndereco());
		DCClienteOld.append(Cliente.NUMEROCONTA, clienteVelho.getContaNumber());

		BasicDBObject DCClienteNew = new BasicDBObject();

		DCClienteNew.append(Cliente.CPF, clienteNovo.getCpf());
		DCClienteNew.append(Cliente.NOME, clienteNovo.getNome());
		DCClienteNew.append(Cliente.ENDERECO, clienteNovo.getEndereco());
		DCClienteNew.append(Cliente.NUMEROCONTA, clienteNovo.getContaNumber());

		collection.findAndModify(DCClienteOld, DCClienteNew); /* Tenta substituir dados mas não está funcionando */
	}
	
	public void removerCliente(Cliente cliente) {  /* Remove cliente encontrado */
		DCCliente.append(Cliente.CPF, cliente.getCpf());
		DCCliente.append(Cliente.NOME, cliente.getNome());
		DCCliente.append(Cliente.ENDERECO, cliente.getEndereco());
		collection.remove(DCCliente);
	}
}
