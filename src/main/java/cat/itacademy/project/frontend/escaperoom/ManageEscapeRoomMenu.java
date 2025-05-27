package cat.itacademy.project.frontend.escaperoom;


import cat.itacademy.project.frontend.Customer.ManageCustomerMenu;
import cat.itacademy.project.frontend.Menu;
import cat.itacademy.project.frontend.Room.ManageRoomMenu;
import cat.itacademy.project.frontend.deco.ManageDecoMenu;
import cat.itacademy.project.frontend.reservation.ManageReservationMenu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.events.EventManager;

import java.util.List;
import java.util.Optional;

public class ManageEscapeRoomMenu extends MenuCommand<EscapeRoomDTO> {
    private final EscapeRoomDTO escapeRoomDTO;
    private final EventManager eventManager = new EventManager(List.of("puzzle.published", "escape_room.updated"));
    private RoomDTO roomDTO;
    private DecoDTO decoDTO;
    private CustomerDTO customerDTO;


    public ManageEscapeRoomMenu(EscapeRoomDTO escapeRoomDTO) {
        this.escapeRoomDTO = escapeRoomDTO;
    }

    public ManageEscapeRoomMenu(EscapeRoomDTO escapeRoomDTO, RoomDTO roomDTO, DecoDTO decoDTO, CustomerDTO customerDTO) {
        this.escapeRoomDTO = escapeRoomDTO;
        this.roomDTO = roomDTO;
        this.decoDTO = decoDTO;
        this.customerDTO = customerDTO;

    }

    @Override
    public Optional<EscapeRoomDTO> execute() {
        int choice = getUserInput();

        switch (choice) {
            case 1:
                log("Escape room details:");
                EscapeRoomPrinter.print(escapeRoomDTO);
                break;

            case 2:
                log("Update escape room details:");
                UpdateEscapeRoomMenu updateEscapeRoomMenu = new UpdateEscapeRoomMenu(escapeRoomDTO, eventManager);
                Optional<EscapeRoomDTO> updated = updateEscapeRoomMenu.execute();
                updated.ifPresent(escapeRoomDTO -> {
                    Menu.setActiveRoom(escapeRoomDTO);
                    Menu.updateExistingRooms(escapeRoomDTO);
                });
                break;

            case 3:
                log("Manage rooms: ");
                ManageRoomMenu manageRoomMenu = new ManageRoomMenu(roomDTO);
                manageRoomMenu.execute();
                break;

            case 4:
                log("Manage Decorative Items: ");
                ManageDecoMenu manageDecoMenu = new ManageDecoMenu(decoDTO);
                manageDecoMenu.execute();
                break;

            case 10:
                log("Delete escape room:");
                DeleteEscapeRoomMenu deleteEscapeRoomMenu = new DeleteEscapeRoomMenu(escapeRoomDTO);
                deleteEscapeRoomMenu.execute();
                break;

            case 11:
                log("Manage Customers: ");
                ManageCustomerMenu manageCustomerMenu = new ManageCustomerMenu(customerDTO);
                manageCustomerMenu.execute();
                break;

            case 12:
                log("Manage Reservation: ");
                ManageReservationMenu manageReservationMenu = new ManageReservationMenu();
                manageReservationMenu.execute();
            default:
                error("Invalid choice. Please try again.");
                break;
        }
        return Optional.of(escapeRoomDTO);
    }

    private int getUserInput() {
        log("1. View escape room details");
        log("2. Update escape room details");
        log("3. Manage Rooms");
        log("4. Manage Decorative Items");
        log("5. Manage Themes");
        log("6. Manage puzzles");
        log("7. Manage packs");
        log("8. Manage reservations");
        log("9. Show inventory");
        log("10. Delete escape room");
        log("11. Manage Customers");
        log("12. Manage Reservation");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}
