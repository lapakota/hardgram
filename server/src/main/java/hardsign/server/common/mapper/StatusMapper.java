package hardsign.server.common.mapper;

import hardsign.server.common.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    public <T> ResponseEntity<T> map(Result<T> result) {
        return switch (result.getStatus()) {
            case Success -> ResponseEntity.ok(result.get());
            case AuthError -> ResponseEntity.status(401).build();
            case ServerError -> ResponseEntity.internalServerError().build();
            case IncorrectArguments -> ResponseEntity.badRequest().build();
            case NotFound -> ResponseEntity.notFound().build();
        };
    }
}