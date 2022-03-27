package work.week11.orderPubSub;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class OrderSub {

    public static void main(String[] args) {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.useSingleServer().setPassword("redis1234");

        final RedissonClient client = Redisson.create(config);
        RTopic topic = client.getTopic("order");
        try {
            int listener = topic.addListener(Order.class, (channel, msg) ->{
                System.out.println("channel:" + channel.toString());
                System.out.println("msg:" + msg);
            });
        } finally {
            client.shutdown();
        }
    }
}
