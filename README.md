# Getting Started

### About the app
The spring boot app exposes a few APIs for a store management tool:
- add product
- get product
- get product list
- replace product
- delete product
- update product price
- update product quantity
It can be easily tested using the swagger; link below.
On the persistence side an H2 in-memory db instance is used.

### Swagger
http://localhost:8080/swagger-ui/index.html#/
### Open API
http://localhost:8080/v3/api-docs

### Security
Basic auth is in place. The app has 2 users and roles set up that can be used for testing purposes:
- user/userpass
- admin/adminpass
The admin user is the only having delete rights.

### Error Handling
Two custom exceptions were implemented to cover the scenarios when:
- the user is trying to alter a product that cannot be found in the database
- the user is trying to update a product's price or product's quantity to a negative value
The custom exception can be easily extended by creating new implementation and errorHandlers in the controlleradvice.
