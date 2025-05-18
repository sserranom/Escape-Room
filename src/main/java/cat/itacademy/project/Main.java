package cat.itacademy.project;

import cat.itacademy.project.frontend.Room.CreateRoomMenu;
import cat.itacademy.project.frontend.escaperoom.*;
import cat.itacademy.project.frontend.theme.FindThemeMenu;

public class Main {
    public static void main(String[] args) {

        // menu:
        /*
        al cargar revisa si existe un escaperoom y lo carga
        si no existe  entra al menu de crear esscaperoom

         */

//        CreateEscapeRoomMenu menu = new CreateEscapeRoomMenu();
//        menu.execute();
////
//        FindEscapeRoomsMenu findMenu = new FindEscapeRoomsMenu();
//        findMenu.execute();
//
//
//
//        CreateThemeMenu createThemeMenu = new CreateThemeMenu();
//        createThemeMenu.execute();
//
//        UpdateEscapeRoomMenu updateMenu = new UpdateEscapeRoomMenu();
//        updateMenu.execute();
//
//        FindEscapeRoomByIdMenu findByIdMenu = new FindEscapeRoomByIdMenu();
//        findByIdMenu.execute();
//
//        DeleteEscapeRoomMenu deleteRomm = new DeleteEscapeRoomMenu();
//        deleteRomm.execute();

//        FindAllEscapeRoomsMenu findEscapeRoomsMenu = new FindAllEscapeRoomsMenu();
//        findEscapeRoomsMenu.execute();
//
//        FindThemeMenu findThemeMenu = new FindThemeMenu();
//        findThemeMenu.execute();

        CreateRoomMenu createRoomMenu = new CreateRoomMenu();
        createRoomMenu.execute();
     
    }
}
