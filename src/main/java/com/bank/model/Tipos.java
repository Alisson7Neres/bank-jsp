package com.bank.model;

public interface Tipos {
	
	void sacar(double valor);
	void depositar(double valor);
	void transferir(double valor, Conta conta);

}
