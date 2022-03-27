package work.week11.orderPubSub;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class Publisher {
    public static void main(String[] args) {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.useSingleServer().setPassword("redis1234");

        final RedissonClient client = Redisson.create(config);
        RTopic topic = client.getTopic("order");
        try {
            long receivedCount = topic.publish(new Order("1","test"));
        } finally {
            client.shutdown();
        }
    }
}
