# 📚 Online Learning Management System

Welcome to the **Online Learning Management System**, a sleek and functional console-based application built in Java. This project empowers students, instructors, and admins to engage in a dynamic e-learning environment with course management, assignment handling, and interactive discussion forums.

---

## ✨ Features

- **User Roles**:
  - **Admin**: Oversee all courses.
  - **Instructor**: Create and manage courses, grade assignments, and engage in discussions.
  - **Student**: Enroll in courses, submit assignments, view grades, and participate in forums.
- **Course Management**: Create, enroll, and view course content.
- **Assignment System**: Submit and grade assignments with ease.
- **Discussion Forums**: Foster collaboration through course-specific discussion threads.
- **File-Based Persistence**: Store data in text files for simplicity and portability.

---

## 🛠 Prerequisites

To run this project, ensure you have:
- **Java Development Kit (JDK)** 8 or higher ([Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html))
- A terminal (Windows Command Prompt or PowerShell) or an IDE like **IntelliJ IDEA**, **Eclipse**, or **VS Code**
- Basic familiarity with Java and command-line operations

---

## 📂 Directory Structure

Ensure your project follows this structure:

```
OnlineLearningSystem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── model/
│   │   │   │   ├── User.java
│   │   │   │   ├── Course.java
│   │   │   │   ├── Assignment.java
│   │   │   │   ├── DiscussionPost.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   ├── CourseService.java
│   │   │   │   ├── AssignmentService.java
│   │   │   │   ├── DiscussionService.java
│   │   │   ├── storage/
│   │   │   │   ├── FileStorage.java
│   │   │   ├── main/
│   │   │   │   ├── Main.java
│   │   ├── resources/
│   │   │   ├── users.txt
│   │   │   ├── courses.txt
│   │   │   ├── assignments.txt
│   │   │   ├── discussion_posts.txt
├── data/
│   ├── users.txt
│   ├── courses.txt
│   ├── assignments.txt
│   ├── discussion_posts.txt
├── bin/  (created after compilation)
├── README.md
```

> **Note**: The `main` package contains `Main.java`, and the `data/` directory must contain empty `.txt` files for persistence.

---

## 🚀 Setup

Follow these steps to get the project up and running:

### 1. Create the Project Structure
- Create the directory structure as shown above.
- Place each Java file in its respective package under `src/main/java/`:
  - `model` for `User.java`, `Course.java`, `Assignment.java`, `DiscussionPost.java`
  - `service` for `UserService.java`, `CourseService.java`, `AssignmentService.java`, `DiscussionService.java`
  - `storage` for `FileStorage.java`
  - `main` for `Main.java`
- Create the `data/` directory with empty files: `users.txt`, `coursestxt`, `assignments.txt`, `discussion_posts.txt`.

### 2. Compile the Code
- Open a terminal in the `OnlineLearningSystem` directory:
  ```
  cd C:\Users\suhas b s\Desktop\java project\OnlineLearningSystem
  ```
- **On Windows (PowerShell)**:
  ```powershell
  javac -d bin (Get-ChildItem -Path src\main\java -Recurse -Include *.java).FullName
  ```
- **On Windows (Command Prompt)**:
  ```bash
  javac -d bin src\main\java\model\*.java src\main\java\service\*.java src\main\java\storage\*.java src\main\java\main\*.java
  ```
- **On Unix-like Systems (Linux/macOS)**:
  ```bash
  javac -d bin src/main/java/*/*.java src/main/java/main/*.java
  ```
- This creates compiled `.class` files in the `bin/` directory.

> **Tip**: If compilation fails, ensure all Java files have correct package declarations (e.g., `package model;` for `User.java`).

### 3. Run the Application
- Run the main class:
  ```bash
  java -cp bin main.Main
  ```
- Alternatively, use an IDE:
  - Import the project into IntelliJ IDEA or Eclipse.
  - Set `src/main/java` as the source root.
  - Run `Main.java` directly.

---

## 🎮 Usage

Upon running, you'll see the main menu:

```
=== Online Learning System ===
1. Login
2. Register
3. Exit
Choose an option:
```

### 🔐 Login or Register
- **Register**: Choose option 2, enter a username, password, and role (`Admin`, `Instructor`, or `Student`).
- **Login**: Choose option 1, enter your credentials.

### 👩‍💼 Admin Actions
- View all available courses.

### 👨‍🏫 Instructor Actions
- Create new courses with titles and content.
- View your courses.
- Grade student assignments.
- Post and view messages in course discussion forums.

### 👩‍🎓 Student Actions
- Browse and enroll in courses.
- View enrolled courses and their content.
- Submit assignments.
- Check assignment grades.
- Participate in course discussion forums.

### 🚪 Exit
- Select the logout option or exit the application.

---

## 📝 Notes
- **Data Storage**: All data (users, courses, assignments, posts) is stored in text files in the `data/` directory.
- **No Dependencies**: The project is self-contained and requires no external libraries.
- **Extensibility**: Easily extend the system by adding features like a GUI or database integration.

---

## 🛠 Troubleshooting

- **File Not Found**:
  - Ensure the `data/` directory exists with `users.txt`, `courses.txt`, `assignments.txt`, and `discussion_posts.txt`.
- **Compilation Errors**:
  - Verify that all `.java` files are in the correct directories (`model`, `service`, `storage`, `main`).
  - Check package declarations (e.g., `package model;` for `User.java`).
  - Use the PowerShell command:
    ```powershell
    javac -d bin (Get-ChildItem -Path src\main\java -Recurse -Include *.java).FullName
    ```
  - If you see "private access" errors, ensure methods like `writeToFile` in `FileStorage.java` are `public`.
- **Runtime Errors**:
  - Confirm that the `data/` directory is writable.
  - Delete the `bin/` directory and recompile:
    ```powershell
    Remove-Item -Recurse -Force bin
    ```
- **Windows Issues**:
  - Use PowerShell or Command Prompt commands as shown above.
  - Consider using an IDE to avoid command-line complexities.

---

## 🌟 Contributing
Want to enhance the project? Feel free to:
- Add a graphical user interface (e.g., with JavaFX or Swing).
- Integrate a database (e.g., MySQL or SQLite) for robust storage.
- Implement additional features like quizzes or video streaming.

Submit pull requests or share ideas to make this LMS even better!

---
