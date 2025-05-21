package cat.itacademy.project.frontend.deco;

import cat.itacademy.project.api.deco.UpdateDecoController;
import cat.itacademy.project.api.room.UpdateRoomController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.deco.UpdateDecoDTO;
import cat.itacademy.project.shared.domain.dtos.room.UpdateRoomDTO;

import java.util.Optional;

public class UpdateDecoMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            UpdateDecoDTO dto = getInfo();
            UpdateDecoController controller = new UpdateDecoController(dto);
            controller.execute();
            info("Decorative Item with name '" + dto.nameToUpdate() + "' updated successfully.");

        } catch (IllegalArgumentException e) {
            error("Error: " + e.getMessage());
        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }

    private UpdateDecoDTO getInfo() {
        String newType;
        String nameToUpdate = MenuScanner.readString("Enter the name of the decorative item to update: ");
        String newName = MenuScanner.readString("Enter the new name: ");
        String newDescription = MenuScanner.readString("Enter the new description: ");
        int typeObjs = MenuScanner.readInt("Please select Type of de Decorative Item: 1 - furniture, 2 - garnishment");
        switch (typeObjs) {
            case 1 -> newType = "furniture";
            case 2 -> newType = "garnishment";
            default -> newType = null;

        }
        int newEscapeRoomId = MenuScanner.readInt("Enter the new ID of the Escape Room: ");
        double newPrice = MenuScanner.readDouble("Enter the new Price: ");

        return new UpdateDecoDTO(nameToUpdate, newName.isEmpty() ? null : newName, newDescription, newType, newEscapeRoomId <= 0 ? null : newEscapeRoomId,  newPrice <= 0 ? null : newPrice );
    }
}
