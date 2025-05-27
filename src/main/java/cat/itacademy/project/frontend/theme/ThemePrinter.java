package cat.itacademy.project.frontend.theme;

import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;

public class ThemePrinter {

    static void print(ThemeDTO themeDTO) {
        System.out.printf("Id: %d   |  Name:  %s  |  Description:  %s |  Id: %d%n",
                themeDTO.id(), themeDTO.name(), themeDTO.description(), themeDTO.escapeRoomId());
    }
}
