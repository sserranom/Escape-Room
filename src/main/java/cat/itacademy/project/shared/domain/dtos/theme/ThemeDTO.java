package cat.itacademy.project.shared.domain.dtos.theme;

import cat.itacademy.project.shared.domain.dtos.DTO;

public record ThemeDTO(int id, String name, String description, int escapeRoomId) implements DTO {

}
