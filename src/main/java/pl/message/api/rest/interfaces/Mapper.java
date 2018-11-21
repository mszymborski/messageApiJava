package pl.message.api.rest.interfaces;

import pl.message.api.rest.exceptions.NotFoundUserException;

public interface Mapper<E,D> {

    D getDTO(E entity);
    E getEntity(D dto);
}
