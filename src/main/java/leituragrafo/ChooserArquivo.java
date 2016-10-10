package leituragrafo;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooserArquivo implements LoaderArquivo {

    @Override
    public File openFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Arquivo XML", "xml");
        fileChooser.setFileFilter(extensionFilter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file;
        }
        throw new RuntimeException("Arquivo nao lido");
    }
}
