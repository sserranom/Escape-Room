package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.application.UpdateEscapeRoom;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.UpdateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateEscapeRoomService extends MenuCommand<Void> {
    private final EscapeRoomRepository repo;
    private final UpdateEscapeRoomDTO updateEscapeRoomDTO;

    public UpdateEscapeRoomService(UpdateEscapeRoomDTO updateEscapeRoomDTO, EscapeRoomRepository repository) {
        this.updateEscapeRoomDTO = updateEscapeRoomDTO;
        this.repo = repository;
    }

    @Override
    public Optional<Void> execute() {

            UpdateEscapeRoom updater = new UpdateEscapeRoom(updateEscapeRoomDTO, repo);
            updater.updateByName();
            info("Escape room update process completed.");
            return Optional.empty();
        }
}

