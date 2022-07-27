package eedenisov.example;

import eedenisov.example.model.Director;
import eedenisov.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;


/**
 * @author eedenisov
 */
public class App {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();


        try {
            session.beginTransaction();
//            Создадим нового режиссера и новый фильм, и свяжем эти сущности
            Director newDirector = new Director("Steven Spielberg", 52);
            Movie newMovie = new Movie("Jurassic Park", 1993, newDirector);

            newDirector.setMovies(new ArrayList<>(Collections.singletonList(newMovie)));

            session.save(newDirector);
            session.save(newMovie);

//            Добавим новый фильм для режиссера
//            Director director = session.get(Director.class, 4);
//            Movie newMovie = new Movie("Casino Royale", 1967, director);
//            director.getMovies().add(newMovie);
//            session.save(newMovie);

//            Поменяем режиссера у существующего фильма
//            Director director = session.get(Director.class, 9);
//            Movie movie = session.get(Movie.class, 12);
//            movie.getDirector().getMovies().remove(movie);
//            movie.setDirector(director);
//            director.getMovies().add(movie);
//            session.save(director);
//            session.save(movie);

//            Удалим фильм у режиссера
//            Movie movie = session.get(Movie.class, 12);
//            Director director = movie.getDirector();
//            director.getMovies().remove(movie);
//            session.remove(movie);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
