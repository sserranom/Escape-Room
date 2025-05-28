package cat.itacademy.project.frontend.deco;

import cat.itacademy.project.api.deco.FindAllDecoController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;

import java.util.List;
import java.util.Optional;

public class FindAllDecoMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {
        FindAllDecoController controller = new FindAllDecoController();
        List<DecoDTO> result = controller.execute().get();
        if (result.isEmpty()) {
            error("No Decorative Items Found.");

        } else {
            info("List of Decorative Items: ");
            result.forEach(DecoPrinter::print);
        }

        return Optional.empty();
    }
}
