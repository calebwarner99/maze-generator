public class Point {
    private static int maxX;
    private static int maxY;
    private int x;
    private int y;
    private int index;
    private Point parent;
    private int unionSize;

    private boolean north;
    private boolean south;
    private boolean east;
    private boolean west;

    Point() {
        this(0, 0);
    }

    Point(int index) {
        this(index / maxY, index % maxY);
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;

        this.index = x * (maxY - 1) + y; //Minus one because index starts at 0 and dimension starts at 1
        this.parent = this;
        this.unionSize = 1;

        this.north = false;
        this.south = false;
        this.east = false;
        this.west = false;
    }


    //Getters
    public int getIndex() {
        return this.index;
    }

    public int getUnionSize() {
        return this.unionSize;
    }

    public boolean getNorth() {
        return this.north;
    }

    public boolean getSouth() {
        return this.south;
    }

    public boolean getEast() {
        return this.east;
    }

    public boolean getWest() {
        return this.west;
    }

    public Point getParent() {
        return this.parent;
    }

    public Point getRoot() {
        Point tempPoint = this;

        while (!tempPoint.equals(tempPoint.getParent())) {
            tempPoint = tempPoint.getParent();
        }

        return tempPoint;
    }


    //Setters
    public void setUnionSize(int unionSize) {
        this.unionSize = unionSize;
    }

    public void setNorth() {
        this.north = true;
    }

    public void setSouth() {
        this.south = true;
    }

    public void setEast() {
        this.east = true;
    }

    public void setWest() {
        this.west = true;
    }

    public void setMaxX(int maxX) {
        Point.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        Point.maxY = maxY;
    }

    public void setParent(Point parent) {
        this.parent = parent;
    }

    public boolean equals(Point point) {
        return (this.x == point.x && this.y == point.y && this.index == point.index && this.unionSize == point.unionSize);
    }
}
