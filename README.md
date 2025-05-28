# 🚪 Escape-Room Management System

## 🔍 Overview

This application is designed to manage virtual escape rooms. It allows users to create escape rooms, design different
themes, manage rooms, decorative items, and puzzles, and handle reservations.

## 🏗️ Architecture

The application follows a layered architecture with clear separation of concerns:

1. **Frontend Layer** - User interface for interaction
2. **API Layer** - Controllers that process requests
3. **Business Logic Layer** - Services that implement business rules
4. **Infrastructure Layer** - Repositories that handle data access

## 💻 Frontend Layer

The frontend layer is a console-based user interface that provides menus for interacting with the system. It follows a
command pattern where each menu is a command that can be executed.

### Key Components

#### Menu System

- **MenuCommand<T>** - Abstract base class for all menus with generic return type
- **Menu** - Main entry point that manages the active escape room
- Entity-specific menus (EscapeRoomMenu, ThemeMenu, PuzzleMenu, etc.)

#### User Interaction Flow

1. The application starts with the main Menu
2. If no escape rooms exist, the user is prompted to create one
3. If escape rooms exist, the user selects one to work with
4. Once an active room is selected, the ManageEscapeRoomMenu is displayed
5. From there, the user can:
    - View and update escape room details
    - Manage themes
    - Manage rooms
    - Manage decorative items
    - Manage puzzles
    - Handle reservations
    - Manage customers

#### Menu Structure

Each menu follows a similar pattern:

1. Display options to the user
2. Get user input
3. Execute the corresponding action
4. Return to the menu or navigate to another menu

### Frontend Features

- Color-coded console output for better readability
- Input validation and error handling
- Consistent user experience across different entity management menus

## 🔄 API Layer

The API layer consists of controllers that act as intermediaries between the frontend and business logic layers. Each
controller is responsible for a specific domain entity or use case.

### Key Components

#### Controllers

- Entity-specific controllers (EscapeRoomController, ThemeController, PuzzleController, etc.)
- Each controller typically corresponds to a specific use case (Create, Update, Delete, etc.)

#### Controller Responsibilities

1. Receive DTOs (Data Transfer Objects) from the frontend
2. Initialize the appropriate service from the business logic layer
3. Pass the DTO to the service
4. Return the result to the frontend

### API and Business Logic Interaction

The API layer interacts with the business logic layer through the following pattern:

1. **Controller Initialization**:
    - Controllers create repository instances
    - Controllers create service instances and inject repositories

2. **Request Processing**:
    - Controllers receive DTOs from the frontend
    - Controllers delegate to services for business logic processing
    - Services use repositories for data access
    - Services return results to controllers
    - Controllers return results to the frontend

3. **Event Handling**:
    - Some services publish domain events
    - Event subscribers can react to these events

This architecture ensures:

- Loose coupling between layers
- Single responsibility for each component
- Testability of business logic
- Flexibility to change implementation details without affecting other layers

## 🛠️ Setup Instructions

### Environment Configuration

1. Create a `.env` file in the root directory of the project based on the `template.env` file:
   ```
   cp template.env .env
   ```
2. Edit the `.env` file and fill in the required environment variables:
    - MySQL configuration:
        - MYSQL_ROOT_PASSWORD: Root password for MySQL
        - MYSQL_DATABASE: Database name
        - MYSQL_USER: MySQL username
        - MYSQL_PASSWORD: MySQL password
        - MYSQL_PORT: Port for the main MySQL instance
        - MYSQL_TEST_PORT: Port for the test MySQL instance
        - MYSQL_HOST: MySQL host address
    - MongoDB configuration:
        - MONGO_ROOT_PASSWORD: Root password for MongoDB
        - MONGO_DATABASE: MongoDB database name
        - MONGO_USER: MongoDB username
        - MONGO_PASSWORD: MongoDB password
        - MONGO_PORT: Port for MongoDB
        - MONGO_HOST: MongoDB host address
        - MONGO_TEST_PORT: Port for the test MongoDB instance

### Complete your mongo setup running this command in the mongo shell (Mongo Compass can be used):

```js
use
escape_room; // or the database name you set in .env
db.createUser({
    user: "username", // same username as in .env
    pwd: "password", // same password as in .env
    roles: [{role: "readWriteAnyDatabase", db: "admin"}]
});
```

### Running with Docker Compose

To start the application with Docker Compose:

```
docker-compose up -d
```

This will start the following services:

- MySQL database for the application
- MySQL database for testing
- MongoDB database

## 🔧 Technologies Used

- **Java 21**: Core programming language
- **Maven**: Build and dependency management
- **MySQL**: Relational database for storing most application data
- **MongoDB**: NoSQL database for specific data storage needs
- **Docker**: Containerization for easy deployment
- **JUnit 5**: Testing framework
- **AssertJ**: Fluent assertions for testing

## 💾 Database Structure

The application uses a dual-database approach, with most data stored in MySQL and specific event-based data stored in
MongoDB.

### MySQL Database

MySQL stores the core entities of the application:

- **Escape Rooms**: The main entity that contains basic information about each escape room
- **Themes**: Different themes that can be applied to escape rooms
- **Rooms**: Physical spaces within escape rooms
- **Decorative Items**: Items used to decorate rooms
- **Puzzles**: Challenges that users need to solve
- **Customers**: Information about users who make reservations
- **Reservations**: Booking information for escape rooms
- **Room-Deco Relationships**: Mapping between rooms and decorative items

### MongoDB Database

MongoDB is used for event-based and notification data:

- **Notifications**: Messages sent to users (contains recipient name, email, and message content)
- **Rewards**: Special rewards given to customers (contains recipient, description, and delivery date)

This dual-database approach allows the application to leverage the strengths of both database types:

- MySQL provides strong consistency and relational integrity for the core business data
- MongoDB offers flexibility for storing document-based data like notifications and rewards

## 🎯 Conclusion

The Escape Room Management System demonstrates a well-structured application with clear separation of concerns. The
frontend provides a user-friendly interface for managing escape rooms and related entities, while the API layer ensures
proper communication between the frontend and business logic.
