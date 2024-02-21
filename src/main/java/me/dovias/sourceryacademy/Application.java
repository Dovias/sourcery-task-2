package me.dovias.sourceryacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Application {

    public static <T> void execute(Integer number, Consumer<T> executor, List<Function<Integer, T>> functions, Function<Integer, T> callback, Function<Integer, T> fallback) {
        boolean shouldFallback = true;
        for (Function<Integer, T> function : functions) {
            T object = function.apply(number);
            if (object != null) {
                executor.accept(object);
                shouldFallback = false;
            }
        }
        if (shouldFallback) {
            executor.accept(fallback.apply(number));
        }
        executor.accept(callback.apply(number));
    }

    public static <T> void executeFor(int length, Consumer<T> executor, List<Function<Integer, T>> functions, Function<Integer, T> callback, Function<Integer, T> fallback) {
        for (int number = 1; number <= length; number++) {
            execute(number, executor, functions, callback, fallback);
        }
    }

    private static List<Function<Integer, String>> getFunctions() {
        List<Function<Integer, String>> functions = new ArrayList<>();
        functions.add((integer) -> integer % 3 == 0 ? "Fizz" : null);
        functions.add((integer) -> integer % 5 == 0 ? "Buzz" : null);
        functions.add((integer) -> integer % 7 == 0 ? "Jazz" : null);
        return functions;
    }

    public static void main(String[] args) {
        int length;
        try {
            length = Integer.parseUnsignedInt(args[0]);
        } catch (Exception exception) {
            return;
        }
        if (length < 1) {
            return;
        }

        /*
        This function internally loops n times, iterating fizz buzz functions and invokes them for the numbers,
        it uses Objects::toString as a fallback to provide number string if function returns null.
        Designed it to be as extensible and generic as possible
        (not stating that its very performant with all those heap function objects)
        */
        StringBuilder builder = new StringBuilder();
        executeFor(length, builder::append, getFunctions(), (integer) -> " ", Objects::toString);
        System.out.println(builder.substring(0, builder.length() - 1));
    }
}