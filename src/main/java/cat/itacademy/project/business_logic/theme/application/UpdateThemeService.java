package cat.itacademy.project.business_logic.theme.application;

import cat.itacademy.project.api.escaperoom.application.FindEscapeRoomByIdController;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.theme.domain.Theme;
import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;
import cat.itacademy.project.shared.domain.dtos.theme.UpdateThemeDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateThemeService  {
    private final ThemeRepository repo;


    public UpdateThemeService( ThemeRepository repo) {
        this.repo = repo;
    }

    public Optional<ThemeDTO> execute(UpdateThemeDTO request) {
        if (request.name() == null || request.name().isBlank()) {
            throw new EmptyFieldException("Field 'name' cannot be empty.");
        }

        Optional<Theme> existingOptional = repo.findByName(request.nameToUpdate());

        if (existingOptional.isEmpty()) {
            throw new NotFoundException("Escape room with name '" + request.nameToUpdate() + "' does not exist.");
        }

        Theme theme = existingOptional.get();
        if (request.escapeRoomId() > 0 && request.escapeRoomId() != theme.getEscapeRoom().getId()) {
            try {
               EscapeRoomDTO response = new FindEscapeRoomByIdController().execute(request.escapeRoomId()).get();
               theme.setEscapeRoom(EscapeRoom.fromDatabase(response));
            } catch (Exception e) {
                throw new NotFoundException("Escape room with id '" + request.escapeRoomId() + "' does not exist.");
            }

        }
        theme.setName(request.name().isBlank() ? theme.getName() : request.name());
        theme.setDescription(request.description().isBlank() ? theme.getDescription() : request.description());

        return Optional.of(theme.toDTO());

    }
}
