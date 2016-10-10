package algoritmos;

import static org.junit.Assert.*;

import org.junit.Test;

import leituragrafo.CapturadorArquivoTeste;
import leituragrafo.LeitorXml;
import modelo.Mapa;

public class PCVMenorEncargoTest {

	@Test
	public void test() {
		
		String filename = "/home/cassiano/Dropbox/Univali/6/Grafos/AlgoritmosGrafos/test/algoritmos/caixeiro2.xml";
	    Mapa mapa = new LeitorXml(new CapturadorArquivoTeste(filename)).grafoFromXML();
		PCVMenorEncargo caixeiroViajante = new PCVMenorEncargo(mapa);
		
		caixeiroViajante.executar();
		assertEquals("[A, E, B, D, F, C, A]", caixeiroViajante.getCaminho());
		assertEquals(12.0, caixeiroViajante.getCusto(), 0);
		
	}

}
