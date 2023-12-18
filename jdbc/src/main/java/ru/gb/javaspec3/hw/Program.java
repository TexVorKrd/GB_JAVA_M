package ru.gb.javaspec3.hw;

import ru.gb.javaspec3.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Program {
    public static void main(String[] args) {
        /**
         Создайте базу данных (например, SchoolDB).
         В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
         Настройте Hibernate для работы с вашей базой данных.
         Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
         Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
         Убедитесь, что каждая операция выполняется в отдельной транзакции.
         */

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "12345";

        try {
            // Подключение к базе данных
            Connection connection = DriverManager.getConnection(url, user, password);

            // Создание базы данных
            createDatabase(connection);
            System.out.println("Database created successfully");

            // Использование базы данных
            useDatabase(connection);
            System.out.println("Use database successfully");

            // Создание таблицы
            createTable(connection);
            System.out.println("Create table successfully");

            Course course1 = new Course("ООП", 10);
            Course course2 = new Course("Collection", 5);
            Course course3 = new Course("Многопоточность", 20);
            Course course4 = new Course("Патерны программирования", 15);


            // Добавление данных

            insertData(connection, course1);
            insertData(connection, course2);
            insertData(connection, course3);
            insertData(connection, course4);


            // Чтение данных
            Collection<Course> courses = readData(connection);
            for (var course : courses)
                System.out.println(course);
            System.out.println("Read data successfully");

            // Обновление данных

            Course course = courses.stream().findAny().orElse(null);
            if (course != null) {
                course.setTitle("Не ООП");
                updateData(connection, course);
                System.out.println("Update data successfully");
            } else System.out.println("Таблица пустая");


            // Чтение данных
            courses = readData(connection);
            for (var dbcourse : courses)
                System.out.println(dbcourse);
            System.out.println("Read data successfully");

            // Удаление данных
            for (var dbcourse : courses)
                deleteData(connection, dbcourse.getId());
            System.out.println("Delete data successfully");

            // Закрытие соединения
            connection.close();
            System.out.println("Database connection close successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void createDatabase(Connection connection) throws SQLException {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS SchoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)) {
            statement.execute();
        }
    }

    private static void useDatabase(Connection connection) throws SQLException {
        String useDatabaseSQL = "USE SchoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
            statement.execute();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), duration INT);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }

    /**
     * Добавление данных в таблицу Courses
     *
     * @param connection Соединение с БД
     * @param course     Курс
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void insertData(Connection connection, Course course) throws SQLException {
        String insertDataSQL = "INSERT INTO Courses (title, duration) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getDuration());
            statement.executeUpdate();
        }
    }

    /**
     * Чтение данных из таблицы Courses
     *
     * @param connection Соединение с БД
     * @return Коллекция Курсов
     * @throws SQLException Исключение при выполнении запроса
     */
    private static Collection<Course> readData(Connection connection) throws SQLException {
        ArrayList<Course> studentsList = new ArrayList<>();
        String readDataSQL = "SELECT * FROM Courses;";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int duration = resultSet.getInt("duration");
                studentsList.add(new Course(id, title, duration));
            }
            return studentsList;
        }
    }

    /**
     * Обновление данных в таблице Courses по идентификатору
     *
     * @param connection Соединение с БД
     * @param course     Курс
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void updateData(Connection connection, Course course) throws SQLException {
        String updateDataSQL = "UPDATE Courses SET title=?, duration=? WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(updateDataSQL)) {
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getDuration());
            statement.setInt(3, course.getId());
            statement.executeUpdate();
        }
    }

    /**
     * Удаление записи из таблицы Courses по идентификатору
     *
     * @param connection Соединение с БД
     * @param id         Идентификатор записи
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void deleteData(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM Courses WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}