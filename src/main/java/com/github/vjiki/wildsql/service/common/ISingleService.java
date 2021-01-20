package com.github.vjiki.wildsql.service.common;

import org.springframework.data.domain.Pageable;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

public interface ISingleService<E, M> {

    List<E> getList(final Pageable pageable);

    E getById(final Long id) throws NoSuchElementException;

    E preUpdate(final Long id, final String name);

    E update(final M dto) throws ParseException;

    E create(final M dto) throws ParseException;

    void delete(final Long id);
}
