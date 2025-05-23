package cat.itacademy.project.frontend.deco;

import cat.itacademy.project.api.deco.FindAllDecoController;
import cat.itacademy.project.api.room.FindAllRoomsController;
import cat.itacademy.project.frontend.Room.RoomPrinter;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

import java.util.List;
import java.util.Optional;

public class FindAllDecoMenu extends MenuCommand<List<DecoDTO>> {
//    @Override
//    public Optional<List<DecoDTO>> execute() {
//        FindAllDecoController controller = new FindAllDecoController();
//        Optional<List<DecoDTO>> result = controller.execute();
//        if (result.isEmpty()) {
//            info("No Decorative item Found.");
//        } else {
//            info("List of Decorative Items: ");
//            result.get().forEach(deco -> log("Decorative Items"));
//        }
//
//        return result;
//    }

    @Override
    public Optional<List<DecoDTO>> execute() {
        FindAllDecoController controller = new FindAllDecoController();
        Optional<List<DecoDTO>> result = controller.execute();
        if (result.get().isEmpty()) {
            error("No Decorative Items Found.");
        } else {
            info("List of Decorative Items: ");
            result.get().forEach(DecoPrinter::print);
        }

        return result;
    }
}
