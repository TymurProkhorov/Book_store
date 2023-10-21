# Book_store
![header picture](images/bookStoreImage.jpg)
___

## **Content**
* [Summary](#summary)
* [What can book-store do](#what-can-book-store-do)
* [Technologies](#technologies)
* [How to run project](#how-to-run-project)
***

### **Summary**
* Java 17 restful stateless web-Application that allows, depending on role, to explore and purchase books; to manage the book store.
* The project follows the Model-View-Controller (MVC) architectural pattern and is organized into multiple layers,
making it a multi-tier architecture. The layers include presentation, business logic,
data access, security, validation, configuration, and mapping.
* Application supports CRUD, REST, SOLID, ACID design patterns.
* Security implemented using JWT-tokens.
* Isbn validation and password field matching provided.
* Application supports pagination and sorting.
* Application containerized using docker to provide efficient data management.

***

### **What can book-store do**
* Register new users;
* Authenticate and authorise users;


Role user can(endpoints specified):
* get list of all books - /books
* get specific book by id - /books/id
* search books by any amount of parameters - /books/search
* get list of all categories - /categories
* get specific category by id - /categories/id
* get all books by category id - /categories/id/books
* get shopping cart - /cart
* add book to shopping cart - /cart
* update quantity of books in the shopping cart - /cart/cart-items/cartItemId
* delete book from shopping cart - /cart/cart-items/cartItemId
* get user's order history - /orders
* place new order - /orders
* get all order details - /orders/orderId/items
* get a specific order item within an order - /orders/orderId/items/itemId


Role admin can(endpoints specified):
* the same opportunities as user
* create a new book - /books
* update specific book by id - /books/id
* delete specific book by id - /books/id
* create a new category - /categories
* update category by id - /categories/id
* delete category by id - /categories/id
* update order status - /orders/orderId

***

### **Technologies**


* Java 17
* Maven
* Spring Boot
* Spring Data JPA
* Spring Security - roles concept, JWT
* Spring Boot Web
* Pagination, sorting, Swagger(Open API)
* JUnit
* Mockito
* MySql DB
* Criteria query in Spring Boot
* Soft delete concept
* Liquibase
* Custom data validation
* Docker

***

### **How to run project**

Requirements

To launch the application please make sure you have Docker installed. Then configure your database settings in the .env file.
Then run application using
<div style="border: 1px solid #8FBC8F; background-color: rgba(143, 188, 143, 0.2);">
docker-compose up
</div>
