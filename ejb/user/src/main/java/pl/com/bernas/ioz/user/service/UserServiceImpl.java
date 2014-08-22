package pl.com.bernas.ioz.user.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.com.bernas.ioz.user.dao.UserDao;
import pl.com.bernas.ioz.user.domain.UserDto;
import pl.com.bernas.ioz.user.domain.mapper.Dozer;
import pl.com.bernas.ioz.user.model.RoleEntity;
import pl.com.bernas.ioz.user.model.UserEntity;
import pl.com.bernas.ioz.user.service.UserService;
import pl.com.bernas.tarnica.user.model.User;

@Stateless(name = "userService")
public class UserServiceImpl implements UserService<User> {

	@EJB(beanName = "userDaoDirty")
	private UserDao<UserEntity> userDao;

	@EJB
	private Dozer dozer;

	@PostConstruct
	public void init() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User en = this.findByUserName("KirkoR");

		if (en != null) {
			return;
		}
		RoleEntity role = new RoleEntity();
		role.setName("ADMIN");
		role.setCreationDate(new Timestamp(new Date().getTime()));

		Set<RoleEntity> roles = new HashSet<RoleEntity>();
		roles.add(role);

		UserEntity entity = new UserEntity();
		entity.setUsername("KirkoR");
		entity.setPassword("150b8d563acdef89a3465e1032997e754a102d90b96b86bb42a79973d6f6987a"); // 123qwe
		entity.setRoles(roles);
		entity.setEmail("gbernas@gmail.com");
		entity.setCreationDate(new Timestamp(new Date().getTime()));

		this.register(entity);
	}

	@Override
	public User findByUserName(String userName) {
		UserEntity entity = userDao.findByUserName(userName);
		if (entity != null) {
			return dozer.getMapper().map(entity, UserDto.class);
		} else {
			return null;
		}
	}

	@Override
	public User findById(Long pk) {
		UserEntity entity = userDao.findById(pk);
		return dozer.getMapper().map(entity, UserDto.class);
	}

	@Override
	public Long register(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		UserEntity entity = dozer.getMapper().map(user, UserEntity.class);

		return userDao.insert(entity);
	}

	@Override
	public void online(User userOnline) {
		UserEntity user = this.userDao.findByUserName(userOnline.getUsername());
		user.setOnline(true);

		this.userDao.update(user);
	}

	@Override
	public void offline(User user) {
		UserEntity userEntity = this.userDao.findById(user.getId());
		userEntity.setOnline(false);

		this.userDao.update(userEntity);
	}

}
