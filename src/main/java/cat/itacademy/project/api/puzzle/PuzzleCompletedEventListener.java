package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.events.EventListener;

public class PuzzleCompletedEventListener implements EventListener {
    @Override
    public void update(String topic, DTO dto) {
        PuzzleDTO puzzleDTO = (PuzzleDTO) dto;
        System.out.println("Sending reward: " + puzzleDTO);
    }
}
