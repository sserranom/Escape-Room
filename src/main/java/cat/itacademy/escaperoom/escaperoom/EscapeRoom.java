package cat.itacademy.escaperoom.escaperoom;

public class EscapeRoom  {
    private int id;
    private final String name;
    private final String url;

    private EscapeRoom(int id, String name, String url) {
        this.name = name;
        this.url = url;
        this.id = id;
    }

    private EscapeRoom(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public static EscapeRoom create(String name, String url) {
        return new EscapeRoom(name, url);
    }
    public static EscapeRoom fromDatabase(int id, String name, String url) {
        return new EscapeRoom(id, name, url);
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
