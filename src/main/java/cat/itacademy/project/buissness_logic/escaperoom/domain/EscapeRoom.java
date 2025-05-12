package cat.itacademy.project.buissness_logic.escaperoom.domain;

import cat.itacademy.project.shared.domain.dtos.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

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
}
