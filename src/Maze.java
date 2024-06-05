import java.util.ArrayList;

public class Maze {
    private static final int unionNotRoot = -1;
    private static final char delimiter = '\u2593'; // A box
    private static final char space = ' ';

    private Point[] pointArray;
    private int maxX;
    private int maxY;

    private int size;

    Maze(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        Point dummy = new Point();
        dummy.setMaxX(maxX);
        dummy.setMaxY(maxY);

        this.pointArray = new Point[maxX * maxY];

        for (int i = 0; i < maxX * maxY; i++) {
            this.pointArray[i] = new Point(i);
        }

        this.size = maxX * maxY;
    }

    public int getMaxX() {
        return this.maxX;
    }

    public int getMaxY() {
        return this.maxY;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getSize() {
        return this.size;
    }

    public void union(Point first, Point second) { //Double check that this actually does the union
        Point firstRoot = first.getRoot();
        first.setParent(firstRoot);

        Point secondRoot = second.getRoot();
        second.setParent(secondRoot);

        if (secondRoot.getUnionSize() > firstRoot.getUnionSize()) {
            firstRoot.setParent(secondRoot);
            secondRoot.setUnionSize(secondRoot.getUnionSize() + firstRoot.getUnionSize());
            firstRoot.setUnionSize(unionNotRoot);
        } else {
            secondRoot.setParent(firstRoot);
            firstRoot.setUnionSize(firstRoot.getUnionSize() + secondRoot.getUnionSize());
            secondRoot.setUnionSize(unionNotRoot);
        }
    }

    public ArrayList<Direction> getValidDirections(int index) {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        Point point = pointArray[index];

        if (index - maxY > 0) {
            directions.add(Direction.NORTH);
        }

        if (index + maxY < this.size) {
            directions.add(Direction.SOUTH);
        }

        if ((index + 1) % maxY != 0) {
            directions.add(Direction.EAST);
        }

        if (index % maxY != 0) {
            directions.add(Direction.WEST);
        }

        return directions;
    }

    public boolean connectPoints(int firstIndex, Direction direction) {
        Point firstPoint = pointArray[firstIndex];
        int secondIndex = 0;

        if (direction == Direction.NORTH) {
            secondIndex = firstIndex - maxY;
        } else if (direction == Direction.SOUTH) {
            secondIndex = firstIndex + maxY;
        } else if (direction == Direction.EAST) {
            secondIndex = firstIndex + 1;
        } else if (direction == Direction.WEST) {
            secondIndex = firstIndex - 1;
        }

        Point secondPoint = pointArray[secondIndex];

        if (firstPoint.getRoot().equals(secondPoint.getRoot())) {
            return false;
        }

        this.union(firstPoint, secondPoint);

        if (direction == Direction.NORTH) {
            firstPoint.setNorth();
            secondPoint.setSouth();
        } else if (direction == Direction.SOUTH) {
            firstPoint.setSouth();
            secondPoint.setNorth();
        } else if (direction == Direction.EAST) {
            firstPoint.setEast();
            secondPoint.setWest();
        } else if (direction == Direction.WEST) {
            firstPoint.setWest();
            secondPoint.setEast();
        }

        return true;
    }

    public char[][] toCharArray() {
        char[][] result = new char[(maxX * 2) + 1][(maxY * 2) + 1];
        for (int i = 0; i < (maxY * 2) + 1; i++) {
            result[0][i] = delimiter;
        }

        int charArrayIndex = 1;
        int mazeIndex = 0;

        for (int j = 0; j < maxX; j++) {
            if (j == 0) {
                result[charArrayIndex][0] = space;
            } else {
                result[charArrayIndex][0] = delimiter;
            }

            for (int i = 1; i < (maxY * 2) + 1; i++) {
                result[charArrayIndex][i++] = space;
                if (pointArray[mazeIndex].getEast()) {
                    result[charArrayIndex][i] = space;
                } else {
                    if (j == maxX - 1 && i == maxY * 2) {
                        result[charArrayIndex][i] = space;
                    } else {
                        result[charArrayIndex][i] = delimiter;
                    }
                }
                mazeIndex++;
            }

            result[++charArrayIndex][0] = delimiter;
            mazeIndex -= maxY;

            for (int i = 1; i < (maxY * 2) + 1; i++) {
                if (pointArray[mazeIndex].getSouth()) {
                    result[charArrayIndex][i++] = space;
                } else {
                    result[charArrayIndex][i++] = delimiter;
                }

                result [charArrayIndex][i] = delimiter;
                mazeIndex++;
            }

            charArrayIndex++;
        }

        return result;
    }

    @Override
    public String toString() {
        char[][] charArray = this.toCharArray();
        String result = "";

        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {

                if (charArray[i][j] == space) {
                    result += '\u2591';
                } else {
                    result += delimiter;
                }
            }

            result += "\n";
        }

        return result;
    }

    public String fileToString() {
        char[][] charArray = this.toCharArray();
        String result = "";

        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                result += charArray[i][j];// + "\u2591";
                result += charArray[i][j];
            }

            result += "\n";
        }

        return result;
    }

    public String solveMaze() {
        return "";
    }
}