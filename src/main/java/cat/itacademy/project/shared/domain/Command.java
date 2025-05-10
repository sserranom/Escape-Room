package cat.itacademy.project.shared.domain;

import java.util.Optional;

public abstract class Command<T> {
    public abstract Optional<T> execute();
}
