package cat.itacademy.escaperoom.escaperoom;

public class EscapeRoomCreator {
    private final EscapeRoom escapeRoom;
    private final EscapeRoomRepository repo;

    public EscapeRoomCreator(EscapeRoom escapeRoom, EscapeRoomRepository repo ) {
        this.escapeRoom = escapeRoom;
        this.repo = repo;
    }

    public void create() {
        EscapeRoom existing = repo.findByName(escapeRoom.getName());
        System.out.println(existing);
        if (existing != null) {
            throw new IllegalArgumentException("Escape Room '" + escapeRoom.getName() + "' does not exist");
        }
        repo.create(escapeRoom);

    }

}
