package club.intworld.DataStructure.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Component
@Slf4j
public class RecursionTest implements CommandLineRunner {
    Map map = new HashMap();

    @Override
    public void run(String... args) throws Exception {
        int taijie = 10;
        long start = System.currentTimeMillis();
        long result = testTaiJie(taijie);
        long end = System.currentTimeMillis();
        log.info("{}个台阶，共有{}种走法，计算用时{}ms",taijie,result,end-start);

        start = System.currentTimeMillis();
        result = testTaiJie2(taijie);
        end = System.currentTimeMillis();
        log.info("{}个台阶，共有{}种走法，计算用时{}ms",taijie,result,end-start);
        log.info("Map中有{}个记录",map.size());
    }

    private long testTaiJie(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return testTaiJie(n-2)+testTaiJie(n-1);
    }

    private long testTaiJie2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (map.containsKey(n)) {
            return (int) map.get(n);
        }
        long result = testTaiJie(n-2)+testTaiJie(n-1);
        map.put(n, result);
        return result;
    }
}
