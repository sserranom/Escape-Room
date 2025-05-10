package cat.itacademy.project.menu.escaperoom;

import cat.itacademy.project.escaperoom.application.CreateEscapeRoom;
import cat.itacademy.project.escaperoom.domain.CreateEscapeRoomDTO;
import cat.itacademy.project.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.menu.shared.MenuCommand;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

public class CreateEscapeRoomService extends MenuCommand {
    private final EscapeRoomMySQLRepository repo;
    private final CreateEscapeRoomDTO createEscapeRoomDTO;

    public CreateEscapeRoomService(CreateEscapeRoomDTO createEscapeRoomDTO) {
        this.createEscapeRoomDTO = createEscapeRoomDTO;
        this.repo = new EscapeRoomMySQLRepository();
    }

    @Override
    public void execute() {
        CreateEscapeRoom creator;
        creator = new CreateEscapeRoom(createEscapeRoomDTO, repo);
            creator.create();

    }
}
