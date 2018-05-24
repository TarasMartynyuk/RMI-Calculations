import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
interface SerializableFunction<T,R> extends Function<T,R>, Serializable {}

