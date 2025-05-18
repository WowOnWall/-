package com.imyc.SBAP;

import java.util.*;

import com.imyc.SBAP.Http.privilege.dao.Privilege;
import com.imyc.SBAP.Http.privilege.dao.repository.PrivilegeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.imyc.SBAP.Http.role.dao.Role;
import com.imyc.SBAP.Http.role.dao.repository.RoleRepository;
import com.imyc.SBAP.Http.user.dao.User;
import com.imyc.SBAP.Http.user.dao.repository.UserRepository;

@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private RoleRepository rolesRepository;

	@Autowired
	private UserRepository usersRepository;

	@Autowired
    private PrivilegeRepository privilegeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

        Logger logger = LoggerFactory.getLogger(DemoCommandLineRunner.class);


        // 判断用户是否已存在（比如通过用户名或邮箱判断）
        Optional<User> existingUser = usersRepository.findByUsername("admin");
        if (existingUser.isPresent()) {
            // 如果已经有 admin 用户，就不再执行初始化
            logger.info("Admin 用户已存在，跳过初始化逻辑");
            return;
        }

		Date date = new Date();
		
		User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setName("Admin");
        user.setEmail("test@test.com");
        user.setDisabled(false);
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        usersRepository.save(user);

        Role role = new Role();
        role.setAdmin(true);
        role.setName("ADMIN");
        role.setCreatedAt(date);
        role.setUpdatedAt(date);
        rolesRepository.save(role);

        Role role2 = new Role();
        role2.setAdmin(false);
        role2.setName("USER");
        role2.setCreatedAt(date);
        role2.setUpdatedAt(date);
        rolesRepository.save(role2);

        user.setRoles(Set.copyOf(Arrays.asList(role)));
        usersRepository.save(user);

        String[] priilegeNameList = {"Dashboard_INDEX", "Role_INDEX", "Role_CREATE",
                                    "Role_UPDATE", "Role_DELETE", "User_INDEX", "User_READ", "User_CREATE",
                                    "User_UPDATE", "User_DELETE"};
        List<Privilege> priilegeList = new ArrayList<>();
        for(String name : priilegeNameList) {
            Privilege privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
            priilegeList.add(privilege);
        }

        role.setPrivileges(Set.copyOf(priilegeList));
        rolesRepository.save(role);

        role2.setPrivileges(Set.copyOf(priilegeList));
        rolesRepository.save(role2);
	}
}