package cat.itacademy.escaperoom.escaperoom.application;

import cat.itacademy.escaperoom.escaperoom.EscapeRoomMySQLTestRepository;
import cat.itacademy.project.buissness_logic.escaperoom.application.CreateEscapeRoom;
import cat.itacademy.project.shared.domain.dtos.CreateEscapeRoomDTO;
import cat.itacademy.project.buissness_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateEscapeRoomTest {
    private EscapeRoomMySQLTestRepository repo;

    @BeforeEach
    void setUp() {
        repo = new EscapeRoomMySQLTestRepository();
        repo.restore();
    }

    @AfterEach
    void tearDown() {
        repo.restore();
    }

    @Test
     void create_an_escape_room() {
         CreateEscapeRoomDTO request = new CreateEscapeRoomDTO("Escape Room 1", "url");
         CreateEscapeRoom creator = new CreateEscapeRoom(request, new EscapeRoomMySQLRepository());
            creator.create();

            Assertions.assertThat(repo.findByName("Escape Room 1"))
                    .isPresent()
                    .hasValueSatisfying(escapeRoom -> {
                        assertEquals("Escape Room 1", escapeRoom.getName());
                        assertEquals("url", escapeRoom.getUrl());
                    });
     }

    @Test
    void throws_exception_when_escape_room_already_exists() {
        CreateEscapeRoomDTO request = new CreateEscapeRoomDTO("Escape Room 1", "url");
        CreateEscapeRoom creator = new CreateEscapeRoom(request, new EscapeRoomMySQLRepository());
        creator.create();

        assertThrows(AlreadyExistsException.class, creator::create);
    }

    @Test
    void throws_exception_when_fields_are_empty() {

        assertThrows(EmptyFieldException.class, () -> new CreateEscapeRoomDTO("", "url"));
        assertThrows(EmptyFieldException.class, () -> new CreateEscapeRoomDTO("hola", ""));
    }

}