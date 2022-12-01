package com.ecommerce.core.utilities;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public final class MapperUtil {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    private MapperUtil() {
    }

    /**
     * @param <D>      type of result object.
     * @param <T>      type of source object.
     * @param entity   entity that needs to be mapped.
     * @param outClass class of result object.
     * @return new object of <code>outClass</code> type.
     */
    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    /**
     * @param entityList list of entities needs to be mapped.
     * @param outCLass   class of result list element.
     * @param <D>        type of objects in result list.
     * @param <T>        type of entity in entity list.
     * @return list of mapped object.
     */
    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }
}
