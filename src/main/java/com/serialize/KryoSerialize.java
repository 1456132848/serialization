package com.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.serialize.data.Person;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class KryoSerialize {
    public static void main(String[] args) {
        serialize();
        deSerialize();
    }

    @SneakyThrows
    public static void serialize(){
        Kryo kryo = new Kryo();
        kryo.register(Person.class);
        Person person = new Person();
        person.setName("linmx");
        person.setAge(17);
        Output output = new Output(new FileOutputStream("a.txt"));
        kryo.writeObject(output, person);
        output.close();
    }

    @SneakyThrows
    public static void deSerialize(){
        Kryo kryo = new Kryo();
        kryo.register(Person.class);
        Input input = new Input(new FileInputStream("a.txt"));
        Person person = kryo.readObject(input, Person.class);
        input.close();
        System.out.println(person);
    }
}
