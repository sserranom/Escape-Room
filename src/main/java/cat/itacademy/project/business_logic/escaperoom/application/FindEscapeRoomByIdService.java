package cat.itacademy.project.business_logic.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;

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
        return repo.findById(idToFind);

    }
}
