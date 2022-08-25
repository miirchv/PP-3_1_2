package ru.mii.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mii.springboot.dao.UserDao;
import ru.mii.springboot.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getReferenceById(id);
    }

    @Override
    public void saveUser(User user) {
        User user1 = new User();
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRoles(user.getRoles());
        userDao.save(user1);
    }

    @Override
    public void updateUser(long id, User user) {
        User user1 = userDao.getReferenceById(id);
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRoles(user.getRoles());
        userDao.save(user1);

    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteById(id);
    }
}
