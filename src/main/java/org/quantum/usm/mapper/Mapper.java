package org.quantum.usm.mapper;

public interface Mapper<Dto, Entity> {

	Dto toDto(Entity entity);

	Entity toEntity(Dto dto);

}
