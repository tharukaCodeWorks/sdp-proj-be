package org.devsdp.wscms.auth.service.impl;

import org.devsdp.wscms.auth.dao.UserDao;
import org.devsdp.wscms.auth.model.Permission;
import org.devsdp.wscms.auth.model.Role;
import org.devsdp.wscms.auth.model.User;
import org.devsdp.wscms.auth.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleServiceImpl roleServiceImpl;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByEmail(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getPermissions().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	public List<User> getByPermission(String permission){
		return userDao.findByPermissionsName(permission);
	}

	@Override
	public boolean isUserExists(String email) {
		return userDao.existsByEmail(email);
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	@Override
    public User save(User user) {
		user = userDao.save(user);
        return user;
    }

    @Override
    public User setUserRole(long userRoleId, User user){
		Role role = roleServiceImpl.getById(userRoleId);
		Set<Permission> roles = new HashSet<>(role.getPermissions());
		user.setPermissions(roles);
		user.setRole(role);
		user = userDao.save(user);
		return  user;
	}
}
