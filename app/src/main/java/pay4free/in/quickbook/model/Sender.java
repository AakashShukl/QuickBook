package pay4free.in.quickbook.model;

import pay4free.in.quickbook.Notification.Notification;

/**
 * Created by AAKASH on 09-03-2018.
 */

public class Sender {
public String to;
    public Notification notification;

    public Sender() {
    }

    public Sender(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }
}
