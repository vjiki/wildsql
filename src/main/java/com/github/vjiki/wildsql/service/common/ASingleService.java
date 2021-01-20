package com.github.vjiki.wildsql.service.common;

import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.exception.MyException;
import com.github.vjiki.wildsql.model.repositories.AbstractEntity;
import com.github.vjiki.wildsql.model.repositories.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
public abstract class ASingleService<E extends AbstractEntity,M, R extends AbstractRepository<E>> implements ISingleService<E,M> {

    protected final R repository;

    @Autowired
    public ASingleService(R repository) {
        this.repository = repository;
    }

    protected abstract Class<E> getEClass();

    // @TODO: make converter also parametrized
    @Autowired
    private DtoConverter dtoConverter;

    @Transactional(readOnly = true)
    @Override
    public List<E> getList(Pageable pageable) {

        Page<E> entities = repository.findAll(pageable);

        if (pageable.getPageNumber() > entities.getTotalPages()) {
            int pageNumber = pageable.getPageNumber();
            int totalNumber = entities.getTotalPages();
            final String message = "Page not found: "  + pageNumber + " > " + totalNumber;
            throw new MyException(message);
        }

        if (entities.isEmpty())
        {
            final String message = "Page is empty";
            throw new EntityNotFoundException(message);
        }

        return entities.getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public E getById(final Long id) throws NoSuchElementException {return repository.findById(id).orElseThrow();}

    @Override
    @Transactional
    public void delete(final Long id) {
        Optional<E> optionalE = repository.findById(id);

        if (optionalE.isEmpty()) {
            final String message = optionalE + " id " + id.toString();
            throw new EntityNotFoundException(message);
        }

        repository.delete(optionalE.get());
    }

    // write

    @Transactional
    @Override
    public E create(M dto) throws ParseException {

        E e = dtoConverter.simpleConvert(dto, getEClass());

        E uniqueE = repository.findByName(e.getName());
        if (uniqueE != null) {
            final String message = getEClass() + " : name "
                    + "'" + e.getName() + "'"
                    + " already exists with id: "
                    + "'" + uniqueE.getId() + "'";
            throw new DataIntegrityViolationException(message);
        }
        return repository.save(e);
    }


    @Override
    @Transactional
    public E preUpdate(final Long id, final String name) {

        E existingE = repository.getOne(id);

        E uniqueNameE = repository.findByName(name);
        if (uniqueNameE != null) {
            final String message = getEClass() + ": name "
                    + "'" + name + "'"
                    + " already exists with id: "
                    + "'" + uniqueNameE.getId() + "'";
            throw new DataIntegrityViolationException(message);
        }

        return existingE;
    }

}