package cat.itacademy.project.business_logic.deco.application;

import cat.itacademy.project.business_logic.deco.domain.Deco;
import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.dtos.deco.UpdateDecoDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateDecoService implements Command<DecoDTO> {

    private final UpdateDecoDTO request;
    private final DecoRepository repo;

    public UpdateDecoService(UpdateDecoDTO request, DecoRepository repo) {
        this.request = request;
        this.repo = repo;
    }

    @Override
    public Optional<DecoDTO> execute() {
        if (request.name() == null || request.name().isBlank()) {
            throw new EmptyFieldException("Field 'name' cannot be empty.");
        }

        Optional<DecoDTO> existingOptional = repo.findByName(request.nameToUpdate());

        if (existingOptional.isEmpty()) {
            throw new NotFoundException("Decorative Item with name '" + request.nameToUpdate() + "' does not exist.");
        }

        Deco deco = Deco.fromDatabase(existingOptional.get());

        deco.setName(
                request.name().isBlank()? deco.getName() : request.name()
        );

        deco.setDescription(
                request.description().isBlank()? deco.getDescription() : request.description()
        );

        deco.setType(
                request.type().isBlank()? deco.getType() : request.type()
        );

        deco.setEscapeRoomId(
                deco.getEscapeRoomId() != request.escapeRoomId() && request.escapeRoomId() > 0 ? request.escapeRoomId() : deco.getEscapeRoomId()
        );

        deco.setPrice(
                request.price() > 0 && request.price() != deco.getPrice()? request.price() : deco.getPrice()
        );

//        Deco decoToUpdate = Deco.fromDatabase(existingOptional.get());
//        Deco updatedDeco = decoToUpdate;

//        if (!request.name().equals(decoToUpdate.getName()) || !request.name().isBlank()) {
//            Optional<DecoDTO> existingWithNewName = repo.findByName((request.name()));
//            if (existingWithNewName.isPresent() && !Integer.valueOf(existingWithNewName.get().id()).equals(decoToUpdate.getId())) {
//                throw new AlreadyExistsException("Decorative Item with name '" + request.name() + "' already exist.");
//            }
//            updatedDeco = updatedDeco.createNewInstanceWithName(request.name());
//        }
//
//        if (request.description().equals(decoToUpdate.getDescription()) || !request.description().isBlank()){
//            updatedDeco = updatedDeco.createNewInstanceWithDescription(request.description());
//        }
//
//        if (request.escapeRoomId() > 0 && request.escapeRoomId() != decoToUpdate.getEscapeRoomId()) {
//            updatedDeco = updatedDeco.createNewInstanceWithEscapeRoomId(request.escapeRoomId());
//        }
//
//        if (request.price() > 0 && request.price() != decoToUpdate.getPrice()) {
//            updatedDeco = updatedDeco.createNewInstanceWithPrice(request.price());
//        }

        repo.update(deco);
        return Optional.of(deco.toDTO());
    }
}
