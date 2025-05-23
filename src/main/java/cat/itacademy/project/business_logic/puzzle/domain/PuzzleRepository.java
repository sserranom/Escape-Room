package cat.itacademy.project.business_logic.puzzle.domain;

import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;

import java.util.List;
import java.util.Optional;

public interface PuzzleRepository {

    void create(Puzzle puzzle);

    void update(Puzzle puzzle);

    Optional<Void> delete(int id);

    Optional<PuzzleDTO> findById(int id);

    List<PuzzleDTO> findAll();

    Optional<PuzzleDTO> findByName(String name);

}
