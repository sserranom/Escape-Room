package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

public  class EscapeRoomPrinter {

    static void print(EscapeRoomDTO escapeRoomDTO) {
        System.out.printf("Id: %d   |  Name:  %s   |  Url:  %s %n", escapeRoomDTO.id(), escapeRoomDTO.name(), escapeRoomDTO.url());
    }
}
