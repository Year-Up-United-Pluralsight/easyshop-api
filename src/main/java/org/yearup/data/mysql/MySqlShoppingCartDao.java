package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao
{
    public MySqlShoppingCartDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId){

        ShoppingCart shoppingCart = new ShoppingCart();
        Map<Integer, ShoppingCartItem> items = new HashMap<>();
        Product product = new Product();

        // Uses dataSource to get connection
        try (
            // 1. Opens Connection to Database
            Connection connection = getConnection();

            // 2. Creates preparedStatement for query to get ShoppingCartItems based on UserId
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "SELECT * " +
                            "FROM shopping_cart " +
                            "WHERE user_id = ?"
            );

        ){

            // 3. Sets parameter onto the statement
            preparedStatement1.setInt(1, userId);

            try (
                // 4. Executes query
                ResultSet resultSet1 = preparedStatement1.executeQuery();
            ){

                // 5. Loops through every ShoppingCartItem that has the corresponding userId
                while (resultSet1.next()) {

                    // Gets and stores info for each ShoppingCartItem
                    int productId = resultSet1.getInt("product_id");
                    int quantity = resultSet1.getInt("Quantity");

                    try (
                        // 2. Creates preparedStatement for query to get the Product corresponds to the ProductId
                        PreparedStatement preparedStatement2 = connection.prepareStatement(
                                "SELECT * " +
                                        "FROM products " +
                                        "WHERE product_id = ?"
                        );
                    ) {

                        // 3. Sets parameter onto the statement
                        preparedStatement2.setInt(1, productId);

                        try (
                            // 4. Executes query
                            ResultSet resultSet2 = preparedStatement2.executeQuery();
                        ) {

                            // 5. Loops through the results
                            while (resultSet1.next()) {

                                // Gets and stores info for each Product attribute
                                String name = resultSet1.getString("name");
                                BigDecimal price = resultSet1.getBigDecimal("price");
                                int categoryId = resultSet1.getInt("category_id");
                                String description = resultSet1.getString("description");
                                String color = resultSet1.getString("color");
                                int stock = resultSet1.getInt("stock");
                                boolean featured = resultSet1.getBoolean("featured");
                                String imageURL = resultSet1.getString("image_url");

                                // Creates new product
                                product = new Product(productId, name, price, categoryId, description, color, stock, featured, imageURL);

                            }

                            // Creates new ShoppingCartItem using this info
                            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity, new BigDecimal("0.0"));

                            // Adds this ShoppingCartItem into the ShoppingCart hashmap
                            items.put(productId, shoppingCartItem);
                        }

                    }

                }

            }

            // Constructs ShoppingCart
            shoppingCart.setItems(items);

            // Returns ShoppingCart
            return shoppingCart;
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart){
        return null;
    }

    @Override
    public void update(int user_id, ShoppingCart shoppingCart){

    }

    @Override
    public void delete(int user_id){

    }
}
