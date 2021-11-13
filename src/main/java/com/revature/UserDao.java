package com.revature;
// package com.revature.persistence;

import com.revature.Accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

        public UserDao(Accounts acc)/*implements Dao<User>*/ {}

        // @Override
        public void create(Accounts acc, String sql) throws SQLException {
                System.out.println("create new account with:");
                System.out.println("account number=" + acc.getAccNum());
                System.out.println("account balance=" + acc.getBalance());


                 try (Connection conn = ConnectionService.getInstance()) {
                        assert conn != null;
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.executeUpdate();

                } catch (SQLException e) {
                        System.out.println(e.getMessage());
                }
        }

    public Double getBalance(Persons ps ) {
       //String sql = "select user_name, pass_word from \"Accounts\" where user_name = '" + ps.userName + "' and pass_word = '" + ps.passWord + "'    ";
        //String sql = "select user_name from \"Accounts\" where user_name = '" + ps.userName + "';";
         String sql = "select balance from \"Accounts\" where user_name = '" + ps.userName + "';";
        Persons person = null;
        try (Connection connection = ConnectionService.getInstance()) {
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            if (rs == null){
               System.out.println("User name or Password incorrect.");
                return (-1.0);  // user name or password not found so return negative balance
            }

            if (rs.next()) {
                Double bal = 0.0; //= rs.getDouble(1);
                System.out.println("Your balance is $" + rs.getDouble(1));
                return(bal);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return(1.0);
    }


        public Persons getById(int id) {
            String sql = "select * from \"Persons\" where ps_id=?";
            Persons person = null;
            try (Connection connection = ConnectionService.getInstance()) {
                assert connection != null;
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    person = new Persons();
                    person.setPersonId(rs.getString(1));
                    person.setUserName(rs.getString(2));
                    person.setPassWord(rs.getString(3));
                    // person.setUserType(UserType.values()[rs.getInt(4) - 1]);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return person;
        }



        public boolean update(Connection conn, Persons user, String username, String password, int psId) {
            String sql = "update \"Persons\" set username=?, password=? where id=?";

            if (conn != null) conn= ConnectionService.getInstance();
                try {
                assert conn != null;
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setInt(3, psId);

            if (stmt.executeUpdate() != 0) {
                return true;
            }

                // shorthand combination returning a conditional operation
                return stmt.executeUpdate() != 0;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return false;
        }

        public boolean deleteById(int id) {
            return false;
        }
    }



