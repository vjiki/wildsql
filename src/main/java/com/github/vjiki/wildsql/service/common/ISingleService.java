package com.github.vjiki.wildsql.service.common;

import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Transactional
public interface ISingleService<T, M> {

    List<T> getList(Pageable pageable);

    T getById(Long id);

    T update(M dto) throws ParseException;

    T create(M dto) throws ParseException;

    void delete(Long id);
}
