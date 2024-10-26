import java.util.SplittableRandom;

public class Plot {
	private int xCoordinate;
	private int yCoordinate;
	private int depth;
	private int width;
	private int[][] coordinates;
	
	Plot(){
		xCoordinate = 0;
		yCoordinate = 0;
		depth = 1;
		width = 1;
		coordinates = setCoordinates(xCoordinate,yCoordinate, width,depth);
	}
	
	Plot(int x, int y, int width, int depth){
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.width = width;
		this.depth = depth;
		coordinates = setCoordinates(x,y, width,depth);
	}
	
	Plot(Plot plot){
		this.xCoordinate = plot.getX();
		this.yCoordinate = plot.getY();
		this.width = plot.getWidth();
		this.depth = plot.getDepth();
		coordinates = setCoordinates(plot.getX(),plot.getY(), plot.getWidth(),plot.getDepth());
	}
	
	public int getX() {
		return this.xCoordinate;
	}
	
	public int getY() {
		return this.yCoordinate;
	}
	
	public int getDepth() {
		return this.depth;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setX(int x) {
		this.xCoordinate = x;
	}
	
	public void setY(int y) {
		this.yCoordinate = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public boolean overlaps(Plot plot) {
		int timesOnEdge = 0;
		int coordsEqual = 0;
		
		int[][] otherSquare = setCoordinates(plot.getX(), plot.getY(), plot.getWidth(), plot.getDepth());
		
		for(int i = 0; i < coordinates.length; i++) {
			if(pointOnEdge(otherSquare[i][0], otherSquare[i][1])) {
				timesOnEdge++;
			}
		}
		
		for(int i = 0; i < coordinates.length; i++) {
			if(otherSquare[i][0] == coordinates[i][0] && otherSquare[i][1] == coordinates[i][1]) {
				coordsEqual++;
			}
		}
		
		if(timesOnEdge >= 2 && coordsEqual < 2) {
			return true;
		}
		
		for(int i = 0; i < coordinates.length; i++) {
			if(pointInPlot(otherSquare[i][0], otherSquare[i][1])) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean encompasses(Plot plot) {
		int[][] otherSquare = setCoordinates(plot.getX(), plot.getY(), plot.getWidth(), plot.getDepth());
		
		boolean cond1 = pointInPlot(otherSquare[0][0], otherSquare[0][1]);
		boolean cond2 = pointInPlot(otherSquare[1][0], otherSquare[1][1]);
		boolean cond3 = pointInPlot(otherSquare[2][0], otherSquare[2][1]);
		boolean cond4 = pointInPlot(otherSquare[3][0], otherSquare[3][1]);
		
		
		if(cond1 && cond2 && cond3 && cond4) {
			return true;
		}
		
		return false;
	}
	
	public void printCoordinates(int x, int y) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(
					(coordinates[0][0] == j && coordinates[0][1] == i) ||
					(coordinates[1][0] == j && coordinates[1][1] == i) ||
					(coordinates[2][0] == j && coordinates[2][1] == i) ||
					(coordinates[3][0] == j && coordinates[3][1] == i) ||
					(x == j && y == i))
				{
					System.out.print("*");
				}else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
	
	public void printCoordinates(int[][] square) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(
					(coordinates[0][0] == j && coordinates[0][1] == i) ||
					(coordinates[1][0] == j && coordinates[1][1] == i) ||
					(coordinates[2][0] == j && coordinates[2][1] == i) ||
					(coordinates[3][0] == j && coordinates[3][1] == i) ||
					(square[0][0] == j && square[0][1] == i) ||
					(square[1][0] == j && square[1][1] == i) ||
					(square[2][0] == j && square[2][1] == i) ||
					(square[3][0] == j && square[3][1] == i))
				{
					System.out.print("*");
				}else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
	
	public boolean pointOnEdge(int x, int y) {
		int xBoundLow = coordinates[2][0];
		int xBoundHigh = coordinates[3][0];
		int yBoundLow = coordinates[2][1];
		int yBoundHigh = coordinates[0][1];

		for(int i = 0; i < coordinates.length;i++) {
			if(coordinates[i][0] == x) {
				if(y > yBoundLow && y < yBoundHigh) {
					return true;
				}
			}
		}
		
		for(int i = 0; i < coordinates.length; i++) {
			if(coordinates[i][1] == y) {
				if(x > xBoundLow && x < xBoundHigh) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean pointInPlot(int x, int y) {
		boolean cond1 = coordinates[0][0] < x && coordinates[0][1] > y;
		boolean cond2 = coordinates[1][0] > x && coordinates[1][1] > y;
		boolean cond3 = coordinates[2][0] < x && coordinates[2][1] < y;
		boolean cond4 = coordinates[3][0] > x && coordinates[3][1] < y;
		
		if(cond1 && cond2 && cond3 && cond4)
		{
			return true;
		}else {
			return false;
		}
	}
	
	public int[][] setCoordinates(int xCoord, int yCoord, int width, int depth) {
		int[][] toReturn = new int[][] {
			{xCoord, yCoord + depth},
			{xCoord + width, yCoord + depth},
			{xCoord, yCoord},
			{xCoord + width, yCoord}
		};
		
		return toReturn;
	}
	
	public void printCoords(int[][] coords) {
		System.out.println(
				"[" + coords[0][0] + "," + coords[0][1] + "]" +
				"[" + coords[1][0] + "," + coords[1][1] + "]" +
				"[" + coords[2][0] + "," + coords[2][1] + "]" +
				"[" + coords[3][0] + "," + coords[3][1] + "]");
	}
	
	@Override
	public String toString() {
		return this.xCoordinate + "," + this.yCoordinate + "," + this.width + "," + this.depth;
	}
}
