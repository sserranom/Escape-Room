package cat.itacademy.project.business_logic.reward.application;

import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.reservation.infraestructure.ReservationMySQLRepository;
import cat.itacademy.project.business_logic.reward.domain.RewardRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.UpdateReservationDTO;
import cat.itacademy.project.shared.domain.dtos.reward.CreateRewardDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

public class CreateRewardService {
    private final RewardRepository rewardRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    public CreateRewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
        this.customerRepository = new CustomerMySQLRepository(MySqlConnection.getInstance());
        this.reservationRepository = new ReservationMySQLRepository(MySqlConnection.getInstance());
    }

    public void execute(UpdateReservationDTO dto) {
        ReservationDTO reservation = reservationRepository.findById(dto.id()).get();
        UpdateReservationDTO reservationDTO = new UpdateReservationDTO(
                reservation.id(),
                reservation.customerId(),
                reservation.puzzleId(),
                reservation.totalPrice(),
                reservation.completionDate()
        );
        CreateRewardDTO reward = createReward(reservationDTO);
        rewardRepository.create(reward);
    }

    private CreateRewardDTO createReward(UpdateReservationDTO dto) {
        CustomerDTO customer = customerRepository.findById(dto.id_customer()).get();

        return new CreateRewardDTO(
                customer.email(),
                "piruleta"
        );

    }
}
