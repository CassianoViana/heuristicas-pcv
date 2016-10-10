package util;

public class ContadorOperacoes {

	int operacoes;
	
	public ContadorOperacoes() {
		zerar(); 
	}

	public void zerar() {
		operacoes = 0;
	}
	
	public void contar(){
		contar(1);
	}

	public void contar(int i) {
		operacoes += i;
	}
	
	public int getOperacoes() {
		return operacoes;
	}
}
