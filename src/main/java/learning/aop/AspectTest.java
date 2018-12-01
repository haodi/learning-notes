package learning.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Spring AOP代理类继承、实现接口的方法时，需注意的细节
 * 总结：要从基类开始匹配，或者使用target直接匹配具体类
 *
 * @author lihaodi
 */
@Aspect
@Component
public class AspectTest {

    /**
     * 错误的切入点一，只能匹配到UserService实现类中的直接实现的方法
     */
    @Before("execution(* learning.aop.UserService.*())")
    public void test1() {
        System.out.println("before");
    }

    /**
     * 错误的切入点二，只能配到{@link UserServiceImpl#saveUser()}方法
     */
    @Before("execution(* learning.aop.UserServiceImpl.*())")
    public void test2() {
        System.out.println("before");
    }

    /**
     * 正确切入点一，能够匹配到{@link UserService#saveUser()}方法、{@link BaseService#save()} ()}方法
     */
    @Before("execution(* learning.aop.BaseService+.*())")
    public void test3() {
        System.out.println("before");
    }

    /**
     * 正确切入点二，能够匹配到{@link UserService#saveUser()}方法、{@link BaseService#save()} ()}方法
     */
    @Before("execution(* learning.aop.BaseService+.*())")
    public void test4() {
        System.out.println("before");
    }

    /**
     * 正确切入点三，能够匹配到{@link UserService#saveUser()}方法、{@link BaseService#save()} ()}方法
     */
    @Before("within(learning.aop.BaseService+)")
    public void test5() {
        System.out.println("before");
    }

    /**
     * 正确切入点四，能够匹配到{@link UserService#saveUser()}方法、{@link BaseService#save()} ()}方法
     */
    @Before("target(learning.aop.BaseService)")
    public void test6() {
        System.out.println("before");
    }

    /**
     * 正确切入点五，能够匹配到{@link UserService#saveUser()}方法、{@link BaseService#save()} ()}方法
     */
    @Before("target(learning.aop.UserServiceImpl) && execution(* *())")
    public void test7() {
        System.out.println("before");
    }
}
