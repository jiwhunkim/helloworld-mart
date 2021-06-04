package com.helloworld.config.mapper

import org.mapstruct.CollectionMappingStrategy
import org.mapstruct.MapperConfig
import org.mapstruct.NullValueMappingStrategy

@MapperConfig(
    componentModel = "spring",
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
interface MapperSpringConfig {
}