package cat.itacademy.project.buissness_logic.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.UpdateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateEscapeRoomService implements Command<EscapeRoomDTO> {
    private final UpdateEscapeRoomDTO request;
    private final EscapeRoomRepository repo;


    public UpdateEscapeRoomService(UpdateEscapeRoomDTO request, EscapeRoomRepository repo) {
        this.request = request;
        this.repo = repo;
    }

    public Optional<EscapeRoomDTO> execute() {
        if (request.name() == null || request.name().isBlank()) {
            throw new EmptyFieldException("Field 'name' cannot be empty.");
        }


        Optional<EscapeRoom> existingOptional = repo.findByName(request.nameToUpdate());

        if (existingOptional.isEmpty()) {
            throw new NotFoundException("Escape room with name '" + request.nameToUpdate() + "' does not exist.");
        }

        EscapeRoom escapeRoomToUpdate = existingOptional.get();
        EscapeRoom updatedEscapeRoom = escapeRoomToUpdate;


        if (!request.name().equals(escapeRoomToUpdate.getName()) || !request.name().isBlank()) {
            Optional<EscapeRoom> existingWithNewName = repo.findByName(request.name());
            if (existingWithNewName.isPresent() && !Integer.valueOf(existingWithNewName.get().getId()).equals(escapeRoomToUpdate.getId())) {
                throw new AlreadyExistsException("Escape room with name '" + request.name() + "' already exists.");
            }
            updatedEscapeRoom = updatedEscapeRoom.createNewInstanceWithName(request.name());
        }

        if (!request.url().equals(escapeRoomToUpdate.getUrl()) || !request.url().isBlank()) {
            updatedEscapeRoom = updatedEscapeRoom.createNewInstanceWithUrl(request.url());
        }

            repo.update(updatedEscapeRoom);
        return Optional.of(updatedEscapeRoom.toDTO());

    }
}
