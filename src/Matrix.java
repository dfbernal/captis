import java.util.ArrayList;

public class Matrix 
{
	private String[][] matrixMap;
	private int rowSize = 0;
	private int colSize = 0;
	
	public Matrix(int row, int col) throws Exception
	{
		if (row > 0 && col > 0)
		{
			this.rowSize = row;
			this.colSize = col;
			matrixMap = new String[row][col];
		}
		else
			throw new Exception("Row and Column size must be more than 0");
	}
	
	public void addRow(String[] row)
	{
	}
}
