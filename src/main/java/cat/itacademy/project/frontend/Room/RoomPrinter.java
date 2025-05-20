package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

public class RoomPrinter {

    static void print(RoomDTO room) {
        System.out.printf("Id: %d   |  Name:  %s   |  Price:  %,.2f   |  Difficulty:  %s   |  Theme:  %d %n",
                room.id(), room.name(), room.price(), room.difficulty(), room.themeId());

    }
}
