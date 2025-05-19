package cat.itacademy.project.business_logic.themes;

import cat.itacademy.project.business_logic.escaperoom.application.FindAllEscapeRoomsService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.CreateThemeDTO;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.ThemeDTO;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public EscapeRoom getEscapeRoom() {
        return escapeRoom;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEscapeRoom(EscapeRoom escapeRoom) {
        this.escapeRoom = escapeRoom;
    }

    public static Theme fromDatabase(ThemeDTO dto) {

        EscapeRoom escapeRoom = getEscaperoomFromDto(dto);

        return new Theme(dto.name(), dto.description(), escapeRoom);
    }

    private static EscapeRoom getEscaperoomFromDto(ThemeDTO dto) {
        FindAllEscapeRoomsService finder = new FindAllEscapeRoomsService(new EscapeRoomMySQLRepository(MySqlConnection.getInstance()));
        List<EscapeRoomDTO> escapeRoomList = finder.findAll();
        EscapeRoom escapeRoom = escapeRoomList.stream()
                .filter(escapeRoomDTO -> escapeRoomDTO.id() == dto.escapeRoomId())
                .findFirst()
                .map(EscapeRoom::fromDatabase)
                .orElseThrow(() -> new IllegalArgumentException("Escape room not found"));
        return escapeRoom;
    }
private static EscapeRoom getEscaperoomFromDto(CreateThemeDTO dto) {
        FindAllEscapeRoomsService finder = new FindAllEscapeRoomsService(new EscapeRoomMySQLRepository(MySqlConnection.getInstance()));
        List<EscapeRoomDTO> escapeRoomList = finder.findAll();
        EscapeRoom escapeRoom = escapeRoomList.stream()
                .filter(escapeRoomDTO -> escapeRoomDTO.id() == dto.escapeRoomId())
                .findFirst()
                .map(EscapeRoom::fromDatabase)
                .orElseThrow(() -> new IllegalArgumentException("Escape room not found"));
        return escapeRoom;
    }

    public ThemeDTO toDTO() {
        return new ThemeDTO(this.id, name, description, escapeRoom.getId());
    }

    public static Theme create(CreateThemeDTO dto) {
        EscapeRoom escapeRoom = getEscaperoomFromDto(dto);
        return new Theme(dto.name(), dto.description(), escapeRoom);
    }

    @Override
    public String toString() {
        return "Theme{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
