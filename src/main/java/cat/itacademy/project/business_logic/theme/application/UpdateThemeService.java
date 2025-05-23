package cat.itacademy.project.business_logic.theme.application;

import cat.itacademy.project.business_logic.theme.domain.Theme;
import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;
import cat.itacademy.project.shared.domain.dtos.theme.UpdateThemeDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateThemeService implements Command<ThemeDTO> {
    private final UpdateThemeDTO request;
    private final ThemeRepository repo;


    public UpdateThemeService(UpdateThemeDTO request, ThemeRepository repo) {
        this.request = request;
        this.repo = repo;
    }

    public Optional<ThemeDTO> execute() {
        if (request.name() == null || request.name().isBlank()) {
            throw new EmptyFieldException("Field 'name' cannot be empty.");
        }

        Optional<Theme> existingOptional = repo.findByName(request.nameToUpdate());

        if (existingOptional.isEmpty()) {
            throw new NotFoundException("Escape room with name '" + request.nameToUpdate() + "' does not exist.");
        }

        Theme themeToUpdate = existingOptional.get();
        Theme updatedTheme = themeToUpdate;

        return Optional.of(updatedTheme.toDTO());

    }
}
