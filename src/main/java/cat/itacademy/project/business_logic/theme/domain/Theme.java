package cat.itacademy.project.business_logic.theme.domain;

import cat.itacademy.project.business_logic.escaperoom.application.FindAllEscapeRoomsService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.theme.CreateThemeDTO;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;

public class Theme {
    private int id;
    private String name;
    private String description;
    private EscapeRoom escapeRoom;


    public Theme(String name, String description, EscapeRoom escapeRoom) {
        this.name = name;
        this.description = description;
        this.escapeRoom = escapeRoom;
    }

    public static Theme fromDatabase(ThemeDTO dto) {

        EscapeRoom escapeRoom = getEscaperoomFromDto(dto);

        return new Theme(dto.name(), dto.description(), escapeRoom);
    }

    private static EscapeRoom getEscaperoomFromDto(ThemeDTO dto) {
        FindAllEscapeRoomsService finder = new FindAllEscapeRoomsService(new EscapeRoomMySQLRepository(MySqlConnection.getInstance()));
        List<EscapeRoomDTO> escapeRoomList = finder.findAll();
        return escapeRoomList.stream()
                .filter(escapeRoomDTO -> escapeRoomDTO.id() == dto.escapeRoomId())
                .findFirst()
                .map(EscapeRoom::fromDatabase)
                .orElseThrow(() -> new IllegalArgumentException("Escape room not found"));
    }

    private static EscapeRoom getEscaperoomFromDto(CreateThemeDTO dto) {
        FindAllEscapeRoomsService finder = new FindAllEscapeRoomsService(new EscapeRoomMySQLRepository(MySqlConnection.getInstance()));
        List<EscapeRoomDTO> escapeRoomList = finder.findAll();
        return escapeRoomList.stream()
                .filter(escapeRoomDTO -> escapeRoomDTO.id() == dto.escapeRoomId())
                .findFirst()
                .map(EscapeRoom::fromDatabase)
                .orElseThrow(() -> new IllegalArgumentException("Escape room not found"));
    }

    public static Theme create(CreateThemeDTO dto) {
        EscapeRoom escapeRoom = getEscaperoomFromDto(dto);
        return new Theme(dto.name(), dto.description(), escapeRoom);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EscapeRoom getEscapeRoom() {
        return escapeRoom;
    }

    public void setEscapeRoom(EscapeRoom escapeRoom) {
        this.escapeRoom = escapeRoom;
    }

    public ThemeDTO toDTO() {
        return new ThemeDTO(this.id, name, description, escapeRoom.getId());
    }

    @Override
    public String toString() {
        return "Theme{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
