package cat.itacademy.project.shared.domain.dtos.puzzle;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record UpdatePuzzleDTO (String nameToUpdate, String name, String difficulty, int roomId, String answer, String story, int themeId, double price)  {
    public UpdatePuzzleDTO {

        if (nameToUpdate == null || nameToUpdate.isBlank()){
            throw new EmptyFieldException("The name of the puzzle to update must be provided.");
        }

        if((name == null || name.isBlank())){
            throw new EmptyFieldException("The name of the puzzle must be provided.");
        }


    }
}
