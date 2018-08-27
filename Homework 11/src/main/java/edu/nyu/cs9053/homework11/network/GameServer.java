package edu.nyu.cs9053.homework11.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import edu.nyu.cs9053.homework11.game.Difficulty;

/**
 * User: blangel
 * 
 * A NIO implementation of a NetworkGameProvider.
 * 
 * The server takes the following commands:
 * 
 * <pre>
 *     foes Difficulty
 * </pre>
 * 
 * <pre>
 * move
 * </pre>
 * 
 * where the String "foes Easy" would be a call to
 * {@linkplain NetworkGameProvider#getRandomNumberOfNextFoes(String)} with
 * "Easy" and a call using String "move" would be a call to
 * {@linkplain NetworkGameProvider#getRandomNextMove()}
 */
public class GameServer implements NetworkGameProvider, Runnable {
    
    public static final String SERVER_HOST = "localhost";
    
    public static final int SERVER_PORT = 8080;
    
    private final Selector gameServerSelector;
    
    private final ServerSocketChannel gameServerChannel;
    
    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
        server.run();
    }
    
    public GameServer() throws IOException {
        gameServerSelector = Selector.open();
        gameServerChannel = ServerSocketChannel.open();
        gameServerChannel.bind(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
        gameServerChannel.configureBlocking(false);
        gameServerChannel.register(gameServerSelector, SelectionKey.OP_ACCEPT);
    }
    
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int readyChannelCount = gameServerSelector.select();
                if (readyChannelCount == 0) {
                    continue;
                }
                Set<SelectionKey> readyChannelKeySet = gameServerSelector
                        .selectedKeys();
                Iterator<SelectionKey> readyChannelKeySetIterator = readyChannelKeySet
                        .iterator();
                while (readyChannelKeySetIterator.hasNext()) {
                    SelectionKey readyChannelKey = readyChannelKeySetIterator
                            .next();
                    if (readyChannelKey.isAcceptable()) {
                        acceptSocketChannel(readyChannelKey);
                    } else if (readyChannelKey.isReadable()) {
                        readSocketChannel(readyChannelKey);
                    }
                    readyChannelKeySetIterator.remove();
                }
            } catch (IOException e) {
                try {
                    gameServerSelector.close();
                } catch (IOException ioe) {
                    
                }
            }
        }
    }
    
    private void readSocketChannel(SelectionKey readyChannelKey) {
        SocketChannel gameClientChannel = null;
        try {
            gameClientChannel = (SocketChannel) readyChannelKey.channel();
            ByteBuffer readyGameClientChannelData = ByteBuffer.allocate(1024);
            gameClientChannel.read(readyGameClientChannelData);
            String readReadyGameClientChannelData = new String(
                    readyGameClientChannelData.array(), StandardCharsets.UTF_8)
                    .trim();
            functionCallCheckerBasedOnCommand(gameClientChannel,
                    readReadyGameClientChannelData);
        } catch (IOException e) {
            if (gameClientChannel != null) {
                try {
                    gameClientChannel.close();
                } catch (IOException ioe) {
                    
                }
            }
        }
    }
    
    private void acceptSocketChannel(SelectionKey readyChannelKey) {
        ServerSocketChannel gameServerChannel = null;
        SocketChannel gameClientChannel = null;
        try {
            gameServerChannel = ((ServerSocketChannel) readyChannelKey
                    .channel());
            gameClientChannel = gameServerChannel.accept();
        } catch (IOException e) {
            try {
                gameServerChannel.close();
            } catch (IOException ioe) {
                
            }
        }
        if (gameClientChannel != null) {
            try {
                gameClientChannel.configureBlocking(false);
                gameClientChannel.register(gameServerSelector,
                        SelectionKey.OP_READ);
            } catch (IOException e) {
                try {
                    gameClientChannel.close();
                } catch (IOException ioe) {
                    
                }
            }
        }
    }
    
    private void functionCallCheckerBasedOnCommand(
            SocketChannel gameClientChannel,
            String readReadyGameClientChannelData) throws IOException {
        switch (readReadyGameClientChannelData) {
            case "foes Easy":
                gameClientChannel.write(ByteBuffer
                        .wrap(getRandomNumberOfNextFoes("Easy").getBytes(
                                StandardCharsets.UTF_8)));
                break;
            case "foes Medium":
                gameClientChannel.write(ByteBuffer
                        .wrap(getRandomNumberOfNextFoes("Medium").getBytes(
                                StandardCharsets.UTF_8)));
                break;
            case "foes Hard":
                gameClientChannel.write(ByteBuffer
                        .wrap(getRandomNumberOfNextFoes("Hard").getBytes(
                                StandardCharsets.UTF_8)));
                break;
            case "move":
                gameClientChannel.write(ByteBuffer.wrap(getRandomNextMove()
                        .getBytes(StandardCharsets.UTF_8)));
                break;
        }
    }
    
    public String getRandomNumberOfNextFoes(String difficulty) {
        Random randomNumberOfNextFoesGenerator = new Random();
        int randomNumberOfNextFoes = 0;
        switch (difficulty) {
            case "Easy":
                randomNumberOfNextFoes = randomNumberOfNextFoesGenerator
                        .nextInt(Difficulty.Easy.getLevel() + 1);
                System.out.println("foes Easy --> " + randomNumberOfNextFoes);
                return String.valueOf(randomNumberOfNextFoes);
            case "Medium":
                randomNumberOfNextFoes = randomNumberOfNextFoesGenerator
                        .nextInt(Difficulty.Medium.getLevel() + 1);
                System.out.println("foes Medium --> " + randomNumberOfNextFoes);
                return String.valueOf(randomNumberOfNextFoes);
                
            case "Hard":
                randomNumberOfNextFoes = randomNumberOfNextFoesGenerator
                        .nextInt(Difficulty.Hard.getLevel() + 1);
                System.out.println("foes Hard --> " + randomNumberOfNextFoes);
                return String.valueOf(randomNumberOfNextFoes);
                
            default:
                return null;
        }
    }
    
    public String getRandomNextMove() {
        Random randomNextMoveGenerator = new Random();
        if (randomNextMoveGenerator.nextInt(100) <= 49) {
            if (randomNextMoveGenerator.nextInt(100) <= 49) {
                System.out.println("move --> Up");
                return "Up";
            } else {
                System.out.println("move --> Down");
                return "Down";
            }
        } else {
            if (randomNextMoveGenerator.nextInt(100) <= 94) {
                System.out.println("move --> Left");
                return "Left";
            } else {
                System.out.println("move --> Right");
                return "Right";
            }
        }
    }
}