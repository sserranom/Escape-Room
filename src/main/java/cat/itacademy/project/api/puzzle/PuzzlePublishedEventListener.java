package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.api.notification.NotificationController;
import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.events.EventListener;

public class PuzzlePublishedEventListener implements EventListener {
    NotificationController controller = new NotificationController();
    @Override
    public void update(String topic, DTO data) {
        PuzzleDTO puzzleDTO = (PuzzleDTO) data;
        controller.send(
               puzzleDTO
        );
        System.out.println("Puzzle published: " + puzzleDTO);
    }


}
