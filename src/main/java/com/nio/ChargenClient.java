package com.nio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {
    public static int port = 19;

    @SneakyThrows
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
        ByteBuffer buffer = ByteBuffer.allocate(74);
        WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
        while (socketChannel.read(buffer) != -1){
            buffer.flip();
            writableByteChannel.write(buffer);
            buffer.clear();
            Thread.sleep(1000);
        }
    }
}
