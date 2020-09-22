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

    public List<ArticleDTO> searchArticleByDescription(String search, int start, int noOfRecord) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ArticleDTO> result = null;
         try {
            connection = DBUtils.makeConnection();
            if(connection != null) {
                String sql = "Select title, description, image, createDate from Article order by createDate "
                        + "offset " + (start + 1) + " rows fetch next " + noOfRecord + " rows only";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String imagePath = resultSet.getString("image");
                    Date createDate = resultSet.getDate("createDate");
                    System.out.println(title + "title");
                    ArticleDTO dto = new ArticleDTO();
                    dto.setTitle(title);
                    dto.setDescription(description);
                    dto.setCreatedDate(createDate);
                    dto.setImagePath(imagePath);
                    result = new ArrayList<>();
                    result.add(dto);
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
        return result;
    }
}
