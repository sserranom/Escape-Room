package cat.itacademy.project.frontend.deco;

import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;

public class DecoPrinter {
    static void print(DecoDTO deco) {
        System.out.printf("Id: %d   |  Name:  %s   |  Description:  %s   |  Type:  %s  |   EscapeRoomId:  %s  |  Price:  %,.2f %n",
                deco.id(), deco.name(), deco.description(), deco.type(), deco.escapeRoomId(),  deco.price());

    }
}
