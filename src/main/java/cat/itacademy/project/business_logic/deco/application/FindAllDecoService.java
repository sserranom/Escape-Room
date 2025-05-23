package cat.itacademy.project.business_logic.deco.application;

import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;

import java.util.List;

public class FindAllDecoService {
    private final DecoRepository repo;

    public FindAllDecoService(DecoRepository repo) {
        this.repo = repo;
    }

    public List<DecoDTO> findAll() {
        return repo.findAll();
    }
}
