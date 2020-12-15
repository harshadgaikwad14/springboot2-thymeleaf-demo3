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

		createPrivilege();
		superUser();
		instituteAdminUser();

	}

	public void createPrivilege() {

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("COUNTRY_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("COUNTRY_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("COUNTRY_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("COUNTRY_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("STATE_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("STATE_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("STATE_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("STATE_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN", Arrays.asList(createPrivilegeIfNotFound("CITY_READ_PRIVILEGE"),
				createPrivilegeIfNotFound("CITY_CREATE_PRIVILEGE"), createPrivilegeIfNotFound("CITY_UPDATE_PRIVILEGE"),
				createPrivilegeIfNotFound("CITY_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("COMPANY_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("COMPANY_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("COMPANY_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("COMPANY_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("BOARD_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("BOARD_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("BOARD_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("BOARD_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("STANDARD_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("STANDARD_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("STANDARD_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("STANDARD_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("COURSE_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("COURSE_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("COURSE_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("COURSE_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("SUBJECT_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("SUBJECT_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("SUBJECT_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("SUBJECT_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("CHAPTER_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("CHAPTER_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("CHAPTER_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("CHAPTER_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("TOPIC_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("TOPIC_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("TOPIC_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("TOPIC_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("SUBTOPIC_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("SUBTOPIC_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("SUBTOPIC_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("SUBTOPIC_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("ACADEMICYEAR_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("ACADEMICYEAR_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("ACADEMICYEAR_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("ACADEMICYEAR_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("INSTITUTE_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("INSTITUTE_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("INSTITUTE_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("INSTITUTE_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("TEACHER_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("TEACHER_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("TEACHER_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("TEACHER_DELETE_PRIVILEGE")));

		createRoleIfNotFound("INSTITUTEADMIN",
				Arrays.asList(createPrivilegeIfNotFound("TEACHER_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("TEACHER_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("TEACHER_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("TEACHER_DELETE_PRIVILEGE")));

		createRoleIfNotFound("SUPERADMIN",
				Arrays.asList(createPrivilegeIfNotFound("STUDENT_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("STUDENT_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("STUDENT_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("STUDENT_DELETE_PRIVILEGE")));

		createRoleIfNotFound("INSTITUTEADMIN",
				Arrays.asList(createPrivilegeIfNotFound("STUDENT_READ_PRIVILEGE"),
						createPrivilegeIfNotFound("STUDENT_CREATE_PRIVILEGE"),
						createPrivilegeIfNotFound("STUDENT_UPDATE_PRIVILEGE"),
						createPrivilegeIfNotFound("STUDENT_DELETE_PRIVILEGE")));
	}

	@Transactional
	public void superUser() {

		Role superAdmin = roleRepository.findByName("SUPERADMIN");
		Role instituteAdmin = roleRepository.findByName("INSTITUTEADMIN");

		User user = new User();
		user.setUsername("superuser");
		user.setActive(1);
		user.setPassword(passwordEncoder.encode("superuser"));
		user.setRoles(Arrays.asList(superAdmin, instituteAdmin));

		userRepository.save(user);

	}

	@Transactional
	public void instituteAdminUser() {

		Role instituteAdmin = roleRepository.findByName("INSTITUTEADMIN");
		User user = new User();
		user.setUsername("adminuser");
		user.setActive(1);
		user.setPassword(passwordEncoder.encode("adminuser"));
		user.setRoles(Arrays.asList(instituteAdmin));

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
