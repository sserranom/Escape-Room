package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

public class RoomPrinter {

    static void print(RoomDTO roomDTO) {
        System.out.printf("Id: %d   |  Name:  %s   |  Price:  %,.2f   |  Difficulty:  %s   |  Theme:  %s %n",
                roomDTO.id(), roomDTO.name(), roomDTO.price(), roomDTO.difficulty(), roomDTO.themeName());

    }
}
