package mvc;

public abstract class Command {
    protected mvc.Model model;
    public Command(Model model) {
        this.model = model;
    }
    public abstract void execute();
}
