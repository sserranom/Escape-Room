package cat.itacademy.escaperoom.shared;

public interface Repository {
    <T> void  create(T input);


     <T>void update(T input);


    void delete(int id);

    void findById(int id);

    void findAll();

    void findByName(String name);


}
