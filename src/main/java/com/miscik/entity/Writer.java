package com.miscik.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "writer")
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "writer",  cascade = {CascadeType.ALL})
    private List<Book> books;

    public Writer() {
        this.books = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        Comparator yearComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                if (book1.getYear() < book2.getYear()) return -1;
                else if (book1.getYear() > book2.getYear()) return 1;
                else return 0;
            }
        };
        Collections.sort(books, yearComparator);
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


}
