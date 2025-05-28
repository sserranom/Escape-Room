package cat.itacademy.project.business_logic.deco.domain;

import cat.itacademy.project.shared.domain.dtos.deco.CreateDecoDTO;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;

import java.util.List;
import java.util.Optional;

public interface DecoRepository {

    void create(CreateDecoDTO deco);

    void update(Deco deco);

    Optional<Void> delete(int id);

    Optional<DecoDTO> findById(int id);

    List<DecoDTO> findAll();

    Optional<DecoDTO> findByName(String name);

    List<DecoDTO> findAllByEscapeRoomId(int escapeRoomId);
}
