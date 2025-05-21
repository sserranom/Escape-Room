package cat.itacademy.project.business_logic.deco.domain;

import cat.itacademy.project.shared.domain.dtos.deco.CreateDecoDTO;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;

public class Deco {
    private int id;
    private String name;
    private String description;
    private String type;
    private int escapeRoomId;
    private double price;

    public Deco(int id, String name, String description, String type, int escapeRoomId, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.escapeRoomId = escapeRoomId;
        this.price = price;
    }

    public Deco(String name, String description, String type, int escapeRoomId, double price) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.escapeRoomId = escapeRoomId;
        this.price = price;
    }

    public static Deco create(CreateDecoDTO dto) {
        return new Deco(dto.name(), dto.description(), dto.type(), dto.escapeRoomId(), dto.price());
    }

    public static Deco fromDatabase(DecoDTO dto) {
        return new Deco(dto.id(), dto.name(), dto.description(), dto.type(), dto.escapeRoomId(), dto.price());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getEscapeRoomId() {
        return escapeRoomId;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEscapeRoomId(int escapeRoomId) {
        this.escapeRoomId = escapeRoomId;
    }

    public  DecoDTO toDTO(){
        return new DecoDTO(id, name, description, type, escapeRoomId, price);
    }

    @Override
    public String toString() {
        return "Deco{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", escapeRoomId=" + escapeRoomId +
                ", price=" + price +
                '}';
    }

    public Deco createNewInstanceWithName(String newName) {
        return new Deco(this.id, newName, this.description, this.type, this.escapeRoomId, this.price);
    }

    public Deco createNewInstanceWithDescription(String newDescription) {
        return new Deco(this.id, this.name, newDescription, this.type, this.escapeRoomId, this.price);
    }

    public Deco createNewInstanceWithType(String newType) {
        return new Deco(this.id, this.name, this.description, newType, this.escapeRoomId, this.price);
    }

    public Deco createNewInstanceWithEscapeRoomId(int newEscapeRoomId) {
        return new Deco(this.id, this.name, this.description, this.type, newEscapeRoomId, this.price);
    }

    public Deco createNewInstanceWithPrice(double newPrice) {
        return new Deco(this.id, this.name, this.description, this.type, this.escapeRoomId, newPrice);
    }
}
