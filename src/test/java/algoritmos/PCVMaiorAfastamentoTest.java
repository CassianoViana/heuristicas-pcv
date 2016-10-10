package algoritmos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import leituragrafo.CapturadorArquivoTeste;
import leituragrafo.LeitorXml;
import modelo.Mapa;
import modelo.Rua;

public class PCVMaiorAfastamentoTest {

	private PCVMaiorAfastamento pcv;
	private Mapa mapa;

	@Before
	public void init() {
		mapa = obterGrafo("grafo1.xml");
		pcv = new PCVMaiorAfastamento();
		pcv.setGrafo(mapa);
	}

	@Test
	public void test_get_cidades_maior_distancia() {
		Rua rua = pcv.encontraRuaMaisComprida();
		assertEquals("A", rua.getOrigem().toString());
		assertEquals("E", rua.getDestino().toString());
		assertTrue(44d == rua.getDistancia());
	}

	@Test
	public void test_encontrar_caminho() {
		pcv.procurarMelhorCaminho();

		String caminho = pcv.getCaminho();
		int distancia = pcv.getDistancia();

		assertEquals("[A, C, B, E, D, F, A]", caminho);
		assertEquals(120, distancia);
	}

	private Mapa obterGrafo(String xml) {
		return LeitorXml.ler(new CapturadorArquivoTeste("src/test/java/grafos/" + xml));
	}

}
