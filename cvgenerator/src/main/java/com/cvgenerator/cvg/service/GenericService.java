package com.cvgenerator.cvg.service;

import java.io.IOException;
import java.util.List;

public interface GenericService<D, ID> {
    D save(D d);

    D findById(ID id) throws IOException;

    List<D> findAll() throws IOException;

    void deleteById(ID id);
}
