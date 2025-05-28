package cat.itacademy.project.frontend.escaperoom;


import cat.itacademy.project.frontend.Customer.ManageCustomerMenu;
import cat.itacademy.project.frontend.Menu;
import cat.itacademy.project.frontend.reservation.ManageReservationMenu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.frontend.theme.ManageThemeMenu;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

import java.util.Optional;

public class ManageEscapeRoomMenu extends MenuCommand<EscapeRoomDTO> {
    private EscapeRoomDTO escapeRoomDTO;


    public ManageEscapeRoomMenu(EscapeRoomDTO escapeRoomDTO) {
        this.escapeRoomDTO = escapeRoomDTO;
    }


    @Override
    public Optional<EscapeRoomDTO> execute() {
        boolean isRunning = true;
        while (isRunning) {
            int choice = getUserInput();
            try {
                switch (choice) {
                    case 1:
                        log("Escape room details:");
                        EscapeRoomPrinter.print(escapeRoomDTO);
                        break;

                    case 2:
                        log("Update escape room details:");
                        UpdateEscapeRoomMenu updateEscapeRoomMenu = new UpdateEscapeRoomMenu(escapeRoomDTO);
                        Optional<EscapeRoomDTO> updated = updateEscapeRoomMenu.execute();
                        updated.ifPresent(dto -> {
                            this.escapeRoomDTO = dto;
                            Menu.setActiveRoom(dto);
                            Menu.updateExistingRooms(dto);
                        });
                        break;
                    case 3:
                        log("Manage Themes: ");
                        ManageThemeMenu manageThemeMenu = new ManageThemeMenu();
                        manageThemeMenu.execute();
                        break;
                    case 4:
                        log("Manage Reservation: ");
                        ManageReservationMenu manageReservationMenu = new ManageReservationMenu();
                        manageReservationMenu.execute();
                        break;
                    case 5:
                        log("Show inventory:");
                        ListEscapeRoomInventoryMenu listEscapeRoomInventoryMenu = new ListEscapeRoomInventoryMenu();
                        listEscapeRoomInventoryMenu.execute();
                        break;

                    case 6:
                        log("Delete escape room:");
                        DeleteEscapeRoomMenu deleteEscapeRoomMenu = new DeleteEscapeRoomMenu();
                        deleteEscapeRoomMenu.execute();
                        break;

                    case 7:
                        log("Manage Customers: ");
                        ManageCustomerMenu manageCustomerMenu = new ManageCustomerMenu();
                        manageCustomerMenu.execute();
                        break;


                    case 0:
                        log("Exiting to main menu.");
                        Menu.setActiveRoom(null);
                        isRunning = false;
                        break;
                    default:
                        error("Invalid choice. Please try again.");
                        break;
                }
            } catch (CustomException e) {
                error(e.getMessage());
            }
        }

        return Optional.of(escapeRoomDTO);
    }

    private int getUserInput() {
        log("1. View escape room details");
        log("2. Update escape room details");
        log("3. Manage Themes");
        log("4. Manage reservations");
        log("5. Show inventory");
        log("6. Delete escape room");
        log("7. Manage Customers");
        log("0. Exit");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}
