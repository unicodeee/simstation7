package mvc;

import java.util.ArrayList;

public class Publisher {
    private ArrayList<mvc.Subscriber> subs = new ArrayList<>();

    public void subscribe(mvc.Subscriber sub) { subs.add(sub); }

    public void unsubscribe(mvc.Subscriber sub) { subs.remove(sub); }

    public void notifySubscribers() {
        for(Subscriber sub : subs) {
            sub.update();
        }
    }

}
