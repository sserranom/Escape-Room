package cat.itacademy.project.business_logic.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;

import java.util.List;


public class FindAllEscapeRoomsService {
    private final EscapeRoomRepository repo;

    public FindAllEscapeRoomsService(EscapeRoomRepository repo) {
        this.repo = repo;
    }

    public List<EscapeRoomDTO> findAll() {
        return repo.findAll();


//        public List<EscapeRoomDTO> findAllRaw () {
//            return repo.findAll();
//        }

//    public Optional<EscapeRoomDTO> findById(int id) throws NotFoundException {
//        Optional<EscapeRoom> escapeRoomOptional = repo.findById(id);
//        if (escapeRoomOptional.isPresent()) {
//            EscapeRoom room = escapeRoomOptional.get();
//            return Optional.of(new EscapeRoomDTO(
//                    room.getId(),
//                    room.getName(),
//                    room.getUrl()
//            ));
//        } else {
//            throw new NotFoundException("Escape room with ID " + id + " not found.");
//        }
//    }
    }
}
