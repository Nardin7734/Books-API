package com.interact.mappers;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ObjectMapper
{
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public <O, D> D parseObject(O origin, Class<D> destination )
    {
        return mapper.map( origin, destination );
    }

    public <O, D>List<D> parseList( List<O> origin, Class<D> destination )
    {
        List<D> destinationObjs = new ArrayList<>();

        for( Object o : origin )
        {
            destinationObjs.add( mapper.map( o, destination) );
        }

        return destinationObjs;
    }
}
