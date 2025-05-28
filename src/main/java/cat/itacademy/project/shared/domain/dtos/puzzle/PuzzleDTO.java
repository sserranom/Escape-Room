package cat.itacademy.project.shared.domain.dtos.puzzle;

import cat.itacademy.project.shared.domain.dtos.DTO;


public record PuzzleDTO(int id, String name,  int roomId, String answer, String story, int themeId,
                        double price) implements DTO {




}




