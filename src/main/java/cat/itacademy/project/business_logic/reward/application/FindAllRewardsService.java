package cat.itacademy.project.business_logic.reward.application;

import cat.itacademy.project.business_logic.reward.domain.Reward;
import cat.itacademy.project.business_logic.reward.domain.RewardRepository;

import java.util.List;

public class FindAllRewardsService {
    private final RewardRepository rewardRepository;

    public FindAllRewardsService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public List<Reward> execute() {
        return rewardRepository.findAll();
    }
}
