package cat.itacademy.project.shared.domain.dtos;

public record UpdateEscapeRoomDTO (String nameToUpdate, String name, String url) {

    public UpdateEscapeRoomDTO {

        if (nameToUpdate == null || nameToUpdate.isBlank()){
            throw new IllegalArgumentException("The name of the escape room to update must be provided.");
        }
        if((name == null || name.isBlank()) & (url == null || url.isBlank())){
            throw new IllegalArgumentException("At least one of name or URL must be provided for update.");
        }
    }

}
