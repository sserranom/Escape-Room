package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomInventoryDto;

public class EscapeRoomPrinter {

    static void print(EscapeRoomDTO escapeRoomDTO) {
        System.out.printf("Id: %d   |  Name:  %s   |  Url:  %s %n", escapeRoomDTO.id(), escapeRoomDTO.name(), escapeRoomDTO.url());
    }
    static void printInvetory(EscapeRoomInventoryDto escapeRoomInventoryDto) {
        System.out.println("Escape Room Inventory:");
        System.out.println("Rooms:");
        escapeRoomInventoryDto.rooms().forEach(room -> System.out.printf("Room Id: %d   |  Name:  %s %n", room.id(), room.name()));
        System.out.println("Decos:");
        escapeRoomInventoryDto.decos().forEach(deco -> System.out.printf("Deco Id: %d   |  Name:  %s %n", deco.id(), deco.name()));
        System.out.println("Puzzles:");
        escapeRoomInventoryDto.puzzles().forEach(puzzle -> System.out.printf("Puzzle Id: %d   |  Name:  %s %n", puzzle.id(), puzzle.name()));
    }
}
