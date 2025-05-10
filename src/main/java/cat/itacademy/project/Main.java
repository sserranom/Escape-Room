package cat.itacademy.project;


import cat.itacademy.project.escaperoom.domain.CreateEscapeRoomDTO;
import cat.itacademy.project.escaperoom.application.CreateEscapeRoom;
import cat.itacademy.project.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.menu.escaperoom.CreateController;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

public class Main {
    public static void main(String[] args) {

        // menu:
        /*
        al cargar revisa si existe un escaperoom y lo carga
        si no existe  entra al menu de crear esscaperoom

         */

        CreateController controller = new CreateController();
        controller.execute();
        /*
         una vez el escape esta cargado tienes opciones:
         editar escaperoom
         ver rooms
         ver packs
         etc

         */

    }
}
