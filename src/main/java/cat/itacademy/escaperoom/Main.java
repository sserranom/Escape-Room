package cat.itacademy.escaperoom;


import cat.itacademy.escaperoom.escaperoom.EscapeRoom;
import cat.itacademy.escaperoom.escaperoom.CreateScapeRoom;
import cat.itacademy.escaperoom.escaperoom.EscapeRoomRepository;

public class Main {
    public static void main(String[] args) {

        // menu:
        /*
        al cargar revisa si existe un escaperoom y lo carga
        si no existe  entra al menu de crear esscaperoom

         */

        EscapeRoomRepository repo = new EscapeRoomRepository();
        EscapeRoom newroom = EscapeRoom.create("Escape Room 2", "http://example.com");


        CreateScapeRoom creator = new CreateScapeRoom(newroom, repo);
        creator.create();
        /*
         una vez el escape esta cargado tienes opciones:
         editar escaperoom
         ver rooms
         ver packs
         etc

         */

    }
}
