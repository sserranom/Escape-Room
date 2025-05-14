package cat.itacademy.project.buissness_logic.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class FindEscapeRooms {
    private final EscapeRoomRepository repo;

    public FindEscapeRooms(EscapeRoomRepository repo) {
        this.repo = repo;
    }

    public List<EscapeRoomDTO> findAll() {
        List<EscapeRoom> escapeRooms = repo.findAll();
        return escapeRooms.stream()
                .map(room -> new EscapeRoomDTO(
                        room.getId(),
                        room.getName(),
                        room.getUrl()
                ))
                .collect(Collectors.toList());
    }

    public List<EscapeRoom> findAllRaw(){
        return  repo.findAll();
    }

    public Optional<EscapeRoomDTO> findById(int id) throws NotFoundException {
        Optional<EscapeRoom> escapeRoomOptional = repo.findById(id);
        if (escapeRoomOptional.isPresent()) {
            EscapeRoom room = escapeRoomOptional.get();
            return Optional.of(new EscapeRoomDTO(
                    room.getId(),
                    room.getName(),
                    room.getUrl()
            ));
        } else {
            throw new NotFoundException("Escape room with ID " + id + " not found.");
        }
    }
}
