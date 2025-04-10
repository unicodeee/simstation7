package mvc;

import java.util.ArrayList;

public class Publisher {
    private ArrayList<Subscriber> subs = new ArrayList<>();

    public void subscribe(Subscriber sub) { subs.add(sub); }

    public void unsubscribe(Subscriber sub) { subs.remove(sub); }

    public void notifySubscribers() {
        for(Subscriber sub : subs) {
            sub.update();
        }
    }

}
