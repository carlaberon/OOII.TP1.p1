package org.example;

public class EnMemoriaExportador implements Exportador {
    private String data;

    @Override
    public void export(String data) {
        this.data = data;
    }

    public String data() {
        return this.data;
    }
}
