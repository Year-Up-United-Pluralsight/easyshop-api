package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()
    {
        // get all categories

        // Instantiates list of categories
        List<Category> allCategories = new ArrayList<>();

        // Uses dataSource to get connection
        try (
            // 1. Opens Connection to Database
            Connection connection = getConnection();

            // 2. Creates preparedStatement for query
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "SELECT * FROM categories"
            );

            // 3. Executes the query
            ResultSet resultSet1 = preparedStatement1.executeQuery();
        ){
            // Loops through the results
            while (resultSet1.next()) {

                // Gets and stores info for each category
                int categoryId = resultSet1.getInt("category_id");
                String name = resultSet1.getString("name");
                String description = resultSet1.getString("description");

                // Constructs category object; adds it list of categories
                allCategories.add(new Category(categoryId, name, description));
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        // Returns list of categories
        return allCategories;
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id

        // Uses dataSource to get connection
        try (
            // 1. Opens Connection to Database
            Connection connection = getConnection();

            // 2. Creates preparedStatement for query
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "SELECT name " +
                        "FROM categories " +
                        "WHERE category_id = ?"
            );

        ){

            // 3. Sets parameter onto the statement
            preparedStatement1.setInt(1, categoryId);

            try (
                // 4. Executes query
                ResultSet resultSet1 = preparedStatement1.executeQuery();
            ){

                // 5. Loops through the results
                while (resultSet1.next()) {

                    // Gets and stores info for each category
                    String name = resultSet1.getString("name");
                    String description = resultSet1.getString("description");

                    // Constructs and returns category object
                    return new Category(categoryId, name, description);
                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category

        // Uses dataSource to get connection
        try (
            // 1. Opens Connection to Database
            Connection connection = getConnection();

            // 2. Creates preparedStatement for query
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "INSERT INTO categories(category_id, name, description) " +
                            "VALUES(?, ?, ?)"
            );

        ){

            // 3. Sets parameters onto the statement
            preparedStatement1.setInt(1, category.getCategoryId());
            preparedStatement1.setString(2, category.getName());
            preparedStatement1.setString(3, category.getDescription());

            // 4. Executes query
            int rows = preparedStatement1.executeUpdate();

            // 5. Confirms the update
            System.out.printf("Rows updated %d\n", rows);

            // Returns added category (aka the parameter itself)
            return category;
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(int categoryId, Category category)
    {

        // update category

        // Uses dataSource to get connection
        try (
            // 1. Opens Connection to Database
            Connection connection = getConnection();

            // 2. Creates preparedStatement for query
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "UPDATE categories " +
                            "SET name = ?, description = ? " +
                            "WHERE category_id = ?"
            );

        ){

            // 3. Sets parameters onto the statement
            preparedStatement1.setString(1, category.getName());
            preparedStatement1.setString(1, category.getDescription());
            preparedStatement1.setInt(3, categoryId);

            // 4. Executes query
            int rows = preparedStatement1.executeUpdate();

            // 5. Confirms the update
            System.out.printf("Rows updated %d\n", rows);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category

        // Uses dataSource to get connection
        try (

            // 1. Opens Connection to Database
            Connection connection = getConnection();

            // 2. Creates preparedStatement for query
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "DELETE FROM categories " +
                            "WHERE category_id = ?"
            );

        ){

            // 3. Sets parameters onto the statement
            preparedStatement1.setInt(1, categoryId);

            // 4. Executes query
            int rows = preparedStatement1.executeUpdate();

            // 5. Confirms the update
            System.out.printf("Rows updated %d\n", rows);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
