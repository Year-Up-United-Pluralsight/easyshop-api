# EasyShop Backend API – E-Commerce Capstone Project

Welcome to the backend development portion of **EasyShop**, an e-commerce platform that enables users to browse and purchase products online. This project represents **Version 2** of the EasyShop application, with a focus on debugging existing features and implementing new backend functionality using **Spring Boot** and a **MySQL database**.

---

## 📌 Project Overview

This project is part of a capstone experience where you take on the role of a **backend developer**. The frontend and backend codebases are already functional in Version 1. Your responsibilities begin with improving the backend logic and enhancing the application with new capabilities.

---

## 💻 Technologies Used

- **Java 17**
- **Spring Boot**
- **MySQL**
- **Postman** (for API testing)
- **JUnit** (for unit testing)
- **GitHub Projects** (for issue and task tracking)

---

## ✅ Version 1 Features

Version 1 of the application includes:

- User registration and login
- Product browsing by category
- Search and filter functionality (with known bugs)
- Functional front-end website for interacting with the API

---

## 🛠 Tasks for Version 2

### 🔧 Fix Existing Bugs

- **Bug #1**: Fixed search/filter endpoint returning incorrect results. Corrected the filtering mechanism.
- **Bug #2**: Debugged editing an existing product (e.g., changing price or description) resulting in duplicate entries instead of updating the current record. Ensured updates replace the correct record.

### 🚀 New Feature: Shopping Cart

Implemented a persistent shopping cart feature:

- Available only to **logged-in users**
- Items added to the cart are saved in the database and associated with the user
- Cart contents persist between sessions (e.g., logout → login)
- Utilize the existing `shopping_cart` table in the database

### 🧩 Categories Management (ADMIN Only)

- Implement all methods in `CategoriesController`
- Restrict category **insert**, **update**, and **delete** operations to users with the `ADMIN` role
- Complete missing logic in `MySqlCategoriesDao`

---

## 🔍 Testing & Debugging

You are expected to:

- Use **Postman** extensively to test each API endpoint
- Create and run **unit tests** for critical business logic
- Manually test edge cases and potential failure points

---

## ⭐️ Interesting Code Snippet





