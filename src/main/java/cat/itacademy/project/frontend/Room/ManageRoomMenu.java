package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.frontend.Menu;
import cat.itacademy.project.frontend.escaperoom.ManageEscapeRoomMenu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

import java.util.Optional;

public class ManageRoomMenu extends MenuCommand<RoomDTO> {
    private final RoomDTO roomDTO;

    public ManageRoomMenu(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    @Override
    public Optional<RoomDTO> execute() {
        int choice = getUserInput();

        switch (choice){
            case 1:
                log ("Create Room: ");
                CreateRoomMenu createRoomMenu = new CreateRoomMenu();
                createRoomMenu.execute();
                break;

            case 2:
                log("Update Room details: ");
                UpdateRoomMenu updateRoomMenu = new UpdateRoomMenu();
                updateRoomMenu.execute();
                break;

            case 3:
                log("Room Details: ");
                FindAllRoomMenu findAllRoomMenu = new FindAllRoomMenu();
                findAllRoomMenu.execute();
                break;

            case 4:
                log("Back: ");
                Menu menu = new Menu();
                menu.execute();
                break;

        }
        return Optional.ofNullable(roomDTO);
    }

    private int getUserInput(){
        log("1. Create new Room ");
        log("2. Update Room details ");
        log("3. View Room details");
        log("4. Back <-");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}
