package br.com.cgr.lucrocerto.service.providers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.cgr.lucrocerto.entity.UserEntity;
import br.com.cgr.lucrocerto.model.Authorities;
import br.com.cgr.lucrocerto.model.Authority;
import br.com.cgr.lucrocerto.model.User;
import br.com.cgr.lucrocerto.model.UserSignup;
import br.com.cgr.lucrocerto.model.UserStatus;
import br.com.cgr.lucrocerto.repository.UserRepository;
import br.com.cgr.lucrocerto.service.ConfirmEncoder;
import br.com.cgr.lucrocerto.service.LCPasswordEncoder;
import br.com.cgr.lucrocerto.service.UserService;
import br.com.cgr.lucrocerto.service.exception.AlreadyRegisteredException;
import br.com.cgr.lucrocerto.utils.DateUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userR;

	@Autowired
	private LCPasswordEncoder pwdEncoder;

	@Autowired
	private ConfirmEncoder confirmEncoder;

	@Override
	public List<User> getUsers(Pageable pageable, Sort sort) {
		Page<UserEntity> usersE = userR.findAll(pageable);
		List<User> users = new ArrayList<User>(usersE.getSize());
		for (UserEntity userE : usersE) {
			User user = new User();
			BeanUtils.copyProperties(userE, user);
			users.add(user);
		}
		return users;
	}

	@Override
	public User createUser(UserSignup userSignup) {
		UserEntity userE = null;
		User user = userSignup.getUser();
		UserEntity userAux = userR.findOne(user.getEmail());
		if (userAux != null && !UserStatus.AWATING_CONFIRMATION.equals(userAux.getStatus())) {
			throw new AlreadyRegisteredException();
		} else if (user.getEmail().equals(userSignup.getConfirmEmail())
				&& user.getPwd().equals(userSignup.getConfirmPwd())) {
			userE = new UserEntity();
			user.setPwd(pwdEncoder.encode(user.getPwd()));
			Authority authority = new Authority();
			authority.setId(Authorities.USER.getId());
			List<Authority> authorities = new ArrayList<Authority>();
			authorities.add(authority);
			user.setAuthorities(authorities);
			user.setConfirmationKey(buildConfirmationKey(new Date(), user.getEmail()));
			BeanUtils.copyProperties(user, userE, "authorities");
			userE.setAuthorities(Authority.getEntities(user.getAuthorities()));
			userE.setStatus(UserStatus.AWATING_CONFIRMATION);
			userE = userR.save(userE);
		}
		return (userE != null ? user : null);
	}

	@Override
	public String buildConfirmationKey(Date date, String email) {
		return confirmEncoder.encode(getRawKey(email, date));
	}

	@Override
	public List<String> getNames(Pageable pageable) {
		Page<String> usersE = userR.findAllNames(pageable);
		List<String> users = new ArrayList<String>(usersE.getSize());
		for (String userE : usersE) {
			users.add(userE);
		}
		return users;
	}

	@Override
	public User getEmailAndPwd(String email) {
		User user = new User();
		String[] userE = userR.findByEmail(email);
		if (userE != null) {
			user.setName(userE[0]);
			user.setPwd(userE[1]);
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = null;
		UserEntity userE = userR.findOne(email);
		if (userE != null) {
			user = new User();
			BeanUtils.copyProperties(userE, user);
			user.setAuthorities(Authority.parse(userE.getAuthorities()));
		}
		return user;
	}

	@Override
	public String confirm(String email, String key) {
		String message = "Não foi possível confirmar o email, contate o nosso suporte.";
		UserEntity user = userR.findOne(email);
		if (UserStatus.AWATING_CONFIRMATION.equals(user.getStatus())) {
			for (int i = 0; i < 15; i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -i);
				CharSequence rawPassword = getRawKey(email, calendar.getTime());
				boolean matches = confirmEncoder.matches(rawPassword, key);
				if (matches) {
					message = null;
					user.setStatus(UserStatus.ACTIVE);
					userR.save(user);
					break;
				}
			}
		}
		return message;
	}

	private String getRawKey(String email, Date date) {
		return DateUtils.getString(date, DateUtils.dateFormat_YYYYMMDD).concat(email);
	}

}