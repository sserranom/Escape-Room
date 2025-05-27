package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.events.EventListener;

public class PuzzlePublishedEventListener implements EventListener {
    @Override
    public void update(String topic, DTO data) {
        PuzzleDTO puzzleDTO = (PuzzleDTO) data;
        System.out.println("Puzzle published: " + puzzleDTO);

    }
}
