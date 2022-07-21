package android.os;

import android.os.internal.DelayQueue;
import com.qsp.player.core.game.GameShower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public class MessageQueue {
    private static final Message POISON = new Message();
   private volatile boolean isQuited = false;
    private static final Logger logger = LoggerFactory.getLogger(GameShower.class);

    private DelayQueue<Message> queue = new DelayQueue<>();

    public void enqueueMessage(Message msg) {
        if(isQuited){
            msg.recycle();
            return;
        }
        queue.add(msg);
        msg.markInUse();
    }

    public Message next() {
        try {
            Message next = queue.take();
            if (next == POISON) {
                return null;
            }
            return next;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    void removeMessages(Handler h, int what, Object object) {
        if (h == null) {
            return;
        }
        queue.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                boolean shouldRemove = message.target == h && message.what == what && (object == null || message.obj == object);
                if(shouldRemove){
                    message.recycleUnchecked();
                }
                return shouldRemove;
            }
        });
    }

    void removeMessages(Handler h, Runnable r, Object object) {
        if (h == null || r == null) {
            return;
        }
        queue.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                boolean shouldRemove = message.target == h && message.callback == r && (object == null || message.obj == object);
                if(shouldRemove){
                    message.recycleUnchecked();
                }
                return shouldRemove;
            }
        });
    }
    void removeCallbacks(Handler h) {
        if (h == null) {
            return;
        }

        queue.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                boolean shouldRemove = message.target == h ;
                if(shouldRemove){
                    message.recycleUnchecked();
                }
                return shouldRemove;
            }
        });
    }

    void removeCallbacksAndMessages(Handler h, Object object) {
        if (h == null) {
            return;
        }

        queue.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                boolean shouldRemove = message.target == h && (object == null || message.obj == object);
                if(shouldRemove){
                    message.recycleUnchecked();
                }
                return shouldRemove;
            }
        });
    }

    void quit(boolean safe) {
        if (isQuited) {
            return;
        }
        isQuited = true;
        logger.debug("Quit, messages in queue: " + queue.size());
        if (!safe) {
            queue.clear();
        }
        // Tell looper to quit.
        POISON.when = System.currentTimeMillis();
        POISON.who = "KILLER";
        logger.debug("Feed poison:"+POISON);
        queue.offer(POISON);
    }
}