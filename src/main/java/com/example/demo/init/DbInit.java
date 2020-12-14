package com.example.demo.init;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Privilege;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.repository.PrivilegeRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class DbInit implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PrivilegeRepository privilegeRepository;

	/*
	 * public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder)
	 * { this.userRepository = userRepository; this.passwordEncoder =
	 * passwordEncoder; }
	 */
	@Override
	public void run(String... args) {
		// Delete all
		this.userRepository.deleteAll();

		// Crete users
		User user = new User("user", passwordEncoder.encode("user"), "USER", "");
		User admin = new User("admin", passwordEncoder.encode("admin"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
		User manager = new User("manager", passwordEncoder.encode("manager"), "MANAGER", "ACCESS_TEST1");
		User read = new User("read", passwordEncoder.encode("read"), "READ", "ACCESS_READ");
		User write = new User("write", passwordEncoder.encode("write"), "WRITE", "ACCESS_READ,ACCESS_WRITE");
		User delete = new User("delete", passwordEncoder.encode("delete"), "DELETE",
				"ACCESS_READ,ACCESS_WRITE,ACCESS_DELETE");

		List<User> users = Arrays.asList(user, admin, manager, read, write, delete);

		// Save to db
		this.userRepository.saveAll(users);
		
		userCreation();
		harshad();
	}
	
	@Transactional
	public void harshad()
	{
		Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege createPrivilege = createPrivilegeIfNotFound("CREATE_PRIVILEGE");
		Privilege updatePrivilege = createPrivilegeIfNotFound("UPDATE_PRIVILEGE");
		Privilege deletePrivilege = createPrivilegeIfNotFound("DELETE_PRIVILEGE");
		createRoleIfNotFound("MANAGER", Arrays.asList(readPrivilege, createPrivilege,updatePrivilege,deletePrivilege));
		Role role = roleRepository.findByName("MANAGER");
		
		User user = new User();
		user.setUsername("harshad");
		user.setActive(1);
		user.setPassword(passwordEncoder.encode("harshad"));
		user.setRoles(Arrays.asList(role));
		//user.setEnabled(false);
		userRepository.save(user);

	}

	@Transactional
	public void userCreation() {

		Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege writePrivilege = createPrivilegeIfNotFound("CREATE_PRIVILEGE");

		createRoleIfNotFound("ADMIN", Arrays.asList(readPrivilege, writePrivilege));
		createRoleIfNotFound("USER", Arrays.asList(readPrivilege));
		createRoleIfNotFound("MANAGER", Arrays.asList(readPrivilege, writePrivilege));

		Role adminRole = roleRepository.findByName("USER");
		User user = new User();
		user.setUsername("user@gmail.com");
		user.setActive(1);
		
		user.setPassword(passwordEncoder.encode("user"));

		user.setRoles(Arrays.asList(adminRole));
		user.setEnabled(false);
		userRepository.save(user);

	}

	@Transactional
	Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege();
			privilege.setName(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}
}
