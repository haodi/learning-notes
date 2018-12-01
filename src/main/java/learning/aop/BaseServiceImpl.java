package learning.aop;

/**
 * @author lihaodi
 */
public abstract class BaseServiceImpl implements BaseService {
    @Override
    public void save() {
        System.out.println("save");
    }
} 