package model;

public class Testes {

	public static void main(String[] args) {	
	Aviso avisos = new Aviso();
	//avisos.cadastrarAviso("novo aviso!", "00000000000", "mensagem de teste do aviso", "45893797833");
	//n�o se esque�a que tanto o user de destino quanto o de origem devem existir!!!!!!!!!!!!!
	
	//avisos.cadastrarAviso("00000000000", "Testando o sistema de avisos", "01234567890");
	
	avisos.cadastrarAviso("01234567890", "00000000000", "Testando o sistema de avisos");
	}

}
