package cat.itacademy.project.frontend.reservation;

import cat.itacademy.project.api.reservation.FindReservationByIdController;
import cat.itacademy.project.api.customer.FindCustomerByIdController;
import cat.itacademy.project.api.puzzle.FindPuzzleByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;
import java.time.format.DateTimeFormatter;

public class PrintReservationTicketMenu extends MenuCommand<Void> {

    private final FindReservationByIdController findReservationByIdController;
    private final FindCustomerByIdController findCustomerByIdController;
    private final FindPuzzleByIdController findPuzzleByIdController;

    public PrintReservationTicketMenu() {
        this.findReservationByIdController = new FindReservationByIdController();
        this.findCustomerByIdController = new FindCustomerByIdController();
        this.findPuzzleByIdController = new FindPuzzleByIdController();
    }

    @Override
    public Optional<Void> execute() {
        info("\n--- Print Reservation Ticket ---");
        int reservationId = MenuScanner.readInt("Enter the ID of the reservation to print ticket: ");

        try {
            Optional<Optional<ReservationDTO>> outerReservationOptional = findReservationByIdController.execute(reservationId);

            if (outerReservationOptional.isEmpty()) {
                throw new NotFoundException("Reservation with ID " + reservationId + " could not be retrieved due to an internal issue or invalid ID.");
            }

            Optional<ReservationDTO> reservationOptional = outerReservationOptional.get();

            if (reservationOptional.isEmpty()) {
                throw new NotFoundException("Reservation with ID " + reservationId + " not found.");
            }

            ReservationDTO reservation = reservationOptional.get();

            CustomerDTO customer = null;
            if (reservation.customerId() != null) {
                Optional<CustomerDTO> customerOptional = findCustomerByIdController.execute(reservation.customerId());
                if (customerOptional.isPresent()) {
                    customer = customerOptional.get();
                } else {
                    info("Warning: Customer with ID " + reservation.customerId() + " not found. Ticket will be printed without customer details.");
                }
            }

            PuzzleDTO puzzle = null;
            if (reservation.puzzleId() != null) {
                Optional<Optional<PuzzleDTO>> outerPuzzleOptional = findPuzzleByIdController.execute(reservation.puzzleId());

                if (outerPuzzleOptional.isEmpty()) {
                    info("Warning: Puzzle with ID " + reservation.puzzleId() + " could not be retrieved due to an internal issue. Ticket will be printed without puzzle name.");
                } else {
                    Optional<PuzzleDTO> puzzleOptional = outerPuzzleOptional.get();

                    if (puzzleOptional.isPresent()) {
                        puzzle = puzzleOptional.get();
                    } else {
                        info("Warning: Puzzle with ID " + reservation.puzzleId() + " not found. Ticket will be printed without puzzle name.");
                    }
                }
            }

            printTicket(reservation, customer, puzzle);

        } catch (NotFoundException e) {
            error("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            error("Error: " + e.getMessage());
        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private void printTicket(ReservationDTO reservation, CustomerDTO customer, PuzzleDTO puzzle) {
        info("\n----------------------------------------------------");
        info("               RESERVATION TICKET");
        info("----------------------------------------------------");
        info("Reservation ID: " + reservation.id());
        info("----------------------------------------------------");
        info("CUSTOMER DETAILS:");
        info("  Name:  " + (customer != null && customer.name() != null ? customer.name() : "N/A"));
        info("  Email: " + (customer != null && customer.email() != null ? customer.email() : "N/A"));
        info("----------------------------------------------------");
        info("RESERVATION DETAILS:");
        info("  Puzzle:        " + (puzzle != null && puzzle.name() != null ? puzzle.name() : "N/A"));
        info("  Total Price:   " + String.format("%.2f", reservation.total_price()) + " €");
        info("  Created On:    " + (reservation.creation_date() != null ? reservation.creation_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "N/A"));
        info("  Completion Date: " + (reservation.completionDate() != null ? reservation.completionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "N/A"));
        info("----------------------------------------------------");
        info("        THANK YOU FOR YOUR RESERVATION!");
        info("----------------------------------------------------\n");
    }
}