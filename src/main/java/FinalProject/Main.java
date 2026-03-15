package FinalProject;
import FinalProject.Domain.Pet;
import FinalProject.GUI.MainFrame;
import FinalProject.Repository.PetRepository;
import FinalProject.Repository.PetRepositoryImpl;
import FinalProject.Server.NotificationClient;
import FinalProject.Server.NotificationServer;
import FinalProject.Service.PetService;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        NotificationServer notificationServer = new NotificationServer(5000);
        notificationServer.start();

        PetRepository<Pet, Long> repository = new PetRepositoryImpl();
        PetService service = new PetService(repository, notificationServer);

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(service);

            NotificationClient client = new NotificationClient("localhost", 5000, frame::appendLog);
            client.start();
        });
    }
}