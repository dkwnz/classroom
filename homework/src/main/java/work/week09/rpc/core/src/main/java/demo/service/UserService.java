package demo.service;


import demo.pojo.User;

/**
 * @author lw
 */
public interface UserService {

    /**
     * find by id
     * @param id id
     * @return user
     */
    User findById(Integer id);
}
