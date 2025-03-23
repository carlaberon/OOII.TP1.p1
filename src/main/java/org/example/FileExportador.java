package unrn.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileExportador implements Exportador {
    private final File aFile;

    public FileExportador(String filePath) {
        this.aFile = new File(filePath);
    }

    public void export(String data) {
        try(FileWriter writer = new FileWriter(aFile)){
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
