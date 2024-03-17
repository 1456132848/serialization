package com.serialize;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.linmx.Person;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Hessian2 {
    public static void main(String[] args) {
        serialize();
        deSerialize();
    }

    @SneakyThrows
    public static void serialize(){
        Person person = new Person();
        person.setName("linmx");
        person.setAge(17);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("a.txt"));
        Hessian2Output output = new Hessian2Output(outputStream);
        output.writeObject(person);
        output.close();
        outputStream.close();
    }

    @SneakyThrows
    public static void deSerialize(){
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("a.txt"));
        Hessian2Input input = new Hessian2Input(inputStream);
        Person person = (Person) input.readObject();
        System.out.println(person);
        inputStream.close();
        input.close();
    }
}
