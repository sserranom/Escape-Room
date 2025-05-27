package cat.itacademy.project.business_logic.puzzle.domain;

import cat.itacademy.project.shared.domain.dtos.puzzle.CreatePuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;

public class Puzzle {
    private int id;
    private String name;
    private String difficulty;
    private int roomId;
    private String answer;
    private String story;
    private int themeId;
    private double price;


    public Puzzle(int id, String name, int roomId, String answer, String story, int themeId, double price) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
        this.answer = answer;
        this.story = story;
        this.themeId = themeId;
        this.price = price;
    }

    public Puzzle(String name, String difficulty, int roomId, String answer, String story, int themeId, double price) {
        this.name = name;
        this.difficulty = difficulty;
        this.roomId = roomId;
        this.answer = answer;
        this.story = story;
        this.themeId = themeId;
        this.price = price;
    }

    public static Puzzle create(CreatePuzzleDTO dto) {
        return new Puzzle(dto.name(), dto.difficulty(), dto.roomId(), dto.answer(), dto.story(), dto.themeId(), dto.price());
    }

    public static Puzzle fromDatabase(PuzzleDTO dto) {
        return new Puzzle(dto.id(), dto.name(),  dto.room_id(), dto.answer(), dto.story(), dto.theme_id(), dto.price());
    }

    public PuzzleDTO toDTO() {
        return new PuzzleDTO(id, name,  roomId, answer, story, themeId, price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", roomID=" + roomId +
                ", answer='" + answer + '\'' +
                ", story='" + story + '\'' +
                ", themeId=" + themeId +
                ", price=" + price +
                '}';
    }


}
