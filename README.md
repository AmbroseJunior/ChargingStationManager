
# Charging Station Management App

## 🚀 Overview

The **Charging Station Management App** is a web-based platform for managing electric vehicle (EV) charging stations, providers, and users.  
Built using **Jakarta EE**, **JSF**, **PrimeFaces**, **JPA (Hibernate)**, and deployed on **WildFly 33**, it provides full CRUD operations with a clean admin interface.

Originally created for educational and small-scale use, the app now supports real database persistence using JPA and is fully extendable for larger deployments.

---

## ✅ Features

### 📋 Functional Requirements

- **Dashboard / Home**
  - Displays a welcome message.
  - Optional: list all providers, charging stations, and users in tables.

- **Providers**
  - View all providers with name and contact info.
  - Add new providers.
  - Edit existing providers in popup dialogs.
  - Delete with confirmation.

- **Charging Stations**
  - View all stations (ID, location, status).
  - Add new stations:
    - Select a provider
    - Enter location, status, speed, region, price, connector type, availability
  - Edit station details via dialog.
  - Delete with confirmation.

- **Users**
  - View all users (name, email, balance, car type).
  - Add/edit/delete users with validation.
  - Prevent duplicate usernames.

- **Error Handling**
  - Friendly messages for validation failures.
  - Clear feedback on exceptions and missing fields.

---

### ⚙ Non-Functional Requirements

- **Performance**: ~2s load time; supports ~100 concurrent users.
- **Usability**: Clean UI, popup dialogs, form clearing after use.
- **Scalability**: Now supports persistent storage via JPA.
- **Security**: Basic input validation (authentication planned).
- **Maintainability**: Layered structure: UI → Service → DAO → JPA.
- **Compatibility**: Works on Chrome, Firefox, Edge; deploys to WildFly 33.

---

## 🧰 Prerequisites

- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.6+](https://maven.apache.org/download.cgi)
- [WildFly 33.0.0.Final](https://www.wildfly.org/downloads/)
- MySQL (optional, if using persistent DB)
- A modern web browser

---

## 📦 Setup Instructions

### 1. Clone or Download the Project

```bash
git clone <repository-url>
cd AIV_Project
```

---

### 2. Configure WildFly

1. Extract WildFly to `C:\Users\PC\Desktop\wildfly-33.0.0.Final` (or update paths accordingly).
2. Start WildFly:

```bash
cd C:\Users\PC\Desktop\wildfly-33.0.0.Final\bin
standalone.bat
```

3. Visit [http://localhost:8080](http://localhost:8080) to check it’s running.

---

### 3. Build with Maven

```bash
cd C:\Users\PC\Desktop\AIV_Project
mvn clean package
```

This generates a `.war` file in the `target/` directory.

---

### 4. Deploy the App

Use either:

```bash
mvn wildfly:deploy
```

Or copy `target/ChargingStationManager.war` to:

```
wildfly-33.0.0.Final/standalone/deployments/
```

---

### 5. Access the App

Visit:

```
http://localhost:8080/ChargingStationManager
```

---

## 👨‍💻 How to Use the App

### 🏠 Home

- Navigate between:
  - View Providers / Charging Stations / Users
  - Add Provider / Station / User

---

### 🔌 Manage Charging Stations

- **Add**:
  - Select a provider (dropdown).
  - Fill in location, status, speed, price, etc.
  - Submit with **Save**.

- **Edit**:
  - Click **Edit**, update values, hit **Save**.

- **Delete**:
  - Click **Delete**, confirm.

---

### 🧑‍💼 Manage Providers

- View, add, edit, and delete providers.
- Includes name and contact info fields.

---

### 🚗 Manage Users

- Add/edit users: name, email, balance, car type.
- Duplicate usernames show an error.
- Supports full CRUD actions.

---

## 🧠 Known Limitations

- ❌ No authentication/login.
- ⚠ In-memory behavior only if not connected to a database.
- 📊 No advanced UI features like filtering or pagination yet.

---

## 🛣️ Future Enhancements

- 🔐 Authentication (admin/user roles).
- 💾 Full MySQL/PostgreSQL support with schema auto-generation.
- 📊 Table filters and pagination.
- 🎨 Theming and mobile responsiveness.
- 🧪 Unit + integration tests (JUnit, Selenium).
- 🛡️ Role-based access control.
- 📥 Export data options (CSV/Excel).

---

## 🔍 Troubleshooting

| Issue | Fix |
|-------|-----|
| **Port 8080 in use** | Close apps using it or change port in `standalone.xml` |
| **Deployment fails** | Ensure WildFly is running, and `mvn package` succeeded |
| **Validation errors** | Ensure all fields are filled |
| **Hibernate lazy loading** | Already fixed via `JOIN FETCH` in DAO |
| **No session / proxy error** | Use proper fetch strategy in JPA (we did!) |

---

## 📁 Project Structure

```
AIV_Project/
├── src/
│   └── main/
│       ├── java/
│       │   ├── converters/
│       │   ├── service/
│       │   ├── dao/
│       │   └── vao/
│       ├── resources/
│       └── webapp/
│           ├── WEB-INF/
│           ├── index.xhtml
│           ├── providers.xhtml
│           ├── addProvider.xhtml
│           ├── chargingStations.xhtml
│           ├── addChargingStation.xhtml
│           ├── users.xhtml
│           └── addUser.xhtml
├── pom.xml
└── README.md
```

---

## 📬 Contact

**Developer**: Nnamdi Ambrose Junior Eze  
**Email**: [nadis2u@gmail.com](mailto:nadis2u@gmail.com)

---

🧡 _Manage EV infrastructure with ease — built for learning, scaling, and the electric future._

```

---
