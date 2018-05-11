package com.miscik;

import com.miscik.entity.Book;
import com.miscik.entity.Writer;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        Writer writer = new Writer();
        writer.setFirstName("Erik");
        writer.setLastName("Hemingway");

        Book book = new Book();
        book.setName("Perinbaba2");
        book.setYear(2007);
        book.setWriter(writer);

        Book book2 = new Book();
        book2.setName("Nieco2");
        book2.setYear(2015);
        book2.setWriter(writer);
        Book book3 = new Book();
        book3.setName("Lalala17");
        book3.setYear(2007);
        book3.setWriter(writer);

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        books.add(book3);

        writer.setBooks(books);
        s.save(writer);

        System.out.println(writer.getLastName()+" Pocet knih: "+writer.getBooks().size());

        Writer writer2 = s.get(Writer.class, 4L);

        Criteria criteria = s.createCriteria(Writer.class);
        List<Writer> writers = (criteria.add(Restrictions.eq("firstName", "Erik")).list());
        System.out.println(writers.get(0).getFirstName()+" "+writers.get(0).getLastName());

        s.getTransaction().commit();
        s.close();
    }
}
