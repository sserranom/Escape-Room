package cat.itacademy.project.business_logic.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

import java.util.Optional;

public class FindEscapeRoomByIdService implements Command<EscapeRoomDTO> {
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
