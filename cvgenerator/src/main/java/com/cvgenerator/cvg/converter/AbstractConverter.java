package com.cvgenerator.cvg.converter;

import java.io.IOException;

public abstract class AbstractConverter<D, E> {

    public abstract D toDto(E e) throws IOException;

    public abstract E toEntity(D d);

}
