package cat.itacademy.project.business_logic.theme.application;

import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;

import java.util.Optional;

public class FindThemeByIdService implements Command<ThemeDTO> {
    private final ThemeRepository repo;
    private final int idToFind;

    public FindThemeByIdService(int idToFind, ThemeRepository repo) {
        this.repo = repo;
        this.idToFind = idToFind;
    }

    @Override
    public Optional<ThemeDTO> execute() {
        return repo.findById(idToFind);

    }
}
