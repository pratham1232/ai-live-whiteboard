🎨 StudioBoard Pro
StudioBoard Pro is a high-end, real-time collaborative whiteboard application built with Spring Boot, WebSockets, and PostgreSQL. It allows multiple users to draw simultaneously in a shared workspace with a focus on professional UI/UX and data persistence.
![alt text](https://img.shields.io/badge/version-1.0.0-blue)

![alt text](https://img.shields.io/badge/backend-Spring%20Boot%203.x-green)

![alt text](https://img.shields.io/badge/database-PostgreSQL-blue)

![alt text](https://img.shields.io/badge/frontend-Vanilla%20JS-yellow)
✨ Key Features
Real-time Collaboration: Instant stroke synchronization across all connected clients using WebSockets (STOMP/SockJS).
Manual Cloud Sync: Efficiency-first logic. Draw freely in real-time and save to the PostgreSQL database only when you're ready.
Dynamic Tool Cursors: Custom pencil and eraser icons that follow your mouse, providing a "Ghost Cursor" experience.
High-DPI Support: Crisp, sharp lines on Retina and 4K displays using devicePixelRatio scaling.
Glassmorphism UI: A sleek, modern "SaaS-style" interface with floating tool docks and dark-mode workspace.
Persistent History: Automatically loads previous work from the database upon page refresh.
Professional Tools: Undo functionality, Canvas-to-PNG export, and quick-access color swatches.
🛠️ Tech Stack
Backend
Java 17+
Spring Boot 3.x
Spring WebSocket: For real-time STOMP messaging.
Spring Data JPA: For database interaction.
PostgreSQL: Reliable persistence of whiteboard strokes.
Lombok: To reduce boilerplate code.
Frontend
HTML5 Canvas: High-performance drawing engine.
Vanilla JavaScript: ES6+ logic for drawing and socket handling.
CSS3: Modern Glassmorphic UI with Backdrop Filters.
Lucide Icons: Clean, professional iconography.
SockJS & StompJS: For robust WebSocket communication.
🚀 Getting Started
Prerequisites
JDK 17 or higher.
Maven 3.6+.
PostgreSQL installed and running.
1. Database Setup
Create a database in PostgreSQL:
code
SQL
CREATE DATABASE whiteboard_db;
2. Configure Environment
Update your src/main/resources/application.properties:
code
Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/whiteboard_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
3. Build and Run
code
Bash
mvn clean install
mvn spring-boot:run
The application will be available at http://localhost:8080.
📡 API & WebSocket Endpoints
Type	Endpoint	Description
WS (Pub)	/app/draw	Sends local stroke data to the server.
WS (Sub)	/topic/draw	Receives real-time strokes from other users.
GET	/strokes	Fetches all saved stroke history from PostgreSQL.
POST	/strokes/save	Batches unsaved points into the database.
POST	/strokes/clear-db	(Optional) Wipes the drawing history.
📐 Data Model
A Stroke consists of:
x, y: Coordinates on the canvas.
color: Hex code of the stroke.
size: Thickness of the brush.
type: Either START (pen down) or MOVE (drawing), used to prevent lines from connecting incorrectly during history reconstruction.
🖥️ UI Screenshots
Feature	Description
Left Dock	Quick access to Pencil, Eraser, Undo, and Clear tools.
Bottom Tray	Color swatches and brush size slider.
Status Badge	Real-time "Synced" or "Unsaved" cloud status indicator.
Ghost Cursor	Tool-specific icons that replace the standard mouse pointer.
📜 License
This project is for educational purposes and is open for further customization. Feel free to fork and improve!
