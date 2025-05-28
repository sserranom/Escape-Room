package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.business_logic.reward.application.CreateRewardService;
import cat.itacademy.project.business_logic.reward.infrastructure.RewardMongoRepository;
import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.UpdateReservationDTO;
import cat.itacademy.project.shared.domain.events.EventListener;
import cat.itacademy.project.shared.infrastructure.database.mongodb.MongoDBConnection;

public class PuzzleCompletedEventListener implements EventListener {
    private CreateRewardService service;

    public PuzzleCompletedEventListener() {
        this.service = new CreateRewardService(new RewardMongoRepository(MongoDBConnection.getDatabase()));
    }

    @Override
    public void update(String topic, DTO dto) {
        UpdateReservationDTO reservationDTO = (UpdateReservationDTO) dto;
        service.execute(reservationDTO);
        System.out.println("Sending reward: " + reservationDTO);
    }
}
