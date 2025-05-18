#📚 Library Management System
A Java-based Library Management System developed as a final project for the Data Structures and Algorithms course.

#🔍 Overview
This system is designed to automate common library tasks such as managing books, users, and borrow/return records. The application uses a Java Swing GUI for the frontend and MySQL for backend data persistence.

It is built to demonstrate core data structure and algorithm concepts while being practical and user-friendly.

#🎯 Objectives
Build a responsive desktop interface for library administrators.

Handle book, user, and borrow record management with real-time updates.

Integrate Java data structures and algorithms into functional GUI operations.

Automate due date tracking and overdue fines.

🛠️ Tools & Technologies
Language: Java

Frontend: Java Swing

Backend: MySQL

Build Tool: Apache Maven

IDE: NetBeans 21

🧩 Features
✅ Book Management
Add, update, delete, search books

Real-time inventory tracking

👤 User Management
Add, update, delete, search user records

🔁 Borrow/Return Tracking
Record borrow events and return dates

Fine calculation for overdue books

🔐 Authentication
Admin login system for access control

🧠 Data Structures & Design
List<Map<String, Object>> – to store and manipulate books, users, and borrow records

HashMap – used for key-value mapping of record fields

Queue – tracks pending operations like add, delete, update

DefaultTableModel – binds backend data to frontend tables dynamically

Java Streams & higher-order functions – enable efficient filtering, mapping, and searching

🗃️ Database Schema
Books Table – title, author, genre, ISBN, available copies

Users Table – user details and membership info

Borrow Records – borrow date, return date, due date, fines

Admin Table – credentials for login access

🚀 How It Works
Data is fetched from MySQL on startup and loaded into memory.

All operations (add/update/delete) modify in-memory lists.

Changes are tracked using a queue system and periodically synced with the database.
