package cat.itacademy.project.buissness_logic.themes;

public class CreateNewTheme {
    private ThemeRepository repo;
    private Theme theme;

    public CreateNewTheme(CreateThemeDTO request,ThemeRepository repo) {
        this.theme = Theme.create(request);
        this.repo = repo;
    }

    public void execute() {
        repo.create(theme);
    }






}
