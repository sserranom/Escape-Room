package cat.itacademy.escaperoom.escaperoom.application;

import cat.itacademy.escaperoom.escaperoom.EscapeRoomMySQLTestRepository;
import cat.itacademy.escaperoom.escaperoom.infrastructure.MySqlTestConnection;
import cat.itacademy.project.business_logic.escaperoom.application.CreateEscapeRoomService;
import cat.itacademy.project.shared.domain.dtos.escape_room.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateEscapeRoomServiceTest {
    private final EscapeRoomMySQLTestRepository repo = new EscapeRoomMySQLTestRepository(MySqlTestConnection.getInstance());

    @BeforeEach
    void setUp() {
        repo.restore();
    }

    @AfterEach
    void tearDown() {
        repo.restore();
    }

    @Test
    void execute_an_escape_room() {
        CreateEscapeRoomDTO request = new CreateEscapeRoomDTO("Escape Room 1", "url");
        CreateEscapeRoomService creator = new CreateEscapeRoomService(repo);
        creator.execute(request);

        assertThat(repo.findByName("Escape Room 1"))
                .isPresent()
                .hasValueSatisfying(escapeRoom -> {
                    assertEquals("Escape Room 1", escapeRoom.getName());
                    assertEquals("url", escapeRoom.getUrl());
                });
    }


    @Test
    void throws_exception_when_fields_are_empty() {

        assertThatExceptionOfType(EmptyFieldException.class)
                .isThrownBy(() -> new CreateEscapeRoomDTO("", "url"))
                .withMessage("Name cannot be null or empty");
        assertThatExceptionOfType(EmptyFieldException.class)
                .isThrownBy(() -> new CreateEscapeRoomDTO("name", ""))
                .withMessage("URL cannot be null or empty");
    }

    @Test
    void throws_exception_when_fields_are_null() {
        assertThatExceptionOfType(EmptyFieldException.class)
                .isThrownBy(() -> new CreateEscapeRoomDTO(null, "url"))
                .withMessage("Name cannot be null or empty");
        assertThatExceptionOfType(EmptyFieldException.class)
                .isThrownBy(() -> new CreateEscapeRoomDTO("name", null))
                .withMessage("URL cannot be null or empty");
    }

}