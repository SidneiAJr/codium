public class ClienteService {
    private EmailService emailService;

    public ClienteService() {
        this.emailService = new EmailService();
    }

    public void notificarCliente(String mensagem) {
        emailService.enviarEmail(mensagem);
    }
}
