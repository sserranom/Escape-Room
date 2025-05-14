package cat.itacademy.project.buissness_logic.themes;


import java.util.List;
import java.util.stream.Collectors;


public class FindThemes {
    private final ThemeRepository repo;

    public FindThemes(ThemeRepository repo) {
        this.repo = repo;
    }

    public List<ThemeDTO> findAll() {
        return repo.findAll()
            .stream()
            .map(Theme::toDTO)
            .collect(Collectors.toList());
    }
}