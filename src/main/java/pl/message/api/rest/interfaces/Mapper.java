package pl.message.api.rest.interfaces;

public interface Mapper<E,D> {

    D getDTO(E entity);
    E getEntity(D dto);
}
