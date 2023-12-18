package ru.gb.javaspec3.hw;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gb.javaspec3.models.Course;

public class ProgrammHN {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();


        Course course = new Course();
        Course course1 = new Course("ООП", 10);
        Course course2 = new Course("Collection", 5);
        Course course3 = new Course("Многопоточность", 20);
        Course course4 = new Course("Патерны программирования", 15);

        // Создание сессии
        Session session = sessionFactory.getCurrentSession();

        try {
            // Начало транзакции
            session.beginTransaction();

            // Сохранение объекта в базе данных
            session.save(course1);
            session.save(course2);
            session.save(course3);
            session.save(course4);
            ;
            System.out.println("Object course save successfully");

            // Чтение объекта из базы данных
            Course retrievedCourse = session.get(Course.class, course1.getId());
            System.out.println("Object course retrieved successfully");
            System.out.println("Retrieved course object: " + retrievedCourse);

            // Обновление объекта
            retrievedCourse.setTitle("Новый курс");
            retrievedCourse.setDuration(20);
            session.update(retrievedCourse);
            System.out.println("Object course update successfully");

            // Удаление объекта
            session.delete(course2);
            System.out.println("Object student delete successfully");

            session.getTransaction().commit();
            System.out.println("Transaction commit successfully");
        } finally {
            // Закрытие фабрики сессий
            sessionFactory.close();
        }
    }
}
