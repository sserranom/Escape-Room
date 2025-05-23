package cat.itacademy.project.business_logic.puzzle.domain;

import cat.itacademy.project.shared.domain.dtos.CreatePuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.PuzzleDTO;

public class Puzzle {
    private int id;
    private String name;
    private String difficulty;
    private int roomId;
    private String answer;
    private String story;
    private int themeId;
    private double price;


    public Puzzle(int id, String name, String difficulty, int roomId, String answer, String story, int themeId, double price) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
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

    public PuzzleDTO toDTO() {
        return new PuzzleDTO(id, name, difficulty, roomId, answer, story, themeId, price);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getStory() {
        return story;
    }

    public int getThemeId() {
        return themeId;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public static Puzzle create(CreatePuzzleDTO dto) {
        return new Puzzle(dto.name(), dto.difficulty(), dto.roomId(), dto.answer(), dto.story(), dto.themeId(), dto.price());
    }

    public static Puzzle fromDatabase(PuzzleDTO dto) {
        return new Puzzle(dto.id(), dto.name(), dto.difficulty(), dto.roomId(), dto.answer(), dto.story(), dto.themeId(), dto.price());
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

    public Puzzle createNewInstanceWithName(String newName) {
        return new Puzzle(this.id, newName, this.difficulty, this.roomId, this.answer, this.story, this.themeId, this.price);
    }

    public Puzzle createNewInstanceWithDifficulty(String newDifficulty) {
        return new Puzzle(this.id, this.name, newDifficulty, this.roomId, this.answer, this.story, this.themeId, this.price);
    }

    public Puzzle createNewInstanceWithRoomId(int newRoomId) {
        return new Puzzle(this.id, this.name, this.difficulty, newRoomId, this.answer, this.story, this.themeId, this.price);
    }

    public Puzzle createNewInstanceWithAnswer(String newAnswer) {
        return new Puzzle(this.id, this.name, this.difficulty, this.roomId, newAnswer, this.story, this.themeId, this.price);
    }

    public Puzzle createNewInstanceWithStory(String newStory) {
        return new Puzzle(this.id, this.name, this.difficulty, this.roomId, this.answer, newStory, this.themeId, this.price);
    }

    public Puzzle createNewInstanceWithThemeId(int newThemeId) {
        return new Puzzle(this.id, this.name, this.difficulty, this.roomId, this.answer, this.story, newThemeId, this.price);
    }

    public Puzzle createNewInstanceWithPrice(double newPrice) {
        return new Puzzle(this.id, this.name, this.difficulty, this.roomId, this.answer, this.story, this.themeId, newPrice);
    }


}
