package ma.emsi.jpaemsi.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.emsi.jpaemsi.entities.Role;
import ma.emsi.jpaemsi.entities.User;
import ma.emsi.jpaemsi.repositories.RoleRepository;
import ma.emsi.jpaemsi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByRoleName(roleName);
        if(user.getRoles()!=null){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

        //userRepository.save(user);
    }

    @Override
    public User authenticate(String userName, String password) {
        User user=userRepository.findByUserName(userName);
        if(user==null)  throw new RuntimeException("bad credentials!!");
        if(user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("bad credentials!!");
    }
}
