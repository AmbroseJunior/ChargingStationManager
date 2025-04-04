
# Charging Station Management App

## Overview
The Charging Station Management App is a web-based application designed to manage electric vehicle charging stations, their providers, and users. 
Built using Java, JSF (JavaServer Faces), and PrimeFaces, the app provides a user-friendly interface for administrators to perform CRUD (Create, Read, Update, Delete) operations on providers, charging stations, and users. 
The app uses an in-memory data storage system for simplicity, making it ideal for small-scale management or educational purposes.

## Features
### Functional Requirements
- **Home Page**:
    - Displays a welcome message and a button to list all providers, charging stations, and users.
    - Shows tables with providers, charging stations, and users when the "List Providers, Stations, and Users" button is clicked but not necessarily necessary.
- **Manage Providers**:
    - View a list of all providers with their names and contact information.
    - Add a new provider with a name and contact info.
    - Edit an existing provider using a popup dialog.
    - Delete a provider with a confirmation dialog.
- **Manage Charging Stations**:
    - View a list of all charging stations with their IDs, locations, and statuses.
    - Add a new charging station with details like provider, location, status, charging speed, region, price per kWh, connector type, and availability.
    - Edit an existing charging station using a popup dialog.
    - Delete a charging station with a confirmation dialog.
- **Manage Users**:
    - View a list of all users with their names, emails, account balances, and car types.
    - Add a new user with a name, email, account balance, and car type.
    - Edit an existing user using a popup dialog.
    - Delete a user with a confirmation dialog.
    - Prevent duplicate user names with a clear error message.
- **Error Handling**:
    - Displays user-friendly error messages for validation failures (e.g., missing required fields, duplicate user names).
    - Gracefully handles exceptions during CRUD operations.

### Non-Functional Requirements
- **Performance**:
    - The app should load pages within 2 seconds under normal conditions.
    - Supports up to 100 concurrent users (limited by in-memory storage and WildFly configuration).
- **Usability**:
    - Provides a clean and intuitive UI using PrimeFaces components.
    - Uses popup dialogs for editing to improve user experience.
    - Clears form fields after adding a new entity to streamline workflows.
- **Scalability**:
    - Currently uses in-memory storage, suitable for small-scale use. Can be extended to use a database for larger datasets.
- **Security**:
    - Basic input validation to prevent invalid data entry.
    - No user authentication (intended for admin use in a controlled environment).
- **Maintainability**:
    - Code is organized into layers (UI, service, DAO) for easy maintenance.
    - Includes logging for debugging purposes.
- **Compatibility**:
    - Compatible with modern web browsers (e.g., Chrome, Firefox, Edge).
    - Requires WildFly 33.0.0.Final as the application server.

## Prerequisites
Before setting up and running the app, ensure you have the following installed on your system:

- **Java Development Kit (JDK)**: Version 17 or later.
    - Download from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or use OpenJDK.
- **Maven**: Version 3.6.0 or later.
    - Download from [Apache Maven](https://maven.apache.org/download.cgi).
- **WildFly Application Server**: Version 33.0.0.Final.
    - Download from [WildFly](https://www.wildfly.org/downloads/) and extract to `C:\Users\PC\Desktop\wildfly-33.0.0.Final` (or adjust paths as needed).
- **Git** (optional): To clone the project repository if hosted on a version control system.
- **A Modern Web Browser**: Chrome, Firefox, or Edge for accessing the app.

## Setup Instructions
Follow these steps to set up and run the Charging Station Management App on your local machine.

### Step 1: Clone or Download the Project
If the project is hosted on a Git repository, clone it:
```
git clone <repository-url>
cd "the git name"
```
Alternatively, if you’re working locally, ensure the project is located at:
```
C:\Users\PC\Desktop\AIV_Project Or Your preferred directory
```

### Step 2: Configure WildFly
1. Ensure WildFly 33.0.0.Final is extracted to `C:\Users\PC\Desktop\wildfly-33.0.0.Final`.
2. Start WildFly to verify it’s working:
   ```
   cd C:\Users\PC\Desktop\wildfly-33.0.0.Final\bin
   standalone.bat
   ```
3. Open a browser and navigate to `http://localhost:8080`. You should see the WildFly welcome page.
4. Stop WildFly by pressing `Ctrl+C` in the terminal.

### Step 3: Build the Project
1. Navigate to the project directory:
   ```
   cd C:\Users\PC\Desktop\AIV_Project
   ```
2. Build the project using Maven:
   ```
   mvn clean package
   ```
   This will generate a `.war` file in the `target` directory (e.g., `target/ChargingStationManager.war`).

### Step 4: Deploy the App to WildFly
1. Deploy the app to WildFly:
   ```
   mvn wildfly:deploy
   ```
   This command automatically starts WildFly and deploys the app. If WildFly is already running, it will deploy the app to the running instance.

### Step 5: Access the App
1. Open a web browser and navigate to:
   ```
   http://localhost:8080/ChargingStationManager
   ```
2. You should see the home page with the message "Welcome to the Charging Station Management App".

## How to Use the App
The app is designed for administrators to manage providers, charging stations, and users. Here’s how to use each feature:

### Home Page
- **List Entities**: Click the "List Providers, Stations, and Users" button to view all providers, charging stations, and users in tables.
- **Navigation**: Use the top menu to navigate to "View Providers", "View Charging Stations", "View Users", "Add Provider", "Add Charging Station", or "Add User".

### Manage Providers
1. **View Providers**:
    - Click "View Providers" in the menu.
    - See a table of all providers with their names and contact info.
2. **Add a Provider**:
    - Click "Add Provider" in the menu.
    - Enter a name (e.g., "Provider2") and contact info (e.g., "provider2@mail.com").
    - Click "Save". The form will clear, and you’ll be redirected to the providers page.
3. **Edit a Provider**:
    - On the "View Providers" page, click "Edit" next to a provider.
    - Update the name or contact info in the popup dialog.
    - Click "Save" to update the provider.
4. **Delete a Provider**:
    - On the "View Providers" page, click "Delete" next to a provider.
    - Confirm the deletion in the popup dialog by clicking "Yes".

### Manage Charging Stations
1. **View Charging Stations**:
    - Click "View Charging Stations" in the menu.
    - See a table of all charging stations with their IDs, locations, and statuses.
2. **Add a Charging Station**:
    - Click "Add Charging Station" in the menu.
    - Select a provider from the dropdown (e.g., "test").
    - Enter details (e.g., Location: "Ljubljana", Status: "OPERATIONAL", Charging Speed: 50.0, Region: "Central", Price per kWh: 0.5, Connector Type: "Type2", Available: checked).
    - Click "Save". The form will clear, and you’ll be redirected to the charging stations page.
3. **Edit a Charging Station**:
    - On the "View Charging Stations" page, click "Edit" next to a station.
    - Update the details in the popup dialog (e.g., change status to "OCCUPIED").
    - Click "Save" to update the station.
4. **Delete a Charging Station**:
    - On the "View Charging Stations" page, click "Delete" next to a station.
    - Confirm the deletion in the popup dialog by clicking "Yes".

### Manage Users
1. **View Users**:
    - Click "View Users" in the menu.
    - See a table of all users with their names, emails, account balances, and car types.
2. **Add a User**:
    - Click "Add User" in the menu.
    - Enter details (e.g., Name: "Jane Smith", Email: "jane@smith.com", Account Balance: 150.0, Car Type: "Type3").
    - Click "Save". The form will clear, and you’ll be redirected to the users page.
    - Note: If you try to add a user with a duplicate name (e.g., "feri"), an error message will appear.
3. **Edit a User**:
    - On the "View Users" page, click "Edit" next to a user.
    - Update the details in the popup dialog (e.g., change email to "jane.updated@smith.com").
    - Click "Save" to update the user.
4. **Delete a User**:
    - On the "View Users" page, click "Delete" next to a user.
    - Confirm the deletion in the popup dialog by clicking "Yes".

## Known Limitations
- **In-Memory Storage**: Data is stored in memory using `HashMap` in the DAO layer. All data will be lost when the WildFly server restarts.
- **No Authentication**: The app does not include user authentication or authorization, making it suitable for admin use in a controlled environment.
- **Scalability**: Limited to small-scale use due to in-memory storage. Not suitable for large datasets without a database.
- **Basic UI**: The UI is functional but lacks advanced styling or theming.

## Future Improvements
- **Persistent Storage**: Integrate a database (e.g., MySQL or PostgreSQL) using JPA/Hibernate for persistent data storage.
- **User Authentication**: Add login functionality with roles (e.g., admin, user) using WildFly’s security features or a framework like Spring Security.
- **Search and Filter**: Add search and filter functionality to the tables for easier data management.
- **Enhanced UI**: Apply a PrimeFaces theme or custom CSS for a more polished look.
- **Pagination**: Add pagination to the tables to handle large datasets efficiently.
- **Input Validation**: Add more robust validation (e.g., email format, positive numbers for account balance).
- **Testing**: Write unit tests for the service and DAO layers using JUnit, and end-to-end tests using Selenium.

## Troubleshooting
- **WildFly Not Starting**:
    - Ensure no other process is using port 8080. You can change the port in `C:\Users\PC\Desktop\wildfly-33.0.0.Final\standalone\configuration\standalone.xml` if needed.
    - Check the WildFly logs at `C:\Users\PC\Desktop\wildfly-33.0.0.Final\standalone\log\server.log` for errors.
- **Deployment Fails**:
    - Run `mvn clean package` to ensure the `.war` file is generated correctly.
    - Verify that WildFly is running before deploying (`mvn wildfly:deploy`).
- **Error Messages in UI**:
    - If you see validation errors (e.g., "Provider is required"), ensure all required fields are filled.
    - Check the WildFly logs for stack traces if an unexpected error occurs.
- **Data Not Persisting**:
    - This is expected due to in-memory storage. Restarting WildFly will clear all data.

## Project Structure
```
AIV_Project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── converters/         # JSF converters (e.g., ProviderConverter)
│   │   │   ├── Service/            # Service layer (e.g., ChargingStationService)
│   │   │   ├── vao/               # Entity classes (e.g., ChargingStation, Provider, User)
│   │   │   └── AppBean.java       # Main JSF managed bean
│   │   ├── resources/
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── template.xhtml  # JSF template for consistent layout
│   │       ├── addChargingStation.xhtml
│   │       ├── addProvider.xhtml
│   │       ├── addUser.xhtml
│   │       ├── chargingStations.xhtml
│   │       ├── index.xhtml
│   │       ├── providers.xhtml
│   │       └── users.xhtml
├── pom.xml                         # Maven configuration file
└── README.md                       # This file
```

## Contact
For questions, issues, or contributions, please contact:
- **Developer**: [Nnamdi Ambrose Junior Eze]
- **Email**: [nadis2u@gmail.com]

---
