package cat.itacademy.project.frontend.deco;

import cat.itacademy.project.frontend.Menu;
import cat.itacademy.project.frontend.Room.UpdateRoomMenu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;

import java.util.Optional;

public class ManageDecoMenu  extends MenuCommand<DecoDTO> {
    private final DecoDTO decoDTO;

    public ManageDecoMenu(DecoDTO decoDTO) {
        this.decoDTO = decoDTO;
    }

    @Override
    public Optional<DecoDTO> execute() {
        int choice = getUserImput();

        switch (choice){
            case 1:
                log("Create decorative Item: ");
                CreateDecoMenu createDecoMenu = new CreateDecoMenu();
                createDecoMenu.execute();
                break;

            case 2:
                log("Update decorative Item: ");
                UpdateDecoMenu updateDecoMenu = new UpdateDecoMenu();
                updateDecoMenu.execute();
                break;

            case 3:
                log("Show all decorative Items: ");
                FindAllDecoMenu findAllDecoMenu = new FindAllDecoMenu();
                findAllDecoMenu.execute();
                break;

            case 4:
                log("Back: ");
                Menu menu = new Menu();
                menu.execute();
                break;

        }
        return Optional.ofNullable(decoDTO);
    }

    private int getUserImput(){
        log("1. Create new Decorative Item ");
        log("2. Update Decorative Item ");
        log("3. View Decotarive Item details ");
        log("4. Back <-");

        return MenuScanner.readInt("Please enter your Choice: ");
    }
}
