package cat.itacademy.project.shared.domain.dtos.escape_room;

import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

import java.util.List;

public record EscapeRoomInventoryDto(List<RoomDTO> rooms, List<PuzzleDTO> puzzles, List<DecoDTO> decos) {
}
