package cat.itacademy.project.business_logic.reward.application;

import cat.itacademy.project.business_logic.reward.domain.Reward;
import cat.itacademy.project.business_logic.reward.domain.RewardRepository;

import java.util.Date;

public class RewardService {
    private final RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public void sendRewardToCustomer(Reward reward) {

        String recipient = reward.getRecipient();
        String description = reward.getDescription();
        Date deliveryDate = reward.getDeliveryDate();

        EmailSender.send(recipient, description, deliveryDate);


        rewardRepository.save(reward);
    }
}
