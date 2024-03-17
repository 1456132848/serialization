package com.serialize;

import com.linmx.KryoPerson;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ProtoBuf {
    public static void main(String[] args) {
        serialize();
        deSerialize();
    }

    @SneakyThrows
    public static void serialize(){
        FileOutputStream outputStream = new FileOutputStream("a.txt");
        KryoPerson.Person person = KryoPerson.Person.newBuilder()
                        .setName("John Doe")
                        .setAge(30).build();
        person.writeTo(outputStream);
        outputStream.close();
    }

    @SneakyThrows
    public static void deSerialize(){
        FileInputStream inputStream = new FileInputStream("a.txt");
        KryoPerson.Person person = KryoPerson.Person.parseFrom(inputStream);
        inputStream.close();
        System.out.println(person);
    }
}
