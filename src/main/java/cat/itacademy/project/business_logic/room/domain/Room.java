package cat.itacademy.project.business_logic.room.domain;

import cat.itacademy.project.shared.domain.dtos.room.CreateRoomDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

public class Room {
    private int id;
    private String name;
    private double price;
    private int theme_id;
    private String difficulty;
    private String themeName;

    public Room(int id, String name, String difficulty, double price, int theme_id, String themeName) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.theme_id = theme_id;
        this.themeName = themeName;
    }

    public Room(String name, String difficulty, double price, int theme_id) {
        this.name = name;
        this.price = price;
        this.theme_id = theme_id;
        this.difficulty = difficulty;
    }

    public static Room create(CreateRoomDTO dto) {
        return new Room(dto.name(), dto.difficulty(), dto.price(), dto.themeId());
    }

    public static Room fromDatabase(RoomDTO dto) {
        return new Room(dto.id(), dto.name(), dto.difficulty(), dto.price(), dto.themeId(), dto.themeName());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTheme_id() {
        return theme_id;
    }
    public String getThemeName(){
        return themeName;
    }

    public void setTheme_id(int themeId) {
        this.theme_id = themeId;
    }

    public RoomDTO toDTO() {
        return new RoomDTO(id, name, difficulty, price, theme_id, themeName);
    }

//    @Override
//    public String toString() {
//        return "Room{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", price=" + price +
//                ", escapeRoomId=" + theme_id +
//                '}';
//    }


    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", theme_id=" + theme_id +
                ", difficulty='" + difficulty + '\'' +
                ", themeName='" + themeName + '\'' +
                '}';
    }

    public Room createNewInstanceWithName(String newName) {
        return new Room(newName, this.difficulty, this.price, this.theme_id);
    }

    public Room createNewInstanceWithPrice(double newPrice) {
        return new Room(this.name, this.difficulty, newPrice, this.theme_id);
    }

    public Room createNewInstanceWithEscapeRoomId(int newEscapeRoomId) {
        return new Room(this.name, this.difficulty, this.price, newEscapeRoomId);
    }

    public String getDifficulty() {
        return this.difficulty;
    }
}
