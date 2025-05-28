package cat.itacademy.project.business_logic.escaperoom.domain;

import cat.itacademy.project.shared.domain.dtos.escape_room.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;

public class EscapeRoom {
    private final String name;
    private final String url;
    private int id;

    private EscapeRoom(int id, String name, String url) {
        this.name = name;
        this.url = url;
        this.id = id;
    }

    private EscapeRoom(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public static EscapeRoom create(CreateEscapeRoomDTO dto) {
        return new EscapeRoom(dto.name(), dto.url());
    }

    public static EscapeRoom fromDatabase(EscapeRoomDTO dto) {
        return new EscapeRoom(dto.id(), dto.name(), dto.url());
    }

    public EscapeRoomDTO toDTO() {
        return new EscapeRoomDTO(id, name, url);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "EscapeRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public EscapeRoom createNewInstanceWithName(String newName) {
        return new EscapeRoom(this.id, newName, this.url);
    }

    public EscapeRoom createNewInstanceWithUrl(String newUrl) {
        return new EscapeRoom(this.id, this.name, newUrl);
    }
}
