package club.intworld.DataStructure.list;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class NodeTest implements CommandLineRunner {
    Node head;

    @Override
    public void run(String... args) throws Exception {
        testReverse();
    }

    private void testDelete() {
        buildList(2);
        head.print();
        head.deleteNodeAtIndex(1);
        head.deleteNodeAtIndex(1);
        head.print();
    }

    private void testLoop() {
        buildList(3);

        head.hasLoop();
        head.print();
    }

    private void testInsert() {
        buildList(3);
        head.print();
        head.insertDataAt(1, 201);
        head.print();
    }

    private void testReverse() {
        buildList(5);
        head.print();
        Node reverse = head.reverse(3,3);
        reverse.print();
    }

    private void buildList(int n) {
        log.info("run node builder");
        head = new Node<>(new Random().nextInt(100));
        for (int i = 0; i < n-1; i++) {
            head.addData(new Random().nextInt(100));
        }
    }
}
