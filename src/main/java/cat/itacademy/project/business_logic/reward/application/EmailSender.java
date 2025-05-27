package cat.itacademy.project.business_logic.reward.application;

import cat.itacademy.project.shared.domain.dtos.reward.CreateRewardDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class EmailSender {
    private static final Logger logger = Logger.getLogger(EmailSender.class.getName());

    public static void send(CreateRewardDTO createRewardDTO) {
        if (createRewardDTO == null) {
            throw new IllegalArgumentException("RewardDTO cannot be null");
        }
        send(createRewardDTO.recipient(), createRewardDTO.description(), createRewardDTO.deliveryDate());
    }

    public static void send(String recipient, String description, Date deliveryDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = (deliveryDate != null) ? dateFormat.format(deliveryDate) : "N/A";
        String emailContent = String.format(
                "=== Sending Email ===%nTo: %s%nSubject: Reward Notification%n%n%s%nDelivery Date: %s%n=====================",
                recipient, description, formattedDate
        );

        logger.info(emailContent);
    }

}
