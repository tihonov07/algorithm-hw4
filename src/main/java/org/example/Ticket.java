package org.example;

import lombok.Getter;

import java.time.OffsetDateTime;

/**
 * Можно изменять по своему усмотрению, не нарушая правила приоритезации очереди
 */
@Getter
public class Ticket implements Comparable<Ticket> {

    private static int idSeq;
    public static final String PENSION = "pension";
    public static final String OTHER = "other";

    /**
     * Автогенерация id
     */
    int id = ++idSeq;

    /**
     * Приоритеты типов:
     * 1) pension
     * 2) любые другие
     */
    private final String type;

    /**
     * Приоритет для ранней регистрации
     */
    private final OffsetDateTime registerTime;

    public Ticket(String type, OffsetDateTime registerTime) {
        this.type = type;
        this.registerTime = registerTime;
    }

    @Override
    public int compareTo(Ticket o) {
        if (o.getType().equals(PENSION)) {
            return this.getType().equals(PENSION) ? this.getRegisterTime().compareTo(o.getRegisterTime()) : 1;
        }
        return this.getType().equals(PENSION) ? -1 : this.getRegisterTime().compareTo(o.getRegisterTime());
    }

    @Override
    public String toString() {
        return "Ticket type " + type + "; date " + registerTime;
    }
}
