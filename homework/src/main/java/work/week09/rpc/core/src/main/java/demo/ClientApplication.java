package demo;

import demo.pojo.User;
import demo.proxy.RpcByteBuddy;
import demo.proxy.RpcClient;
import demo.proxy.RpcClientJdk;
import demo.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientApplication {

    public static void main(String[] args) {

        RpcClient client = new RpcClientJdk();
        UserService userService = client.create(UserService.class, "http://localhost:8080/");
        User user = userService.findById(1);
        if (user == null) {
            log.info("Clint service invoke Error");
            return;
        }
        log.info("find user id=1 from server: {}", user.getName());

        RpcClient buddy = new RpcByteBuddy();
        UserService userService1 = buddy.create(UserService.class, "http://localhost:8080/");
        User user1 = userService1.findById(1992129);
        if (user1 == null) {
            log.info("Clint service invoke Error");
            return;
        }
        log.info(String.format("find order name={}", user1.getName()));

    }

}
