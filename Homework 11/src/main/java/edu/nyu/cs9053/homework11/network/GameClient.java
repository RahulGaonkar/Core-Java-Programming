package edu.nyu.cs9053.homework11.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import edu.nyu.cs9053.homework11.game.Difficulty;
import edu.nyu.cs9053.homework11.game.GameProvider;
import edu.nyu.cs9053.homework11.game.screen.InputMove;

/**
 * User: blangel
 * 
 * A blocking IO implementation of a client which requests moves from a remote
 * server implementing the
 * {@linkplain edu.nyu.cs9053.homework11.network.NetworkGameProvider}
 */
public class GameClient implements GameProvider {

    private final Difficulty difficulty;
    private final InputStream serverInput;
    private final OutputStream serverOutput;

    public static GameClient construct(Difficulty difficulty) {
        Socket gameClientSocket = null;
        InputStream serverInput = null;
        OutputStream serverOutput = null;
        try {
            gameClientSocket = new Socket(GameServer.SERVER_HOST, GameServer.SERVER_PORT);
            serverInput = gameClientSocket.getInputStream();
            serverOutput = gameClientSocket.getOutputStream();
        } catch (IOException e) {
            if (gameClientSocket != null) {
                try {
                    gameClientSocket.close();
                } catch (IOException ioe) {

                }
            }
        }
        return new GameClient(difficulty, serverInput, serverOutput);
    }

    public GameClient(Difficulty difficulty, InputStream serverInput, OutputStream serverOutput) {
        this.difficulty = difficulty;
        this.serverInput = serverInput;
        this.serverOutput = serverOutput;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getRandomNumberOfNextFoes() {
        String foesDifficultyCommand = "foes " + difficulty.name();
        byte[] foesDifficultyCommandByteArray = foesDifficultyCommand.getBytes(StandardCharsets.UTF_8);
        byte[] inputNumberOfNextFoesBuffer = new byte[1024];
        try {
            serverOutput.write(foesDifficultyCommandByteArray);
            serverOutput.flush();
            serverInput.read(inputNumberOfNextFoesBuffer, 0, inputNumberOfNextFoesBuffer.length);
        } catch (IOException e) {
            try {
                serverOutput.close();
                serverInput.close();
            } catch (IOException ioe) {

            }
        }
        String readInputNumberOfNextFoes = new String(inputNumberOfNextFoesBuffer, StandardCharsets.UTF_8).trim();
        return Integer.parseInt(readInputNumberOfNextFoes);
    }

    public InputMove getRandomNextMove() {
        String inputMoveCommand = "move";
        byte[] inputMoveCommandByteArray = inputMoveCommand.getBytes(StandardCharsets.UTF_8);
        byte[] inputMoveBuffer = new byte[1024];
        try {
            serverOutput.write(inputMoveCommandByteArray);
            serverOutput.flush();
            serverInput.read(inputMoveBuffer, 0, inputMoveBuffer.length);
        } catch (IOException e) {
            try {
                serverOutput.close();
                serverInput.close();
            } catch (IOException ioe) {

            }
        }
        String readInputMove = new String(inputMoveBuffer, StandardCharsets.UTF_8).trim();
        return randomNextMoveChecker(readInputMove);
    }

    private InputMove randomNextMoveChecker(String readInputMove) {
        switch (readInputMove) {
        case "Up":
            return InputMove.Up;
        case "Down":
            return InputMove.Down;
        case "Left":
            return InputMove.Left;
        case "Right":
            return InputMove.Right;
        default:
            return null;
        }
    }
}