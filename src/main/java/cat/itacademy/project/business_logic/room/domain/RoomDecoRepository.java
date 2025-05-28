package cat.itacademy.project.business_logic.room.domain;

import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.dtos.room.AssignDecoToRoomDto;

import java.util.List;

public interface RoomDecoRepository {
    void assignDecoToRoom(AssignDecoToRoomDto request);

    void removeDecoFromRoom(int roomId, int decoId);

    List<DecoDTO> findDecosByRoomId(int roomId);
}
