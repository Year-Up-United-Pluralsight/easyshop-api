package org.yearup.data.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class MySqlCategoryDaoTest {

    @Test
    void getAllCategories() {

        // ARRANGE
        // Creates the datasource
        BasicDataSource dataSource = new BasicDataSource();

        // Configures the datasource
        dataSource.setUrl("jdbc:mysql://localhost:3306/easyshop");
        dataSource.setUsername("admin");
        dataSource.setPassword("password");

        // ACT



        // ASSERT
    }
}