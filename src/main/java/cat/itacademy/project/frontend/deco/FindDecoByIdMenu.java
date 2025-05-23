package cat.itacademy.project.frontend.deco;

import cat.itacademy.project.api.deco.FindDecoByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;

import java.util.Optional;

public class FindDecoByIdMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            int idToFind = MenuScanner.readInt("Enter the ID of the decorative Item:  ");
            FindDecoByIdController controller = new FindDecoByIdController(idToFind);
            Optional<Optional<DecoDTO>> decoDTO = controller.execute();

            if (decoDTO.isPresent()) {
                Optional<DecoDTO> foundDeco = decoDTO.get();
                DecoPrinter.print(foundDeco.get());
            } else {
                error("Decorative Item with ID " + idToFind + " not found.");
            }

        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}
