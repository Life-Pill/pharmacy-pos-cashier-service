# POS Cashier Service

This repository contains the source code for the POS Cashier Service, a component of the LifePill Point of Sale (POS) system. The POS Cashier Service is responsible for managing cashier entities, including CRUD (Create, Read, Update, Delete) operations and handling cashier details such as account information and bank details.

## Features

- **Cashier Management**: Allows users to create, retrieve, update, and delete cashier entities.
- **Detailed Cashier Information**: Provides functionality to retrieve detailed information about cashiers, including account information and bank details.
- **RESTful API**: Exposes a RESTful API for easy integration with other components of the POS system.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- RESTful API
- H2 Database (for development/testing)
- MySQL Database (for production)

## Setup Instructions

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Ensure you have Java and Maven installed on your machine.
4. Run `mvn spring-boot:run` to start the application.
5. Access the API endpoints through `http://localhost:8084/lifepill/v1/cashier`.

## API Endpoints

- `POST /lifepill/v1/cashier/save`: Create a new cashier entity.
- `PUT /lifepill/v1/cashier/update`: Update an existing cashier entity.
- `PUT /lifepill/v1/cashier/updateAccountDetails`: Update cashier account details.
- `PUT /lifepill/v1/cashier/updateBankAccountDetails`: Update cashier bank account details.
- `PUT /lifepill/v1/cashier/updatePassword`: Update cashier password.
- `PUT /lifepill/v1/cashier/updateRecentPin`: Update cashier recent PIN.
- `GET /lifepill/v1/cashier/get-all-cashiers`: Retrieve all cashiers.
- `GET /lifepill/v1/cashier/get-all-cashiers-by-active-state/{status}`: Retrieve cashiers by active state.
- `GET /lifepill/v1/cashier/get-by-id?id={cashierId}`: Retrieve cashier by ID.
- `DELETE /lifepill/v1/cashier/delete-cashier/{id}`: Delete cashier by ID.
- `GET /lifepill/v1/cashier/get-all-cashiers-bank-details`: Retrieve all cashiers with bank details.

## Contributors

- [Pramitha Jayasooriya](https://github.com/johndoe)
- [Tharindu Jayawardhana](https://github.com/TheTharz)
- [Chandula Jayathilake](https://github.com/ChandulaJ)
- [Inupa Vihan]()

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Feel free to contribute to this project by submitting bug reports, feature requests, or pull requests. Your feedback and contributions are highly appreciated!
