package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.room.UpdateRoomController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.UpdateRoomDTO;

import java.util.Optional;

public class UpdateRoomMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            UpdateRoomDTO dto = getInfo();
            UpdateRoomController controller = new UpdateRoomController(dto);
            controller.execute();
            info("Room with name '" + dto.nameToUpdate() + "' updated successfully.");

        }catch (IllegalArgumentException e){
            error("Error: " + e.getMessage());
        }catch (Exception e){
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }

    private UpdateRoomDTO getInfo(){
        String nameToUpdate = MenuScanner.readString("Enter the name of the room to update: ");
        String newName = MenuScanner.readString("Enter the new name: ");
        double newPrice = MenuScanner.readDouble("Enter the new Price: ");
        int newEscapeRoomId = MenuScanner.readInt("Enter the new ID of the Escape Room: ");

        return new UpdateRoomDTO(nameToUpdate, newName.isEmpty() ? null : newName, newPrice <= 0 ? null : newPrice, newEscapeRoomId <= 0 ? null : newEscapeRoomId);
    }
}
