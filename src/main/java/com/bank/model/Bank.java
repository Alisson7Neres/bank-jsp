package com.bank.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bank implements Tipos {

	private String id_bank;
	private int agencia;

	private long numeroConta;
	private Double saldo;
	private String tipo;
	private Date data;
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public String getId_bank() {
		return id_bank;
	}
	
	public void setId_bank(String id_bank) {
		this.id_bank = id_bank;
	}

	public int getAgencia() {
		if (agencia != 13) {
			agencia = 13;
		}
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(long numeroConta) {
		this.numeroConta = numeroConta;
		if(numeroConta < 8) {
			this.numeroConta = Random();
		}
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public long Random() {
		if (numeroConta < 8) {
			numeroConta = (long) (10000000l + Math.random() * 89999999l);
			System.out.println(numeroConta);
		}

		return numeroConta;
	}

	public Double getSaldo() {
		if (saldo == null) {
			this.saldo = 0.0;
		}
		return saldo;
	}

	public void setSaldo(Double saldo) {
		if (saldo == null) {
			this.saldo += 0.0;
		}
		this.saldo = saldo;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
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
