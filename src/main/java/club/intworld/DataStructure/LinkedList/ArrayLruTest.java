package club.intworld.DataStructure.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.*;

@Component
@Slf4j
public class ArrayLruTest implements CommandLineRunner {
    private int[] lru;
    private int volume;
    private int used;
    private int latest;

    @Override
    public void run(String... args) {
        volume = 20;
        createArrayForLru(volume);
    }

    private void createArrayForLru(int size) {
        lru = new int[size];
        used = 0;
        latest = -1;
        for (int i = 0; i < 10; i++) {
            lru[i] = new Random().nextInt(100);
            latest ++;
            used ++;
        }
        addDataToArray(101);
        for (int i = 11; i < volume; i++) {
            lru[i] = new Random().nextInt(100);
            latest ++;
            used ++;
        }

        printArray();
        latest = 15;
        addDataToArray(101);

        printArray();
    }

    /* latest表示最新的数据位置，latest前面的位置为次新，以此类推
    *  当数组中无相同数据，且未填满时，直接在latest+1位置增加数据，latest=latest+1
    *  当数组中无相同数据，但已填满时，直接将latest+1位置修改为新的数据，latest=latest+1。原latest+1位置为最老的数据
    *  当数组中有相同数据，则把该数据提到latest位置，相应的部分数据做搬移
    * */
    private void addDataToArray(int data) {
        if (lru == null) {
            lru = new int[volume];
            used = 0;
            latest = -1;
        }

        int index = findDataInArray(data);
        if (index == -1) {
            if (used < volume) {
                log.info("未找到数据({})，且缓存未满，在{}位置添加数据",data,latest+1);
                lru[latest+1] = data;
                latest ++;
                used ++;
            } else {
                if (latest < (volume-1)) {
                    log.info("未找到数据({})，但缓存已满，在{}位置添加数据",data,latest+1);
                    lru[latest+1] = data;
                    latest ++;
                } else {
                    log.info("未找到数据({})，但缓存已满，在{}位置添加数据",data,0);
                    lru[0] = data;
                    latest = 0;
                }
            }
        } else {
            if (index <= latest) {
                if (latest - index >= 0) {
                    arraycopy(lru, index + 1, lru, index, latest - index);
                }
                lru[latest] = data;
            } else {
                if (volume - 1 - index >= 0) {
                    arraycopy(lru, index + 1, lru, index, volume - 1 - index);
                }
                lru[volume-1] = lru[0];
                if (latest >= 0) {
                    arraycopy(lru, 1, lru, 0, latest);
                }
                lru[latest] = data;
            }
        }
    }

    private void printArray() {
        log.info("数组容量为：{}，共有 {} 个有效数据",volume,used);
        log.info("当前最新的数据在 {} 位置，数据值为 {}", latest, lru[latest]);
        for (int i = 0; i < volume; i++) {
            log.info("位置 {}：{}",i, lru[i]);
        }
    }

    /* 由于LRU数组中的数据具有先后顺序，即latest为最新，之前的为次新
    *  所以查询采用倒查的方式进行，即查询由新到旧的数据 */
    private int findDataInArray(int data) {
        int index = -1;
        for (int i = latest; i > -1; i--) {
            if (lru[i] == data) {
                return i;
            }
        }
        for (int i = volume-1; i > latest ; i--) {
            if (lru[i] == data) {
                return i;
            }
        }
        return index;
    }
}
