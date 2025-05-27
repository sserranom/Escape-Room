package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.customer.domain.Customer;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.UpdateReservationDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateReservationService {
    private final ReservationRepository repo;
    private final CustomerRepository customerRepo;
    private final PuzzleRepository puzzleRepo;


    public UpdateReservationService(ReservationRepository repo, CustomerRepository customerRepo, PuzzleRepository puzzleRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
        this.puzzleRepo = puzzleRepo;
    }

    public Optional<Reservation> execute(UpdateReservationDTO request) {
        if (request.id() <= 0) {
            throw new IllegalArgumentException("Reservation ID must be positive for update.");
        }

        Optional<ReservationDTO> existingReservationOpt = repo.findById(request.id());

        if (existingReservationOpt.isEmpty()) {
            throw new NotFoundException("Reservation with ID " + request.id() + " not found for update.");
        }

        Reservation existingReservation = Reservation.fromDatabase(existingReservationOpt.get());

        if (request.totalPrice() != null) {
            if (request.totalPrice() <= 0) {
                throw new IllegalArgumentException("Total price must be greater than zero for update.");
            }
            existingReservation.setTotalPrice(request.totalPrice());
        }

        if (request.completionDate() != null) {
            existingReservation.setCompletionDate(request.completionDate());
        }
        if (request.id_customer() <=0 ) {

            customerRepo.findById(request.id_customer())
                    .orElseThrow(() -> new NotFoundException("Customer with ID " + request.id_customer() + " not found."));
            existingReservation.setCustomerId(request.id_customer());
        }

        if (request.id_puzzle() <= 0) {

            existingReservation.setPuzzleId(request.id_puzzle());
        }


       repo.update(existingReservation);

        return Optional.of(existingReservation);
    }
}
