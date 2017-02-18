

public class Element implements Comparable<Element>{

	int index;
	double value;
	public Element (){					//  constructor
		index=0;
		value=0.0;
	}
	public Element (int i, double d){		
		index=i;
		value = d;
	}
	public int getIndex(){				//get index
		return index;
	}
	public double getValue(){			//get value
		return value;
	}
	public void setIndex(int i ){		//set index
		index=i;
	}
	public void setValue(double v){		//set value
		value=v;
	}
	
	public String toString() {			//to print single element
		// TODO Auto-generated method stub
		System.out.print("[" +index+ ", "+ value +"]");
		return null;
		
	}
	@Override
	public int compareTo(Element o) {		//to compare to element
		
	    return  o.getIndex()-this.getIndex();
	}
}
