package cat.itacademy.project.frontend.reservation;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;
import cat.itacademy.project.shared.domain.dtos.theme.CreateThemeDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class CreateReservationMenu extends MenuCommand<Void> {
    private Integer customer_id;
    private Integer puzzle_id;
    private LocalDate completion_date;


    @Override
    public Optional<Void> execute() {
        CreateReservationDTO request = getUserInfo();
        return Optional.empty();
    }
    public CreateReservationDTO getUserInfo() {


        while (customer_id == null || puzzle_id == null) {

            try {
                customer_id = MenuScanner.readInt("Enter the Customer ID: ");
                puzzle_id = MenuScanner.readInt("Enter the Puzzle ID: ");
                completion_date = MenuScanner.readDate("Enter the Date completion: ");
            } catch (EmptyFieldException e) {
                error("Field cannot be empty");
            } catch (Exception e) {
                error("An unexpected error occurred: " + e.getMessage());
            }
        }
        return new CreateReservationDTO(customer_id, puzzle_id, completion_date);
    }
}
