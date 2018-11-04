package pl.message.api.rest.interfaces;

import org.springframework.http.ResponseEntity;

public interface CUDController<T> {

    ResponseEntity<?> create(T model);
    ResponseEntity<?> update(Long id, T model);
    ResponseEntity<?> delete(Long id);

}
