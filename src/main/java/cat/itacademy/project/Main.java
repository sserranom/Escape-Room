package cat.itacademy.project;

import cat.itacademy.project.api.App;
import cat.itacademy.project.frontend.Menu;

public class Main {
    public static void main(String[] args) {
        App.start();
        Menu menu = new Menu();
        menu.execute();
    }

}
