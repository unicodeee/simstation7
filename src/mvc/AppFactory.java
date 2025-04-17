package mvc;

public interface AppFactory {
    String getTitle();

    String[] getEditCommands();

    String getHelp();

    String about();

    mvc.Model makeModel();

    View makeView(mvc.Model m);

    Command makeEditCommand(Model model, String type, Object source);
}
