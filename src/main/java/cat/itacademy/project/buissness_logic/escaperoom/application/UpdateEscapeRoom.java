package cat.itacademy.project.buissness_logic.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.UpdateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateEscapeRoom {
    private final UpdateEscapeRoomDTO request;
    private final EscapeRoomRepository repo;


    public UpdateEscapeRoom(UpdateEscapeRoomDTO request, EscapeRoomRepository repo) {
        this.request = request;
        this.repo = repo;
    }

    public void updateByName() throws AlreadyExistsException {
        Optional<EscapeRoom> existingOptional = repo.findByName(request.nameToUpdate());

        EscapeRoom escapeRoomToUpdate = existingOptional.get();
        EscapeRoom updatedEscapeRoom = escapeRoomToUpdate;
        boolean nameUpdated = false;

        if (request.name() != null && !request.name().isBlank() && !request.name().equals(escapeRoomToUpdate.getName())) {
            Optional<EscapeRoom> existingWithNewName = repo.findByName(request.name());
            if (existingWithNewName.isPresent() && !Integer.valueOf(existingWithNewName.get().getId()).equals(escapeRoomToUpdate.getId())){
                throw new AlreadyExistsException("Escape room with name '" + request.name() + "' already exists.");
            }
            updatedEscapeRoom = updatedEscapeRoom.withName(request.name());
            nameUpdated = true;
        }

        boolean urlUpdated = false;
        if (request.url() != null && !request.url().isBlank() && !request.url().equals(escapeRoomToUpdate.getUrl())) {
            updatedEscapeRoom = updatedEscapeRoom.withUrl(request.url());
            urlUpdated = true;
        }

        if (nameUpdated || urlUpdated) {
            repo.update(updatedEscapeRoom);
        }

    }
}
