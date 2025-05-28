package cat.itacademy.project.business_logic.reward.domain;

import cat.itacademy.project.shared.domain.dtos.reward.CreateRewardDTO;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface RewardRepository {
    void create(CreateRewardDTO reward);

    void save(Reward reward);

    Optional<Reward> findById(ObjectId id);

    List<Reward> findAll();

}
