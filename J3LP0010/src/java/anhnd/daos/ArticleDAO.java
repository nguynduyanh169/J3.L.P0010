/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.ArticleDTO;
import anhnd.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author anhnd
 */
public class ArticleDAO implements Serializable {

    public ArticleDAO() {
    }

    public List<ArticleDTO> searchArticleByDescription(String search, int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Select title, description, image, createDate from Article Where description like N'%"+ search + "%' order by createDate desc "
                        + "offset ? rows fetch next ? rows only";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, pageIndex);
                preparedStatement.setInt(2, pageSize);
                resultSet = preparedStatement.executeQuery();
                result = new ArrayList<>();
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String imagePath = resultSet.getString("image");
                    Date createDate = resultSet.getDate("createDate");
                    dto = new ArticleDTO();
                    dto.setTitle(title);
                    dto.setDescription(description);
                    dto.setCreatedDate(createDate);
                    dto.setImagePath(imagePath);
                    result.add(dto);
                }
            }
            for (ArticleDTO articleDTO : result) {
                System.out.println(articleDTO.toString());
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return result;
    }

    public int countArticleByDescription(String search) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "select count(articleId) from Article where description like ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "%" + search + "%");
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return result;
    }

    public boolean insertArticle(ArticleDTO dto) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Insert into Article (title,description,image,createDate,createBy,status) values (?,?,?,CAST( GETDATE() AS date),?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, dto.getTitle());
                preparedStatement.setString(2, dto.getDescription());
                preparedStatement.setString(3, dto.getImagePath());
                preparedStatement.setString(4, dto.getCreateBy());
                preparedStatement.setInt(5, dto.getStatus());
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    check = true;
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

        return check;
    }
}
