package cat.itacademy.project;


import cat.itacademy.project.frontend.escaperoom.CreateEscapeRoomMenu;
import cat.itacademy.project.frontend.escaperoom.FindEscapeRoomsMenu;

public class Main {
    public static void main(String[] args) {

        // menu:
        /*
        al cargar revisa si existe un escaperoom y lo carga
        si no existe  entra al menu de crear esscaperoom

         */

        CreateEscapeRoomMenu menu = new CreateEscapeRoomMenu();
        menu.execute();

        FindEscapeRoomsMenu findMenu = new FindEscapeRoomsMenu();
        findMenu.execute();
        /*
         una vez el escape esta cargado tienes opciones:
         editar escaperoom
         ver rooms
         ver packs
         etc

         */

    }
}
