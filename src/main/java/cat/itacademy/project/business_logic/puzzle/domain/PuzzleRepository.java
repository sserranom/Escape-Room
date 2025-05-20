package cat.itacademy.project.business_logic.puzzle.domain;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;

import java.util.List;
import java.util.Optional;

public interface PuzzleRepository {

    void create(Puzzle puzzle);

    void update(Puzzle puzzle);

    Optional<Void> delete(int id);

    Optional<Puzzle> findById(int id);

    List<Puzzle> findAll();

    Optional<Puzzle> findByName(String name);

}
