

public class SparseVector {
	public DoublyLinkedList<Element> ele = new DoublyLinkedList<>();
	
	public SparseVector(){
		ele=new DoublyLinkedList<>();	
	}
	
	public void set(Element value) {   		//set Element
       ele.add(value);			
    }
	
	public Element get(int i){				//get Element
	    return ele.get(i); 
	}
	
	public double getValue(int i){			//get Element Value;
		return ele.get(i).getValue();
	}
	public int getIndex(int i){				//get Element index;
		return ele.get(i).getIndex(); 
	}
	
	public void BubbleSort(){
		 int i, j;
		 Element temp;  							// holding variable
		 int numLength = ele.size(); 
		     for( i=0; i< (numLength -1); i++)    	// Element to be compared
		    {
		          for( j=i; j < numLength; j++)   	// rest of the Elements
		         {
		                if( this.get(i).getIndex() > this.get(j).getIndex())      
		               {
		                        temp= new Element(this.getIndex(i),this.getValue(i));          // swap Elements
		                        get(i).setIndex(this.getIndex(j));
		                        get(i).setValue(this.getValue(j));
		                        ele.get(j).setIndex(temp.getIndex());
		                        ele.get(j).setValue(temp.getValue());
		               }
		          }
		     }
		     return;
	}
	public SparseVector add(SparseVector sv){
		SparseVector result= new SparseVector();
		SparseVector bAdd= new SparseVector(); 	 //Element to will be add
			
		for (int i =0 ;i<ele.size();i++){		//assume the result will be the original
			result.set(this.get(i));
		}
		for (int i =0 ;i<sv.ele.size();i++){
			bAdd.set(sv.get(i));
		}
		for (int i =0 ;i<result.ele.size();i++){		//add Elements that have same Element index
			for(int j= i; j<bAdd.ele.size();j++){
				if(result.get(i).compareTo(bAdd.get(j))==0){
					if(bAdd.getValue(j)+this.getValue(i)==0){
						result.ele.remove(i);
					}else{
						result.get(i).setValue(bAdd.getValue(j)+this.getValue(i));
					}
					bAdd.ele.remove(j);		//remove Element that is have be added
				}			
			}
		}	
		
		for(int i=0;i<bAdd.ele.size();i++)
		{
			result.set(bAdd.get(i));		//insert Element that is not have same Element index
		}
		result.BubbleSort();		//sort the result Element list
		return result;				//return
		
	 }
	
	 public SparseVector subtract(SparseVector sv){
		SparseVector result= new SparseVector();
		SparseVector bSubtract= new SparseVector(); //Element to will be subtract
		
		for (int i =0 ;i<ele.size();i++){      		//assume the result will be the original
			result.set(this.get(i));
		}
		for (int i =0 ;i<sv.ele.size();i++){
			Element E= new Element(sv.getIndex(i),0.0-sv.getValue(i)); // set the Elements that is going to be subtract to negative
			bSubtract.set(E);
		}
		for (int i =0 ;i<result.ele.size();i++){						//add Elements that have same Element index
			for(int j= i; j<bSubtract.ele.size();j++){
				if(result.get(i).compareTo(bSubtract.get(j))==0){
					if(bSubtract.getValue(j)+getValue(i)==0){
						result.ele.remove(i);
					}else{
						result.get(i).setValue(bSubtract.getValue(j)+getValue(i));
					}
					bSubtract.ele.remove(j);	//remove Element that is have be subtracted
				}			
			}
		}	
		
		for(int i=0;i<bSubtract.ele.size();i++)
		{
			result.set(bSubtract.get(i));			//insert Element that is not have same Element index
		}
		result.BubbleSort();		//sort the result Element list
		return result;				//return
		
	}
	 
	public double dot(SparseVector sv){
		SparseVector bDot= new SparseVector();		
		double result=0.0;
		for (int i =0 ;i<sv.ele.size();i++){
			bDot.set(sv.get(i));				//store the vector that is going to be multiply in to bDot
		}
		
		for (int i =0 ;i<ele.size();i++){							//go throw to loops to fine Element have same index
			for(int j= i; j<bDot.ele.size();j++){					
				if(this.get(i).compareTo(bDot.get(j))==0){		
						result=this.getValue(i)*bDot.getValue(j)+result;	//do the calculation store in result
						bDot.ele.remove(j);
					}
				}			
			}

		return result;			//return result
		
	}
	public void toPrint(){
		 for(int i = 0; i<ele.size();i++){		//print all element in single vector
			 ele.get(i).toString();
			
		 }
		 System.out.println("");
	}
	public String toString(SparseVector a ,SparseVector b, String Command){
		 
		 Command.toLowerCase();			//make all the case to LowerCase to avoid error
		
		 switch (Command) {						// see which command does user input to choose which function to use
		 		case "add" : 					//add
		 			a.toPrint();
					System.out.println("+");
					b.toPrint();
					System.out.println("=");
					a.add(b).toPrint();
					break;
		 		case "subtract":				//subtract
		 			a.toPrint();
					System.out.println("-");
					b.toPrint();
					System.out.println("=");
					a.subtract(b).toPrint();
					break;
		 		case "dot":						//dot
		 			a.toPrint();
					System.out.println((char) 183);
					b.toPrint();
					System.out.println("=");
					System.out.println(a.dot(b));	
					break;
		 }
		 

		 return null;
	 }
	 
}
