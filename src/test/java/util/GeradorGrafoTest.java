package util;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Mapa;

public class GeradorGrafoTest {

	@Test
	public void test() {
		
		GeradorGrafo geradorMapa = new GeradorGrafo();
		geradorMapa.setNrCidades(3, 10);
		geradorMapa.setDistancias(5, 50);
		Mapa mapa = geradorMapa.gerar();
		
		assertNotNull(mapa);
	}

}
