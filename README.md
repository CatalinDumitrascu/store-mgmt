# store-mgmt
Backend project for managing products belonging to a specific store

To try the project, clone and run the spring boot application. It will be running on port 8080.

Available endpoints:
- GET /api/products/info - returns a simple message if the server is up
- GET /api/products - returns a list of all the available products
- GET /api/products/{id} - returns a product by its id
- POST /api/products - creates a new product and stores it in the database based on a JSON body
- PUT /api/products/{id} - updates an existing product using its id to look it up and a JSON body to update its properties

The application uses an in-memory DB, and has a config for some preloaded data.

The application uses Basic Auth, and has two user roles set up, as follows:
USER - user/password
ADMIN - admin/admin
- GET /api/products/info - available even if not authenticated
- GET /api/products - USER/ADMIN
- GET /api/products/{id} - USER/ADMIN
- POST /api/products - ADMIN
- PUT /api/products/{id} - ADMIN

Possible further developments:
- Changing to an RDBMS (like PostgreSQL for instance)
- Exapanding the logging to more classes
- Adding a @ControllerAdvice class for nicer responses in case of errors before the execution of the developed code, like MethodNotAllowed, or when validation fails for the request body.
- Adding multiple custom exceptions for nicer responses in code, more than the simple 400 BAD Request which was used
- Adding more functionality like searching for products with filters
- Adding tests for all the classes


