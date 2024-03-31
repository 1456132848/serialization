package com.nio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChargenServer {
    public static int port = 19;

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Listening for connections on port " + port);
        byte[] rotation = new byte[95 * 2];
        for (byte i = ' '; i <= '~'; i++) {
            rotation[i - ' '] = i;
            rotation[i + 95 - ' '] = i;
        }
        ServerSocketChannel serverSocketChannel;
        Selector selector;
        serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        socket.bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel serve = (ServerSocketChannel) key.channel();
                    SocketChannel client = serve.accept();
                    System.out.println("Accepted connection from " + client);
                    client.configureBlocking(false);
                    SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
                    ByteBuffer byteBuffer = ByteBuffer.allocate(74);
                    byteBuffer.put(rotation, 0, 72);
                    byteBuffer.put((byte) '\r');
                    byteBuffer.put((byte) '\n');
                    byteBuffer.flip();
                    key2.attach(byteBuffer);
                } else if (key.isWritable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    if (!buffer.hasRemaining()) {
                        //用下一行填充缓冲区
                        buffer.rewind();
                        byte first = buffer.get();
                        buffer.rewind();
                        int position = first - ' ' + 1;
                        buffer.put(rotation,position,72);
                        buffer.put((byte) '\r');
                        buffer.put((byte) '\n');
                        buffer.flip();
                    }
                    client.write(buffer);
                }
            }
        }
    }
}
