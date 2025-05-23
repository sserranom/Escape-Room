package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.dtos.room.UpdateRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateRoomService implements Command<RoomDTO> {
    private final UpdateRoomDTO request;
    private final RoomRepository repo;

    public UpdateRoomService(UpdateRoomDTO request, RoomRepository repo) {
        this.request = request;
        this.repo = repo;
    }

    @Override
    public Optional<RoomDTO> execute() {
        if (request.name() == null || request.name().isBlank()) {
            throw new EmptyFieldException("Field 'name' cannot be empty.");
        }

        Optional<RoomDTO> existingOptional = repo.findByName(request.nameToUpdate());

        if (existingOptional.isEmpty()) {
            throw new NotFoundException("Room with name '" + request.nameToUpdate() + "' does not exist.");
        }

        Room room = Room.fromDatabase(existingOptional.get());

        room.setName(
                request.name().isBlank()? room.getName() : request.name()
        );
        room.setPrice(
                request.price() > 0 && request.price() != room.getPrice()? request.price() : room.getPrice()
        );
        room.setTheme_id(
                room.getTheme_id() != request.themeId() && request.themeId() > 0 ? request.themeId() : room.getTheme_id());

        room.setDifficulty(request.difficulty().isBlank() ? room.getDifficulty() : request.difficulty());

        repo.update(room);
        return Optional.of(room.toDTO());

    }
}
