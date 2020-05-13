package br.com.poo.sysfi.view;

import br.com.poo.sysfi.model.Banco;

public class AppMain {

	public static void main(String[] args) {
		String admin = "admin", senhaMestre = "123"; /* Login de admin, para caso não haja nenhuma unidade criada e para controle total */
		Banco Nubank = new Banco(); //Banco padrão que tem como herdeiros as unidades
		new Login(admin, senhaMestre, Nubank); 

	}

}
