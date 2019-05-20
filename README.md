This is a web application to manage the book rental system
where people can rent out their own books or rent books from
other people.


Prerequisites to run the project

Java, maven, mongodb, Intellij/Eclipse


Steps to run the project

    1. Checkout the repository to your local directory

    2. Make sure that mongodb is installed in your system and is up and running

    3. If security is enabled to login to mongodb, then enter those credentials in src/main/resources/application.properties file

    4. Uncomment the mongodb.user and other configuration and enter the proper values. Also enter the proper ip address of mongodb host

    5. Run the project using "./mvnw spring-boot:run"

    6. The application uses JWT for authentication and authorization in the backend.

    7. To test the api calls create a dummy user in the database under "user" collection

        or you can create new user using below command

        curl -H "Content-Type: application/json" -X POST -d '{
            "username": "admin",
            "password": "password"
        }' http://localhost:8080/users/sign-up

    8. Now try to login using the below command

        curl -i -H "Content-Type: application/json" -X POST -d '{
            "username": "admin",
            "password": "password"
        }' http://localhost:8080/login

    9. Now test the data by passing the JWT token to any of the get requests from postman

    10. Sample endpoint is /api/v1/books/listall
