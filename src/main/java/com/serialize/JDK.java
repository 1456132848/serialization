package com.serialize;

import com.linmx.Person;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JDK {
    public static void main(String[] args) {
        serialize();
        deSerialize();
    }

    @SneakyThrows
    public static void serialize(){
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("a.txt"));
        Person person = new Person();
        person.setName("linmx");
        person.setAge(17);
        outputStream.writeObject(person);
        outputStream.close();
    }

    @SneakyThrows
    public static void deSerialize(){
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("a.txt"));
        Person person = (Person) inputStream.readObject();
        inputStream.close();
        System.out.println(person);
    }
}
