package cat.itacademy.project.frontend.reservation;

import cat.itacademy.project.api.reservation.FindAllReservationController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;

import java.util.List;
import java.util.Optional;

public class FindAllReservationMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        FindAllReservationController controller = new FindAllReservationController();
        Optional<List<ReservationDTO>> result = controller.execute();

        if (result.isEmpty() || result.get().isEmpty()) {
            info("No reservations found.");
        } else {
            info("List of reservations:");
            result.get().forEach(ReservationPrinter::print);
        }

        return Optional.empty();
    }
}
