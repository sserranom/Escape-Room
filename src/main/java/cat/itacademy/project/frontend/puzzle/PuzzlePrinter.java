package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;

public class PuzzlePrinter {

    static void print(PuzzleDTO puzzle) {
        System.out.printf("Id: %d   |  Name:  %s   |  Theme:  %d   |  Room:  %d    |  Answer:  %s    |  Story:  %s   |  Price:  %,.2f   ",
                puzzle.id(), puzzle.name(), puzzle.themeId(), puzzle.roomId(), puzzle.answer(), puzzle.story(), puzzle.price());

    }
}

