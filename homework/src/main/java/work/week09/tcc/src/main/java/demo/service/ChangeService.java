package demo.service;

import demo.pojo.Change;
import org.dromara.hmily.annotation.Hmily;

public interface ChangeService {

    @Hmily
    boolean exChange(Change change);
}
