package cat.itacademy.project.buissness_logic.themes;

public class CreateNewThemeService {
    private ThemeRepository repo;
    private Theme theme;

    public CreateNewThemeService(CreateThemeDTO request, ThemeRepository repo) {
        this.theme = Theme.create(request);
        this.repo = repo;
    }

    public void execute() {
        repo.create(theme);
    }






}
