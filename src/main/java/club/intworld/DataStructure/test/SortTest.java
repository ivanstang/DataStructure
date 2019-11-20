package club.intworld.DataStructure.test;

import club.intworld.DataStructure.list.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class SortTest implements CommandLineRunner {
    private int[] array;
    private int n;
    private Node head;
    private int k = 0;

    @Override
    public void run(String... args) throws Exception {
//        createArray(100000);
//        int[] array2 = array.clone();
//        long start = System.currentTimeMillis();
//        bubbleSort(array,n);
//        long end = System.currentTimeMillis();
//        log.info("冒泡排序计算用时{}ms",end-start);
//
//        start = System.currentTimeMillis();
//        insertionSort(array2,n);
//        end = System.currentTimeMillis();
//        log.info("插入排序计算用时{}ms",end-start);

//        createArrayLinked(10);
//        head.print();
//
//        bubbleSortLinked(head);
//
//        head.print();

        createArray(6);
        int[] array2 = array.clone();
        selectSort(array);
        log.info("选择交换共交换了{}次",k);

        k = 0;
        insertionSort(array2,n);
        log.info("插入交换共交换了{}次",k);
    }

    private void createArray(int n) {
        this.n = n;
        this.array = new int[n];
        int data;
        for (int i = 0; i < n; i++) {
            data = new Random().nextInt(10000);
            array[i] = data;
            log.info("" + data);
        }
    }

    private void createArrayLinked(int n) {
        Node temp = null;
        for (int i = 0; i < n; i++) {
            if (head == null) {
                head = new Node(new Random().nextInt(10000));
                temp = head;
            } else {
                temp.next = new Node(new Random().nextInt(10000));
                temp = temp.next;
            }
        }
    }

    /* 冒泡排序，a表示数组，n表示数组大小
     空间复杂度：O(1)
     时间复杂度：最好O(n)，最坏O(n2)，平均O(n2)
     排序是否稳定：是
    */
    public void bubbleSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j+1]) { // 交换
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;  // 表示有数据交换
                }
            }
            if (!flag) break;  // 没有数据交换，提前退出
        }
    }

    /* 冒泡排序，head表示链表头结点
     空间复杂度：O(1)
     时间复杂度：最好O(n)，最坏O(n2)，平均O(n2)
     排序是否稳定：是
    */
    public void bubbleSortLinked(Node head) {
        if (head.next == null) return;

        Node temp = head;
        Node after;
        Node before;
        boolean flag = false;
        while (temp.next != null) {
            before = temp;
            after = temp.next;
            if ((int)after.data < (int)before.data) {
                int tmp = (int)before.data;
                before.data = after.data;
                after.data = tmp;
                flag = true;
                k++;
            }
            temp = temp.next;
        }
        if (flag) bubbleSortLinked(head);
    }


    /* 插入排序，a表示数组，n表示数组大小
     空间复杂度：O(1)
     时间复杂度：最好O(n)，最坏O(n2)，平均O(n2)
     排序是否稳定：是
    */
    public void insertionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];  // 数据移动
                    k ++;
                } else {
                    break;
                }
            }
            a[j+1] = value; // 插入数据
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                    k ++;
                }
            }
            // 交换
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            k = k++;
        }
    }

    public void insertionSortLinked(Node head) {
        if (head.next == null) return;

        Node temp = head;
        while (temp.next != null) {

        }

    }
}
