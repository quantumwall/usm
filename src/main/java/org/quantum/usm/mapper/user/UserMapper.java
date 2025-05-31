package org.quantum.usm.mapper.user;

import org.quantum.usm.dto.UserDto;
import org.quantum.usm.entity.User;
import org.quantum.usm.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDto, User> {

	@Override
	public UserDto toDto(User entity) {
		return new UserDto(entity.getId(),
				entity.getUsername(),
				entity.getFirstname(),
				entity.getLastname());
	}

	@Override
	public User toEntity(UserDto dto) {
		User user = new User();
		user.setId(dto.id());
		user.setUsername(dto.username());
		user.setFirstname(dto.firstname());
		user.setLastname(dto.lastname());
		return user;
	}

}
