package me.dovias.sourceryacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Application {

    public static <T> void execute(Integer number, Consumer<T> executor, List<Function<Integer, T>> functions, Function<Integer, T> fallback) {
        for (Function<Integer, T> function : functions) {
            T object = function.apply(number);
            if (object != null) {
                executor.accept(object);
                return;
            }
        }
        executor.accept(fallback.apply(number));
    }

    public static <T> void executeFor(int length, Consumer<T> executor, List<Function<Integer, T>> functions, Function<Integer, T> fallback) {
        for (int number = 1; number <= length; number++) {
            execute(number, executor, functions, fallback);
        }
    }

    private static List<Function<Integer, String>> getFunctions() {
        List<Function<Integer, String>> functions = new ArrayList<>();

        Function<Integer, String> fizz = (integer) -> integer % 3 == 0 ? "Fizz" : null;
        Function<Integer, String> buzz = (integer) -> integer % 5 == 0 ? "Buzz" : null;
        Function<Integer, String> jazz = (integer) -> integer % 7 == 0 ? "Jazz" : null;
        Function<Integer, String> fizzBuzz = new CombinedStringFunction<>(fizz, buzz);
        Function<Integer, String> buzzJazz = new CombinedStringFunction<>(buzz, jazz);
        Function<Integer, String> fizzBuzzJazz = new CombinedStringFunction<>(fizzBuzz, jazz);

        functions.add(fizzBuzzJazz);
        functions.add(fizzBuzz);
        functions.add(buzzJazz);
        functions.add(fizz);
        functions.add(buzz);
        functions.add(jazz);
        return functions;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            return;
        }
        int n;
        try {
            n = Integer.parseUnsignedInt(args[0]);
        } catch (NumberFormatException exception) {
            return;
        }

        /*
        This function internally loops n times, iterating fizz buzz functions and invokes them for the numbers,
        it uses Objects::toString as a fallback to provide number string if function returns null.
        Designed it to be as extensible and generic as possible
        (not stating that its very performant with all those heap function objects)
        */
        StringBuilder builder = new StringBuilder();
        executeFor(n, (string) -> builder.append(string).append(' '), getFunctions(), Objects::toString);
        System.out.println(builder.substring(0, builder.length() - 1));
    }
}