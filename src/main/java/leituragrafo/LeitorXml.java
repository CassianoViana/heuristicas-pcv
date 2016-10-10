/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leituragrafo;

/*
 * Informe abaixo o pacote em que este arquivo está 
 * A informação de pacote deve ser a primeira do arquivo 
 * Obs.:
 *  Se importou o arquivo por uma IDE (Netbeans, Eclipse,...) A informação de
 *  pacote pode já estar presente, neste caso ignore esta mensagem. 
 */
//package ???;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import modelo.Mapa;
import modelo.Cidade;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * Use este código para criar seu grafo a partir do xml gerado na aba anterior
 * Passos: 
 *  1 - Salve o xml em um arquivo 
 *  2 - Exporte este código para seu projeto 
 *  3 - Altere o método grafoFromXML incluindo uma referência à 
 *      classe do seu grafo, conforme exemplificado no código.
 *      Apenas as linhas comentadas precisam ser alteradas.
 *  4 - Execute este código conforme demonstrado no método main, logo abaixo
 *  5 - Uma janela de seleção de arquivo abrirá, selecione o arquivo xml salvo no passo 1
 */
public class LeitorXml {

    //Exemplo de uso
    public static void main(String[] args) {
        LoaderArquivo chooserArquivo =  new ChooserArquivo();
        LeitorXml leitorXmlImpl = new LeitorXml(chooserArquivo);
        leitorXmlImpl.grafoFromXML();
    }

    LoaderArquivo capturador;
    public LeitorXml(LoaderArquivo capturadorArquivo) {
        this.capturador = capturadorArquivo;
    }
    
    public Mapa grafoFromXML() {

        Mapa mapa = new Mapa();

        File file = capturador.openFile();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList grafoXml = doc.getElementsByTagName("Mapa");

            for (int i = 0; i < grafoXml.getLength(); i++) {
                Node node = grafoXml.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    boolean ponderado = Boolean.parseBoolean(element.getAttribute("ponderado"));
                    boolean dirigido = Boolean.parseBoolean(element.getAttribute("dirigido"));
                    mapa.setPonderado(ponderado);
                    mapa.setDirigido(dirigido);
                }
            }

            NodeList nodes = doc.getElementsByTagName("Vertice");
            if (nodes.getLength() == 0) {
                System.out.println("Você não está importando o xml correto. \n"
                        + "O xml esperado é aquele gerado a partir do botão 'Scripts' (na janela principal do programa). \n"
                        + "- Passos: \n"
                        + "1) clique sobre o botão 'Scripts' na tela principal do GraphMax\n"
                        + "2) Acesse a aba 'XML' na janela que foi aberta\n"
                        + "3) Clique no botão 'Exportar'");
            } else {
                System.out.println("-Vertices");
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        int relId = Integer.parseInt(element.getAttribute("relId"));
                        String rotulo = element.getAttribute("rotulo");
                        int posX = Integer.parseInt(element.getAttribute("posX"));
                        int posY = Integer.parseInt(element.getAttribute("posY"));

                        mapa.adicionarVertice(new Cidade(rotulo, relId));

                        System.out.println("relId: " + relId);
                        System.out.println("rotulo: " + rotulo);
                        System.out.println("posicao: " + posX + ", " + posY);
                    }
                    System.out.println();
                }
                nodes = doc.getElementsByTagName("Aresta");
                System.out.println("-Arestas");
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        int idVertice1 = Integer.parseInt(element.getAttribute("idVertice1"));
                        int idVertice2 = Integer.parseInt(element.getAttribute("idVertice2"));
                        double peso = Double.parseDouble(element.getAttribute("peso"));

                        mapa.adicionarAresta(idVertice1, idVertice2, peso);

                        System.out.println("idVertice1: " + idVertice1);
                        System.out.println("idVertice2: " + idVertice2);
                        System.out.println("peso: " + peso);
                    }
                    System.out.println();
                }
                return mapa;
            }
        } catch (ParserConfigurationException | SAXException | IOException | NumberFormatException ex) {
            System.err.println(ex);

        }
        throw new RuntimeException("Falha ao ler grafo! Nada retornado!");
    }

	public static Mapa ler(LoaderArquivo loaderArquivo) {
		return new LeitorXml(loaderArquivo).grafoFromXML();		
	}

}
