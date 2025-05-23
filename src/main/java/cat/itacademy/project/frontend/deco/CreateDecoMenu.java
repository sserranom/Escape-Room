package cat.itacademy.project.frontend.deco;

import cat.itacademy.project.api.deco.CreateDecoController;
import cat.itacademy.project.api.room.CreateRoomController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.deco.CreateDecoDTO;
import cat.itacademy.project.shared.domain.dtos.room.CreateRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.InvalidDificultyException;

import java.util.Optional;

public class CreateDecoMenu extends MenuCommand<Void> {
    private String name;
    private String description;
    private String type;
    private int escapeRoomId;
    private double price;

    @Override
    public Optional<Void> execute() {
        CreateDecoDTO dto = getUserInfo();
        CreateDecoController controller = new CreateDecoController(dto);
        controller.execute();
        info("Decorative Item created successfully.");
        return Optional.empty();
    }

    private CreateDecoDTO getUserInfo() {

        while (name == null || description == null || escapeRoomId <= 0 || price <= 0 ) {
            try {
                name = MenuScanner.readString("Enter the name of the Decorative Item: ");
                description = MenuScanner.readString("Enter the descripcion of the decorative item: ");
                int typeDeco = MenuScanner.readInt("Select the type of the decorative item: 1 - furniture, 2 - garnishment ");
                switch (typeDeco) {
                    case 1 -> type = "furniture";
                    case 2 -> type = "garnishment";
                    default -> error("Invalid type. Please select 1, or 2.");
                }
                escapeRoomId = MenuScanner.readInt("Enter the id of the escape room: ");
                price = MenuScanner.readDouble("Enter the price of the decorative item: ");

            } catch (EmptyFieldException e) {
                error("Error: All fields must be filled in.");
            } catch (InvalidDificultyException e) {
                error(e.getMessage());
            }
        }
        return new CreateDecoDTO(name, description, type, escapeRoomId, price);
    }

}
