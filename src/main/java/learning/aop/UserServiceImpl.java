package learning.aop;

import org.springframework.stereotype.Service;

/**
 * @author lihaodi
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Override
    public void saveUser() {
        System.out.println("save user");
    }
}  