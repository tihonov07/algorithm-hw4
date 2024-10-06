package org.example;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void managerFabric() {
        var manager = homeWork.managerFabric();
        var now = OffsetDateTime.now();
        var first = new Ticket(Ticket.PENSION, now);
        var second = new Ticket(Ticket.OTHER, now.plusDays(1));
        var third = new Ticket(Ticket.PENSION, now.plusDays(2));
        var five = new Ticket(Ticket.OTHER, now.plusDays(3));
        var six = new Ticket(Ticket.PENSION, now.plusDays(4));

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(five);
        manager.add(six);

        assertEquals(first, manager.next());
        assertEquals(third, manager.next());
        assertEquals(six, manager.next());
        assertEquals(second, manager.next());
        assertEquals(five, manager.next());
    }

    @Test
    void check() {
        List<Integer> expectedQueue = generateQueue(1, 10);
        List<String> pairs = generatePairs(expectedQueue);
        System.out.println(expectedQueue);
        assertEquals(expectedQueue, homeWork.check(pairs));
    }

    private List<String> generatePairs(List<Integer> expectedQueue) {
        List<String> pairs = new ArrayList<>();
        Function<Integer, Integer> map = (x) -> (x < 0 || x >= expectedQueue.size()) ? 0 : expectedQueue.get(x);

        for (int i = 0;
             i < expectedQueue.size(); i++) {
            pairs.add(String.format("%d:%d", map.apply(i - 1), map.apply(i + 1)));
        }
        Collections.shuffle(pairs);
        return pairs;
    }

    private List<Integer> generateQueue(int seed, int length) {
        return new Random(seed)
                .ints(1, length * 100)
                .limit(length)
                .boxed()
                .collect(Collectors.toList());
    }


}