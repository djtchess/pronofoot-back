package fr.pronofoot.metier;

import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;


@Slf4j
public class BaseMetier {
    protected <M, E> M mapToEntity(E origin, Class<M> entityClass) {
        if (origin == null) {
            return null;
        }
        M entity = new DozerBeanMapper().map(origin, entityClass);
        return entity;
    }

    protected <M, E> M map(E origin, Class<M> modelClass) {
        return origin != null ? new DozerBeanMapper().map(origin, modelClass) : null;


    }
}
