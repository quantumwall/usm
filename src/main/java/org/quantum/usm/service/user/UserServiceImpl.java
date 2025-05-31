package org.quantum.usm.service.user;

import org.quantum.usm.dto.UserDto;
import org.quantum.usm.entity.User;
import org.quantum.usm.exception.EntityAlreadyExistsException;
import org.quantum.usm.exception.EntityNotFoundException;
import org.quantum.usm.mapper.user.UserMapper;
import org.quantum.usm.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	@Transactional
	public User create(UserDto userDto) {
		if (userRepository.findByUsername(userDto.username()).isPresent()) {
			throw new EntityAlreadyExistsException("User %s already exists".formatted(userDto.username()));
		}
		return userRepository.save(userMapper.toEntity(userDto));
	}

	@Override
	@Transactional
	public User update(Long id, UserDto user) {
		User userFromDb = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User %d not found".formatted(id)));
		userFromDb.setFirstname(user.firstname());
		userFromDb.setLastname(user.lastname());
		return userFromDb;
	}

	@Override
	public User get(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User %d not found".formatted(id)));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User %d not found".formatted(id)));
		userRepository.deleteById(id);
	}

}
