package Code;

import Graphical.Solver;
import java.util.*;

public class Sudoku {
	
    private static class Box{
        public Integer row;
        public Integer col;
        
        public Box()
        {
            row=0;
            col=0;
        }
        };
    
    private static final Integer N=9;
	
	private static boolean usedinrow(Integer[][] arr, Integer row, Integer num)
	{
		Integer col;
		for(col=0;col<N;col++)
		{
			if(Objects.equals(arr[row][col], num))
				return true;
		}
		return false;
	}
	
	private static boolean usedincol(Integer[][] arr, Integer col, Integer num)
	{
		Integer row;
		for(row=0;row<N;row++)
		{
			if(Objects.equals(arr[row][col], num))
				return true;
		}
		return false;
	}
	
	private static boolean usedinbox(Integer[][] arr, Integer srow, Integer scol, Integer num)
	{
		Integer row,col;
		for(row=0;row<3;row++)
			for(col=0;col<3;col++)
			{
				if(Objects.equals(arr[srow+row][scol+col], num))
					return true;
			}
		return false;
	}
	
	private static boolean issafe(Integer[][] arr, Integer row, Integer col, Integer num)
	{
		boolean res = !usedinrow(arr,row,num) && !usedincol(arr,col,num) && !usedinbox(arr,row-row%3,col-col%3,num);
		return res;
	}
	
        
	private static boolean findunassigned(Integer[][] arr, Box b)
        {
            for(b.row=0;b.row<9;b.row++)
               for(b.col=0;b.col<9;b.col++)
               {
                   if(arr[b.row][b.col]==(0))
                       return true;
               }
           return false;
        }
        
        
        public static boolean solve(Solver.GridClass obj)
        {
            Box b1 = new Box();
            if(!findunassigned(obj.ansgrid,b1))
                return true;
            
            int i;
            for(i=1;i<=9;i++)
            {
                if(issafe(obj.ansgrid,b1.row,b1.col,i))
                {
                    obj.ansgrid[b1.row][b1.col] = i;
                    
                    if(solve(obj)==true)
                        return true;

                    obj.ansgrid[b1.row][b1.col] = 0;
                }
            }
            return false;    
        }
        
        public static boolean checkall(Integer[][] arr)
        {
            int i,j;
            for(i=0;i<9;i++)
            {
                for(j=0;j<9;j++)
                {
                    if(arr[i][j]<0 || arr[i][j]>9)
                        return false;
                }
            }
            return true;
        }
}
