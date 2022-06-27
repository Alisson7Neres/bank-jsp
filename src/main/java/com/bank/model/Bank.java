package com.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Bank implements Tipos {

	private String nome;
	private String agencia;

	private long numeroConta;
	private Double saldo;
	private  enum tipo{
		CONTACORRENTE,
		POUPANCA;
	};

	private List<Cliente> clientes = new ArrayList<Cliente>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(long numeroConta) {
		this.numeroConta = numeroConta = Random();
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	private long Random() {
		if (numeroConta < 8) {
			numeroConta = (long) (10000000l + Math.random() * 89999999l);
			System.out.println(numeroConta);
		}

		return numeroConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public void sacar(double valor) {

	}

	@Override
	public void depositar(double valor) {

	}

	@Override
	public void transferir(double valor, Conta conta) {

	}

}
