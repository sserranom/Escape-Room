package cat.itacademy.project.shared.domain;

import java.util.Optional;

public interface Command<T> {
    Optional<T> execute();
}
