package ejerciciosTP1;

public class ServiceMailFake implements IMailService {
    private String mail;

    @Override
    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
        this.mail = destinatario + " - " + asunto + ": " + mensaje;
    }

    public String mail() {
        return this.mail;
    }

    public boolean noSeEnvioElCorreo() {
        return this.mail == null;
    }
}
