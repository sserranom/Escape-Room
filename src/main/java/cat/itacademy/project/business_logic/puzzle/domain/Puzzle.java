package cat.itacademy.project.business_logic.room.domain;

import cat.itacademy.project.shared.domain.dtos.CreateRoomDTO;
import cat.itacademy.project.shared.domain.dtos.RoomDTO;

public class Room {
    private int id;
    private String name;
    private double price;
    private int escapeRoomId;

    public Room(int id, String name, double price, int escapeRoomId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.escapeRoomId = escapeRoomId;
    }

    public Room(String name, double price, int escapeRoomId) {
        this.name = name;
        this.price = price;
        this.escapeRoomId = escapeRoomId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getEscapeRoomId() {
        return escapeRoomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEscapeRoomId(int escapeRoomId) {
        this.escapeRoomId = escapeRoomId;
    }

    public static Room create (CreateRoomDTO dto){
        return new Room(dto.name(), dto.price(), dto.escapeRoomId());
    }

    public static Room fromDatabase(RoomDTO dto){
        return new Room(dto.id(), dto.name(), dto.price(), dto.escapeRoomId());
    }

    public RoomDTO toDTO(){
        return new RoomDTO(id, name, price, escapeRoomId);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", escapeRoomId=" + escapeRoomId +
                '}';
    }

    public Room createNewInstanceWithName(String newName){
        return new Room(this.id, newName, this.price, this.escapeRoomId);
    }

    public Room createNewInstanceWithPrice(double newPrice){
        return new Room(this.id, this.name, newPrice, this.escapeRoomId);
    }

    public Room createNewInstanceWithEscapeRoomId (int newEscapeRoomId){
        return new Room(this.id, this.name, this.price, newEscapeRoomId);
    }
}
