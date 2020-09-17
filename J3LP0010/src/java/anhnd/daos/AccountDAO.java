/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.AccountDTO;
import anhnd.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author anhnd
 */
public class AccountDAO implements Serializable{

    public AccountDAO() {
    }
    
    public boolean insertAccount(AccountDTO dto) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Insert into Account(email, name, password, role, status) values(?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, dto.getEmail());
                preparedStatement.setString(2, dto.getName());
                preparedStatement.setString(3, dto.getPassword());
                preparedStatement.setInt(4, 0);
                preparedStatement.setInt(5, 0);
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

}
