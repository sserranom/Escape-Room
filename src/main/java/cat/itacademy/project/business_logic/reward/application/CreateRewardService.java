package cat.itacademy.project.business_logic.reward.application;

import cat.itacademy.project.business_logic.reward.domain.RewardRepository;
import cat.itacademy.project.shared.domain.dtos.reward.CreateRewardDTO;

public class CreateRewardService {
    private final RewardRepository rewardRepository;

    public CreateRewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public void execute(CreateRewardDTO reward) {
        rewardRepository.create(reward);
    }
}
