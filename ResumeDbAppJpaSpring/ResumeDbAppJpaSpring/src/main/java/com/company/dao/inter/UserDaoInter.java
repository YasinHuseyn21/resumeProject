package com.company.dao.inter;



import com.company.entity.User;

import java.util.List;

public interface UserDaoInter {
    public List<User> getAll();
    public User getById(int userId);
    public boolean updateUser(User user);
    public boolean removeUser(int id);
    public boolean addUser(User user);
    public User findByEmail(String email);




}
