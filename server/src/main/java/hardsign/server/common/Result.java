package hardsign.server.common;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class Result<T> {
    private final T value;
    private final Status status;
    private final Status DEFAULT_STATUS = Status.Success;

    private Result(T value, Status status) {
        this.value = value;
        this.status = status;
    }

    public static <T> Result<T> ok(T value) {
        Objects.requireNonNull(value);
        return new Result<>(value, Status.Success);
    }

    public static <T> Result<T> fault(Status status) {
        if (status == Status.Success)
            throw new RuntimeException();
        return new Result<>(null, status);
    }

    public static <T> Result<T> of(Supplier<T> function, Status status) {
        try {
            return Result.ok(function.get());
        } catch (Exception e) {
            return Result.fault(status);
        }
    }

    public static <T> Result<T> of(T value, Status status) {
        if (value == null)
            return Result.fault(status);
        return Result.ok(value);
    }

    public <F> Result<F> then(Supplier<F> function, Status status) {
        if (isSuccess())
            return Result.of(function, status);
        return Result.fault(getStatus());
    }

    public <F> Result<F> then(Function<T, F> function, Status status) {
        if (isSuccess())
            return Result.of(() -> function.apply(get()), status);
        return Result.fault(getStatus());
    }

    public <F> Result<F> then(Function<T, F> function) {
        var currentStatus = getStatus();
        if (currentStatus != Status.Success)
            return then(function, currentStatus);
        return then(function, DEFAULT_STATUS);
    }

    public <F> F mapStatus(Function<Result<T>, F> statusMapper) {
        return statusMapper.apply(this);
    }

    public boolean isSuccess() {
        return status == Status.Success;
    }

    public T get() {
        if (isSuccess())
            return value;
        throw new RuntimeException("No value present");
    }

    public Status getStatus() {
        return status;
    }
}