package org.quantum.usm.service.user;

import org.quantum.usm.dto.UserDto;
import org.quantum.usm.entity.User;

public interface UserService {

	User create(UserDto user);

	User update(Long id, UserDto user);

	User get(Long id);

	void delete(Long id);
}
