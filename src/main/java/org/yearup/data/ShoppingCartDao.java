package org.yearup.data;

import org.yearup.models.Category;
import org.yearup.models.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);

    // add additional method signatures here
    ShoppingCart create(ShoppingCart shoppingCart);
    void update(int user_id, ShoppingCart shoppingCart);
    void delete(int user_id);

//    List<ShoppingCartItem> getAllShoppingCartItems();


    // What is a shopping cart?
    // Made up of...
        // 1) user_id
        // 2) product_id
        // 3) quantity

    // My issue is...
    // I want to filter the entries of the shopping cart table by user_id
    // That way I can just say, "For every shopping cart with user_id equal to the user_id of the current user..."
    // But I need to be able to write SQL to do that (there's no MySqlShoppingCartDao)

    // I'm adding my own MySql -- what's the plan?...


    // NOTES
    // "ShoppingCart" is just a Hashmap of <productId, ShoppingCartItems>
    // People's ShoppingCartItems all mixed inside of the one great ShoppingCart
    // Top-down Hierarchy Format
        // Items [ShoppingCart]
        // > Product Id [ShoppingCartItem Key]
        // > Product, Quantity, DiscountPercent, LineTotal [ShoppingCartItem Value]
        // > ProductId, Name, Price, CategoryId, Description, Color, Stock, ImageURL, Featured [Product]


}
