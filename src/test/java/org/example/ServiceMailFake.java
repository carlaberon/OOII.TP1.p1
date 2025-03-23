package org.example;

public class ServiceMailFake implements IMailService {
    private String mensaje;

    @Override
    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
        this.mensaje = destinatario + " - " + asunto + ": " + mensaje;
    }

    public String mensaje() {
        return this.mensaje;
    }
}
