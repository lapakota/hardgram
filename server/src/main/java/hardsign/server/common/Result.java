package hardsign.server.common;

import hardsign.server.common.mapper.StatusMapper;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Result<T> {
    private final T value;
    private final Status status;
    private static final Status DEFAULT_FAULT_STATUS = Status.Success;

    private static final StatusMapper statusMapper = new StatusMapper();

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

    public static <T> Result<T> of(Supplier<T> function) {
        return Result.of(function, DEFAULT_FAULT_STATUS);
    }

    public static <T> Result<T> fromOptional(Optional<T> optional, Status status) {
        return optional
                .map(Result::ok)
                .orElse(Result.fault(status));
    }

    public static <T> Result<T> of(T value, Status status) {
        if (value == null)
            return Result.fault(status);
        return Result.ok(value);
    }

    public static <T> Result<T> of(T value) {
        return Result.of(value, DEFAULT_FAULT_STATUS);
    }

    public <F> Result<F> then(Supplier<F> function, Status status) {
        if (isSuccess())
            return Result.of(function, status);
        return Result.fault(getStatus());
    }

    public <F> Result<F> then(Supplier<F> function) {
        return then(function, DEFAULT_FAULT_STATUS);
    }

    public <F> Result<F> then(Function<T, F> function, Status status) {
        if (isSuccess())
            return Result.of(() -> function.apply(get()), status);
        return Result.fault(getStatus());
    }

    public <F> Result<F> then(Function<T, F> function) {
        if (status != Status.Success)
            return then(function, status);
        return then(function, Status.Success);
    }

    public <F> F mapStatus(Function<Result<T>, F> statusMapper) {
        return statusMapper.apply(this);
    }

    public <F> ResponseEntity<F> buildResponseEntity(Function<T, F> modelMapper) {
        return then(() -> modelMapper.apply(get()))
                .mapStatus(statusMapper::map);
    }

    public boolean isSuccess() {
        return status == Status.Success;
    }

    public boolean isFailure() {
        return !isSuccess();
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