package com.serialize;

import com.serialize.data.Person;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ProtoStuff {
    public static void main(String[] args) {
        serialize();
        deSerialize();
    }

    @SneakyThrows
    public static void serialize(){
        Person person = new Person();
        person.setName("linmx");
        person.setAge(18);
        Schema<Person> schema = RuntimeSchema.getSchema(Person.class);
        // Re-use (manage) this buffer to avoid allocating on every serialization
        FileOutputStream outputStream = new FileOutputStream("a.txt");
        LinkedBuffer buffer = LinkedBuffer.allocate(512);
        ProtostuffIOUtil.writeTo(outputStream,person, schema, buffer);
        buffer.clear();
        outputStream.close();
    }

    @SneakyThrows
    public static void deSerialize(){
        Schema<Person> schema = RuntimeSchema.getSchema(Person.class);
        Person person = schema.newMessage();
        FileInputStream inputStream = new FileInputStream("a.txt");
        ProtostuffIOUtil.mergeFrom(inputStream, person, schema);
        inputStream.close();
        System.out.println(person);
    }
}
