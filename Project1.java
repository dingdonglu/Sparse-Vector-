import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Project1 {
	public static void main(String[] args){
	
	 Element ele= new Element();
	 SparseVector aVector = new SparseVector();			//first vector
	 SparseVector bVector = new SparseVector();			//second vector
	 SparseVector resultVector = new SparseVector();	//result vector which will get the result
	 String[] lines = new String[3];
     try
     {
     	FileReader fileReader = new FileReader("project1.txt");			//read text file
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        
        int count=0;
         while ((s= bufferedReader.readLine()) != null) {		//reading every line from text file
            lines[count]=s;    	
         	count ++;
         }
         bufferedReader.close();		//close bufferedreader
         
     }
     catch(Exception e)				//catch exception
     {
         System.out.println(e);
     }
    
     StringTokenizer st = new StringTokenizer(lines[0]);	// used tokenizer to break elements
     StringTokenizer st1 = new StringTokenizer(lines[1]);
 
     while(st.hasMoreElements()){							//break Element and store into Sparse vector
      ele= new Element (Integer.parseInt(st.nextToken()),Double.parseDouble(st.nextToken()));
      aVector.set(ele);
      
	}
    
     while(st1.hasMoreElements()){							//break Element and store into Sparse vector
      ele= new Element (Integer.parseInt(st1.nextToken()),Double.parseDouble(st1.nextToken()));
      bVector.set(ele); 
        
   	}
        
     resultVector.toString(aVector, bVector, lines[2]);			// pass in two vector to get the result we want
     

    }
}
