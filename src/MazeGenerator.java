import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class MazeGenerator {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What are the dimensions of the maze?");

        System.out.print("Width: ");
        int maxY = scanner.nextInt();

        System.out.print("Height: ");
        int maxX = scanner.nextInt();

        Maze maze = new Maze(maxX, maxY);

        generateRandomMaze(maze);

        System.out.println(maze);

        writeMazeToFile(maze);
    }

    public static void generateRandomMaze(Maze maze) {
        int numClusters = maze.getSize();

        Random rand = new Random();

        while (numClusters > 1) {
            int randomIndex = rand.nextInt(maze.getSize());
            ArrayList<Direction> directions = maze.getValidDirections(randomIndex);
            int randomDirection = rand.nextInt(directions.size());

            if (maze.connectPoints(randomIndex, directions.get(randomDirection))) {
                numClusters--;
            }
        }
    }

    public static void writeMazeToFile(Maze maze) {
        try {
            FileWriter fp = new FileWriter("C:\\Users\\Caleb\\Downloads\\maze.txt");
            fp.write(maze.fileToString());
            fp.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
