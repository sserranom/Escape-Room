package cat.itacademy.project.business_logic.reward.application;

import cat.itacademy.project.shared.domain.dtos.reward.CreateRewardDTO;

import java.util.logging.Logger;

public class EmailSender {
    private static final Logger logger = Logger.getLogger(EmailSender.class.getName());

    public static void send(CreateRewardDTO createRewardDTO) {
        if (createRewardDTO == null) {
            throw new IllegalArgumentException("RewardDTO cannot be null");
        }
        send(createRewardDTO.recipient(), createRewardDTO.description());
    }

    public static void send(String recipient, String description) {
        String emailContent = String.format(
                "=== Sending Email ===%nTo: %s%nSubject: Reward Notification%n%n%s%%n=====================",
                recipient, description
        );

        logger.info(emailContent);
    }

}
