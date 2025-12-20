<h1>BookMyShow Backend API (Spring Boot)</h1>

<p>A simple backend API built using <strong>Java Spring Boot</strong> for managing Movies, Theatres, Shows, Seats, and Ticket Booking — similar to BookMyShow.</p>

<hr>

<h2>🚀 Tech Stack</h2>
<ul>
  <li><strong>Language:</strong> Java</li>
  <li><strong>Framework:</strong> Spring Boot</li>
  <li><strong>IDE:</strong> IntelliJ IDEA</li>
  <li><strong>Database:</strong> MySQL / PostgreSQL / MongoDB</li>
  <li><strong>Build Tool:</strong> Maven / Gradle</li>
  <li><strong>Security (Optional):</strong> Spring Security + JWT</li>
</ul>

<hr>

<h2>📁 Project Structure</h2>
<pre>
src/
 ├── controller
 ├── service
 ├── repository
 ├── model
 ├── dto
 ├── config
 └── exception
</pre>

<hr>

<h2>🎬 Features</h2>
<ul>
  <li>Manage Movies (Add, Update, Delete, List)</li>
  <li>Manage Theatres & Screens</li>
  <li>Create Shows with time, date, and movie mapping</li>
  <li>Seat availability check</li>
  <li>Ticket booking with seat lock</li>
  <li>User authentication (optional)</li>
  <li>Clean layered architecture</li>
</ul>

<hr>

<h2>📌 Basic API Endpoints</h2>

<h3>🎬 Movies</h3>
<table border="1" cellspacing="0" cellpadding="5">
  <tr><th>Method</th><th>Endpoint</th><th>Description</th></tr>
  <tr><td>GET</td><td>/api/movies</td><td>Get all movies</td></tr>
  <tr><td>POST</td><td>/api/movies</td><td>Add movie</td></tr>
  <tr><td>GET</td><td>/api/movies/{id}</td><td>Get movie by ID</td></tr>
  <tr><td>PUT</td><td>/api/movies/{id}</td><td>Update movie</td></tr>
  <tr><td>DELETE</td><td>/api/movies/{id}</td><td>Delete movie</td></tr>
</table>

<h3>🏢 Theatres</h3>
<table border="1" cellspacing="0" cellpadding="5">
  <tr><th>Method</th><th>Endpoint</th><th>Description</th></tr>
  <tr><td>POST</td><td>/api/theatres</td><td>Add theatre</td></tr>
  <tr><td>GET</td><td>/api/theatres</td><td>List theatres</td></tr>
</table>

<h3>🕒 Shows</h3>
<table border="1" cellspacing="0" cellpadding="5">
  <tr><th>Method</th><th>Endpoint</th><th>Description</th></tr>
  <tr><td>POST</td><td>/api/shows</td><td>Create show</td></tr>
  <tr><td>GET</td><td>/api/shows?movieId=</td><td>Get shows by movie</td></tr>
</table>

<h3>💺 Seats</h3>
<table border="1" cellspacing="0" cellpadding="5">
  <tr><th>Method</th><th>Endpoint</th><th>Description</th></tr>
  <tr><td>GET</td><td>/api/shows/{id}/seats</td><td>Seat availability</td></tr>
</table>

<h3>🧾 Booking</h3>
<table border="1" cellspacing="0" cellpadding="5">
  <tr><th>Method</th><th>Endpoint</th><th>Description</th></tr>
  <tr><td>POST</td><td>/api/bookings</td><td>Book seats</td></tr>
</table>

<hr>

<h2>🔧 How to Run (IntelliJ IDEA)</h2>

<ol>
  <li>Clone the repository:
    <pre>git clone &lt;repo-url&gt;</pre>
  </li>
  <li>Open the project in <strong>IntelliJ IDEA</strong></li>
  <li>Update database config in <code>application.properties</code></li>
  <li>Run the application:
    <pre>mvn spring-boot:run</pre>
  </li>
</ol>

<hr>

<h2>⚙️ application.properties Example</h2>
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookmyshow
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
<hr>

<h2>🛠️ Future Enhancements</h2>
<ul>
  <li>Payment integration</li>
  <li>Microservice architecture</li>
  <li>Notification service</li>
  <li>Admin dashboard</li>
  <li>Seat-lock expiry system</li>
</ul>
