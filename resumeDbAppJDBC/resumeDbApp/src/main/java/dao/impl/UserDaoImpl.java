package dao.impl;

import dao.inter.AbstractDao;
import dao.inter.UserDaoInter;
import entity.Country;
import entity.Skill;
import entity.User;
import entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    public User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthDate");


        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);

        return new User(id, name, surname, email, phone, nationality, birthplace, birthdate);

    }


    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT" +
                    "\tu.*," +
                    "\tc.name AS birthplace," +
                    "\tn.nationality " +
                    "FROM" +
                    "\tUSER u" +
                    "\tLEFT JOIN country c ON u.birthplace_id = c.id" +
                    "\tLEFT JOIN country n ON u.nationality_id = n.id;");

            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT\n" +
                    "\tu.*,\n" +
                    "\tc.NAME AS birthplace,\n" +
                    "\tn.nationality \n" +
                    "FROM\n" +
                    "\tUSER u\n" +
                    "\tLEFT JOIN country c ON u.birthplace_id = c.id\n" +
                    "\tLEFT JOIN country n ON u.nationality_id = n.id \n" +
                    "WHERE\n" +
                    "\tu.id= " + userId);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getUser(rs);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean updateUser(User user) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name =?, surname=?, email=?, phone=? where id=?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setInt(5, user.getId());

            return stmt.execute();

        } catch (Exception ex) {
            return false;
        }


    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id=" + id);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,email,phone) value(?,?,?,?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());


            return stmt.execute();

        } catch (Exception ex) {
            return false;
        }

    }







}