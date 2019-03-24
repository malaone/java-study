import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xulifei
 * @Date: 15:48 2019/1/22
 * @Description:
 */
public class MyTest {

    @Test
    public void test2() {
        int i=0,k=10;
        while (k-- > 0) {
            i = i++;
        }
        System.out.println(i);//0
        System.out.println(System.nanoTime());
    }

    @Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 123);

//        String a = (String) map.get("a");
        String b = (String) map.get("b");
        System.out.println(map.get("b"));
        System.out.println(b);
    }
}
