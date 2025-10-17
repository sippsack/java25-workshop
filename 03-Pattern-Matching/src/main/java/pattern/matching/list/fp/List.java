package pattern.matching.list.fp;

import java.util.Objects;

public sealed interface List<T> {
    record Cons<T>(T value, List<T> next) implements List<T> {
        @Override
        public String toString() {
            return "[%s] --> %s".formatted(value, next);
        }
    }

    final class Nil<T> implements List<T> {
        @Override
        public String toString() {
            return "NIL";
        }
    }

    static List NIL = new Nil<>();

    public static void main(String[] args) {
        List<Integer> list = of(1, 2, 3);
        list = new Cons<>(1, new Cons<>(2, new Cons<>(3, List.NIL)));
        System.out.println(list);
        System.out.println(head(list));
        System.out.println(tail(list));
        System.out.println(contains(5, list));
        System.out.println(contains(1, list));
    }

    @SafeVarargs
    static <T> List<T> of(T... values) {
        if (values.length == 0) return List.NIL;

        List<T> current = List.NIL;
        for (int i = values.length - 1; i >= 0; i--) {
            current = new Cons<>(values[i], current);
        }
        return current;
    }

    static <T> T head(List<T> list) {
        return switch(list) {
            case List.Nil<T> _ -> throw new IndexOutOfBoundsException("Liste ist leer");
            case Cons<T>(T value, _) -> value;
        };
    }

    static <T> List<T> tail(List<T> list) {
        return switch (list) {
            case List.Nil<T> e -> e;
            case Cons<T>(_, var next) -> next;
        };
    }

    static <T> boolean contains(T value, List<T> list) {
        return switch (list) {
            case List.Nil _ -> false;
            case Cons<T>(T v, _) when Objects.equals(v, value) -> true;
            case Cons<T>(_, var tail) -> contains(value, tail);
        };
    }
}
