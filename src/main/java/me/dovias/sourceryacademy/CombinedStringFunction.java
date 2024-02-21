package me.dovias.sourceryacademy;

import java.util.function.Function;

public class CombinedStringFunction<T> implements Function<T, String> {
    private final Function<T, String> function1;
    private final Function<T, String> function2;
    
    public CombinedStringFunction(Function<T, String> function1, Function<T, String> function2) {
        this.function1 = function1;
        this.function2 = function2;
    }

    @Override
    public String apply(T object) {
        String function1Output = function1.apply(object);
        String function2Output = function2.apply(object);

        return function1Output != null && function2Output != null ? function1Output + function2Output : null;
    }
}
