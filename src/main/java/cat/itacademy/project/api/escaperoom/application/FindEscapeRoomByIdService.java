package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class FindEscapeRoomByIdService extends MenuCommand<EscapeRoomDTO> {
    private final EscapeRoomRepository repo;
    private final int idToFind;

    public FindEscapeRoomByIdService(int idToFind, EscapeRoomRepository repo) {
        this.repo = repo;
        this.idToFind = idToFind;
    }

    @Override
    public Optional<EscapeRoomDTO> execute() {
        Optional<EscapeRoom> escapeRoomOptional = repo.findById(idToFind);
        return escapeRoomOptional.map(escapeRoom -> new EscapeRoomDTO(
                escapeRoom.getId(),
                escapeRoom.getName(),
                escapeRoom.getUrl()
        ));
    }
}
