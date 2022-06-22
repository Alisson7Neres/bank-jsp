package com.bank.model;

public class Bank implements Tipos{
	
	private String nome;
	private String agencia;
	private String numeroConta;
	private Double saldo;
	
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
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
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
