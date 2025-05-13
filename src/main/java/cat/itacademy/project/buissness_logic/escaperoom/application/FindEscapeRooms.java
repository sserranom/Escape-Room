package cat.itacademy.project.buissness_logic.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

import java.util.List;
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
}
