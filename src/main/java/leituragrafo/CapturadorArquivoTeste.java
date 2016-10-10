package leituragrafo;

import java.io.File;

public class CapturadorArquivoTeste implements LoaderArquivo{
    private final String nomeArquivo;

    public CapturadorArquivoTeste(String testexml) {
        this.nomeArquivo = testexml;
    }

    public File openFile() {
        return new File(nomeArquivo);
    }
}
