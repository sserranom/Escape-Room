package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.RoomDTO;
import cat.itacademy.project.shared.domain.dtos.UpdateRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.rmi.AlreadyBoundException;
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
        if (request.name() == null || request.name().isBlank()){
            throw new EmptyFieldException("Field 'name' cannot be empty.");
        }

        Optional<Room> existingOptional = repo.findByName(request.nameToUpdate());

        if (existingOptional.isEmpty()){
            throw new NotFoundException("Room with name '" + request.nameToUpdate() + "' does not exist.");
        }

        Room roomToUpdate = existingOptional.get();
        Room updatedRoom = roomToUpdate;

        if (!request.name().equals(roomToUpdate.getName()) || !request.name().isBlank()) {
            Optional<Room> existingWithNewName = repo.findByName((request.name()));
            if (existingWithNewName.isPresent() && !Integer.valueOf(existingWithNewName.get().getId()).equals(roomToUpdate.getId())){
                throw new AlreadyExistsException("Room with name '" + request.name() + "' already exist.");
            }
            updatedRoom = updatedRoom.createNewInstanceWithName(request.name());
        }

        if (request.price() > 0 && request.price() != roomToUpdate.getPrice()) {
            updatedRoom = updatedRoom.createNewInstanceWithPrice(request.price());
        }

        if (request.escapeRoomId() > 0 && request.escapeRoomId() != roomToUpdate.getEscapeRoomId()) {
            updatedRoom = updatedRoom.createNewInstanceWithEscapeRoomId(request.escapeRoomId());
        }

        repo.update(updatedRoom);
        return Optional.of(updatedRoom.toDTO());

    }
}
