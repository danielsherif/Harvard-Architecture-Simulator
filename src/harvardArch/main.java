package harvardArch;

import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.BigInteger;


public class main 
{

	private  String [] instructionMemory;
	private  Byte[] dataMem;         //Double law arithmetic op bas
	private  int []GPReg ;
	private  short pc;
	private  int []SREG;
	private  char type;
	private  String fetchDecodeReg ="";
	private  String decodeExecuteReg ="";
	private boolean fullDisplay;
	private static boolean branch;
	private short pcNew ;
	public static int i = 1 ;
	public static int maxCycles =0 ;
	public static int lifeSaver =0 ;
	
		public main()
	{
		this.instructionMemory= new String[1024];
		this.dataMem= new Byte[2048];
		this.GPReg= new int[64];
		this.SREG= new int [8];
		this.pc=0;
		this.fullDisplay= false;
	}


	   public void fetch() throws Exception {	
		   if(this.instructionMemory[this.pc]==null) return ;
		   System.out.println( this.instructionMemory[this.pc] + " --> is being fetched");
	        String instruction = "";
	        instruction = this.instructionMemory[this.pc];
	        if (instruction.split(" ")[0].equals("BEQZ"));
	        lifeSaver=pc;
	        if(branch)
	        	pc = pcNew ;
	        else
	        	this.pc++;
	        fetchDecodeReg = instruction ;	        
	    }

	private void decode() throws Exception {
		System.out.println(fetchDecodeReg + " --> is being decoded");   
		// TODO Auto-generated method stub
		String instruction =  fetchDecodeReg ;   
		fetchDecodeReg="";
		String[] words = instruction.split(" ");
		   String ins = words[0];
		   String opcode;
		   int r1,r2;
		   int imm,address;
		   String result;
		   int numberOfBits =6 ;
		   String bsR1,bsR2;
		        switch(ins)
		        {
		        
		        case "ADD":
		        	opcode="0000";	
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	r2 = Integer.parseInt(words[2].substring(1));
		        	type = 'r';
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r2)).replace(' ', '0');
		        	result = opcode+ bsR1 +bsR2;
//					bsR1 = toBinary(r1);
//					bsR2 = toBinary(r2);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        	
		        case "SUB":
		        	opcode="0001";	
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	r2 = Integer.parseInt(words[2].substring(1));
		        	type = 'r';
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r2)).replace(' ', '0');
		        	result =opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(r2);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        	
		        case "MUL":
		        	opcode="0010";
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	r2 = Integer.parseInt(words[2].substring(1));
		        	type = 'r';
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r2)).replace(' ', '0');
		        	result = opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(r2);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        	
		        case "LDI":
		        	opcode="0011";
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	imm = Integer.parseInt(words[2]);
		        	type = 'i';
		        	if(imm<0)
		        		bsR2 = Integer.toBinaryString(imm).substring(Integer.numberOfLeadingZeros(imm)).substring(Integer.toBinaryString(imm).length()-6, Integer.toBinaryString(imm).length());
		        	else
		        		bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(imm)).replace(' ', '0');
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	result =opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(imm);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        	
		        case "BEQZ":
		        	opcode="0100";
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	imm = Integer.parseInt(words[2]);
		        	type = 'i';
		        	if(imm<0)
		        		bsR2 = Integer.toBinaryString(imm).substring(Integer.numberOfLeadingZeros(imm)).substring(Integer.toBinaryString(imm).length()-6, Integer.toBinaryString(imm).length());
		        	else
		        		bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(imm)).replace(' ', '0');
		        	
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	
		        	result = opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(imm);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        	
		        case "AND":
		        	opcode="0101";
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	r2 = Integer.parseInt(words[2].substring(1));
		        	type = 'r';
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r2)).replace(' ', '0');
		        	result = opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(r2);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        	
		        case "OR":
		        	opcode="0110";
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	r2 = Integer.parseInt(words[2].substring(1));
		        	type = 'r';
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r2)).replace(' ', '0');
		        	result = opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(r2);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        case "JR":
		        	opcode="0111";
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	r2 = Integer.parseInt(words[2].substring(1));
		        	type = 'r';
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r2)).replace(' ', '0');
		        	result = opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(r2);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        case "SLC":
		        	opcode="1000";
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	imm = Integer.parseInt(words[2]);
		        	type = 'i';
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	if(imm<0)
		        		bsR2 = Integer.toBinaryString(imm).substring(Integer.numberOfLeadingZeros(imm)).substring(Integer.toBinaryString(imm).length()-6, Integer.toBinaryString(imm).length());
		        	else
		        		bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(imm)).replace(' ', '0');
		        	
		        	result = opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(imm);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        case "SRC":
		        	opcode="1001";
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	imm = Integer.parseInt(words[2]);
		        	type = 'i';
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	if(imm<0)
		        		bsR2 = Integer.toBinaryString(imm).substring(Integer.numberOfLeadingZeros(imm)).substring(Integer.toBinaryString(imm).length()-6, Integer.toBinaryString(imm).length());
		        	else
		        		bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(imm)).replace(' ', '0');
		        	
		        	result =opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(imm);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        case "LB":
		        	opcode="1010";
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	address = Integer.parseInt(words[2]);
		        	type = 'i';
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(address)).replace(' ', '0');
		        	result = opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(address);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        case "SB":
		        	opcode="1011";
		        	r1 = Integer.parseInt(words[1].substring(1));
		        	address = Integer.parseInt(words[2]);
		        	type = 'i';
		        	bsR1 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(r1)).replace(' ', '0');
		        	bsR2 = String.format("%" + numberOfBits + "s", Integer.toBinaryString(address)).replace(' ', '0');
		        	result = opcode+ bsR1 +bsR2;
//		        	bsR1 = toBinary(r1);
//					bsR2 = toBinary(address);
//					result = Integer.toBinaryString(opcode)+ bsR1 +bsR2;
		        	break;
		        	
		        default:
		        		throw new Exception("Wrong instruction type");
    
		        }
		        
		        
		        decodeExecuteReg = result;
		      
	}
	
	public void execute() throws Exception {
		String currentInstruction;
		
		int count=0;
		for(int i=0 ; i< instructionMemory.length && instructionMemory[i]!=null ; i++)
{
		count++;
}
		if (count==1)
		currentInstruction = instructionMemory[0];
		else if (count ==2 && i==3)
		currentInstruction = instructionMemory[0];
		else if (count ==2 && i==4)
			currentInstruction = instructionMemory[1];	
		else if(i==maxCycles)
			currentInstruction = instructionMemory[pc-1];				
		else
		currentInstruction = instructionMemory[pc-2];
		
			
	

		System.out.println(currentInstruction + " --> is being executed" );
		String result = decodeExecuteReg ;
		decodeExecuteReg="";
		String opcode;
		int tok1 , tok2;
		opcode=result.substring(0, 4);
		tok1 = Integer.parseInt(result.substring(4, 10),2);
		tok2 = Integer.parseInt(result.substring(10),2);
		int oldOperation;
		
		tok2 = getValueFromBinary(result.substring(10));
		
		
		for (int i=0 ; i<SREG.length ; i++)
		{
			SREG[i]=0;
		}
		

		if (type=='r') 
		{
			
			tok2 = GPReg[Integer.parseInt(result.substring(10),2)];
			
			switch(opcode)
			{
			 case "0000":
				 oldOperation= GPReg[tok1];
				 GPReg[tok1] = GPReg[tok1]+tok2;
				 //C Flag
				 if(GPReg[tok1]>>8 ==0b1) 
				 {
					 SREG[4]=1;
				 }
				 //V Flag
				 if(oldOperation>>7==tok2>>7 && (oldOperation>>7 != GPReg[tok1]>>7))
				 {
					 SREG[3]=1;
				 }
				 //N Flag
				 //SREG[2]=Integer.parseInt((GPReg[tok1]+"").substring(0, 1));
//				 String nFlag = tok1 +"";
//				 SREG[2] = 
				// int temp = GPReg[tok1]>>7;
				// SREG[2]=twosComplement(GPReg[tok1]);
				 if  (GPReg[tok1] < 0)
					 SREG[2]=1;
				 else
					 SREG[2]=0;
				
				//S  Flag
				 SREG[1]=SREG[2]^SREG[3];
				 //Z Flag
				 if (GPReg[tok1]==0)
				 {
					 SREG[0]=1;
				 }
				
			        break;
			        
			    case "0001":
			    	oldOperation= GPReg[tok1];
			    	GPReg[tok1] = GPReg[tok1]-tok2;
			    	//V Flag
					 if(oldOperation>>7!=tok2>>7 && (tok2>>7 == GPReg[tok1]>>7))
					 {
						 SREG[3]=1;
					 }
					//N Flag
					 if  (GPReg[tok1] < 0)
						 SREG[2]=1;
					 else
						 SREG[2]=0;
					 //S  Flag
					 SREG[1]=SREG[2]^SREG[3];
					 //Z Flag
					 if (GPReg[tok1]==0)
					 {
						 SREG[0]=1;
					 }
					 
			        break;
			    case "0010":
			    	GPReg[tok1] = GPReg[tok1]*tok2;
			    	//N Flag
			    	 if  (GPReg[tok1] < 0)
						 SREG[2]=1;
					 else
						 SREG[2]=0;
					 //Z Flag
					 if (GPReg[tok1]==0)
					 {
						 SREG[0]=1;
					 }
					 
			        break;
			    case "0101":
			    	GPReg[tok1] =GPReg[tok1]&tok2;
			    	//N Flag
			    	 if  (GPReg[tok1] < 0)
						 SREG[2]=1;
					 else
						 SREG[2]=0;
					 //Z Flag
					 if (GPReg[tok1]==0)
					 {
						 SREG[0]=1;
					 }
			        break;
			    case "0110":
			    	GPReg[tok1] = GPReg[tok1]|tok2;
			    	//N Flag
			    	 if  (GPReg[tok1] < 0)
						 SREG[2]=1;
					 else
						 SREG[2]=0;
					 //Z Flag
					 if (GPReg[tok1]==0)
					 {
						 SREG[0]=1;
					 }
			        break;
			        
			    case "0111":
			    	//String pcString =  Integer.toBinaryString(GPReg[tok1]) + Integer.toBinaryString(tok2);
			    	//pc = (short) (Integer.parseInt(pcString) & 0xFFFF) ;
			    	String aBinaryString = Integer.toBinaryString(GPReg[tok1]);
			    	String bBinaryString = Integer.toBinaryString(tok2);
			    	String pcString = aBinaryString + bBinaryString;
			    	short pc = (short) Integer.parseInt(pcString, 2);
			    	
			        break;
			    default:
			    	throw new Exception("Wrong instruction type");
			        
			}
		}
		
		else if(type=='i')
		{
			switch(opcode)
			{
			 case "0011":
				 GPReg[tok1] = tok2;
			        break;
			    case "0100":
			    	if (GPReg[tok1]==0)
			    	{
			    		pcNew= (short) (lifeSaver+tok2+1);
			    		branch=true;
			    		
			    	}
			        break;
			    case "1000":
			    	GPReg[tok1] = GPReg[tok1] << tok2 | GPReg[tok1] >>> 8 - tok2;
			    	//N Flag
					 SREG[2]=GPReg[tok1]>>7;
					 //Z Flag
					 if (GPReg[tok1]==0)
					 {
						 SREG[0]=1;
					 }
			        break;
			    case "1001":
			    	GPReg[tok1] = GPReg[tok1] >>> tok2 | GPReg[tok1] << 8 - tok2;
			    	//N Flag
					 SREG[2]=GPReg[tok1]>>7;
			    	 //Z Flag
					 if (GPReg[tok1]==0)
					 {
						 SREG[0]=1;
					 }
			        break;
			    case "1010":
			    	GPReg[tok1] = dataMem[tok2];
			    case "1011":
			    	dataMem[tok2] = (byte) GPReg[tok1];
			        break;
			    default:
			    	throw new Exception("Wrong instruction type");
			        
			}
		}
		else 
			throw new Exception("Wrong instruction type");
		
		
	}
	

	public void run(int cycles) throws Exception {
	    int numInstructions = instructionMemory.length;
	    
	    if (numInstructions==0)
	    {
	    	throw new Exception("No instructions in memory");
	    }
		 numInstructions=0;
		for(int i=0 ; i< instructionMemory.length && instructionMemory[i]!=null ; i++)
{
			numInstructions++;
}
	    for ( i = 1; i <= cycles; i++) {
	        System.out.println("cycle:" + i);
	        if (!decodeExecuteReg.equals("")) // there is something waiting to be executed
	            this.execute();
	        if (!fetchDecodeReg.equals("")) // there is something waiting to be decoded
	            this.decode();
	        
	        if ( instructionMemory[pc]!=null) // check if i is within the bounds of the instructionMemory array
                this.fetch();
	        
	        if(branch)
	        {
	        	fetchDecodeReg ="";
	        	decodeExecuteReg ="";
	        	branch=false;
	        	
	        }

	        display();
	        
	        if(decodeExecuteReg.equals("")&&fetchDecodeReg.equals("")&&this.instructionMemory[pc]==null)return;
	        
	    }
		}
		
	
	
	public void display() {
		if (!fullDisplay) 
		{
		System.out.print("Data Memory: ");
		for(int j=0;j<10;j++)
			System.out.print(dataMem[j]+",");
		System.out.println();
		System.out.print("Register File: ");
		for(int j=0;j<10;j++)
			System.out.print(GPReg[j]+",");
		System.out.println();
		System.out.print("Status Register: ");
		for(int j=0;j<8;j++)
			System.out.print(SREG[j]+",");
		System.out.println();
		System.out.println("_____________________________________________________");	
		}
		else 
		{
			System.out.print("Data Memory: ");
			for(int j=0;j<10;j++)
				System.out.print(dataMem[j]+",");
			System.out.println();
			System.out.print("Register File: ");
			for(int j=0;j<63;j++)
				System.out.print(GPReg[j]+",");
			System.out.println();
			System.out.print("Status Register: ");
			for(int j=0;j<8;j++)
				System.out.print(SREG[j]+",");
			System.out.println();
			System.out.println("_____________________________________________________");	
		}
	}
	

	private void readInstructionsFromFile(String filePath) throws FileNotFoundException {
//		List<String> instructions = new ArrayList<String>();

        try  {
        	BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            int i=0;
            while ((line = br.readLine()) != null) {
                //instructions.add(line);
            	instructionMemory[i] = line ;
            	i++ ;
            }
          
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list to an array
//        String[] instructionArray = new String[instructions.size()];
//        instructionArray = instructions.toArray(instructionArray);

//        return instructionArray;
    }
	

	public static int getValueFromBinary(String a) {

	    if (a.charAt(0) == '1') {
	        // Negative
	        StringBuilder b = new StringBuilder();
	        for (int i = 0; i < a.length(); i++) {
	            if (a.charAt(i) == '0') {
	                b.append('1');
	            } else {
	                b.append('0');
	            }
	        }
	        int complement = Integer.parseInt(b.toString(), 2);
	        return -complement;
	    } else {
	        return Integer.parseInt(a, 2);
	    }
	}

	public static int twosComplement(int integer) {

		  // Check if the integer is negative.
		  if (integer < 0) {

		    // Get the number of bits in the integer.
		    int numberOfBits = Integer.numberOfLeadingZeros(-integer) + 1;

		    // Flip the bits of the integer.
		    integer = ~integer;

		    // Add 1 to the flipped bits.
		    integer++;

		    // Mask the integer to the number of bits.
		    integer &= (1 << numberOfBits) - 1;

		  }

		  // Return the integer.
		  return integer;
		}
	public static String decimalToBinaryTwoComplement(int decimal) {
        String binary = Integer.toBinaryString(decimal);
        int numBits = Integer.SIZE;  // Number of bits in an integer
        
        // Pad the binary string with leading zeros
        binary = String.format("%" + numBits + "s", binary).replace(' ', '0');
        
        // If the decimal is negative, calculate the two's complement
        if (decimal < 0) {
            binary = flipBits(binary);
            binary = addOne(binary);
        }
        
        return binary;
    }
    
    private static String flipBits(String binary) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < binary.length(); i++) {
            char bit = binary.charAt(i);
            result.append(bit == '0' ? '1' : '0');
        }
        
        return result.toString();
    }
    
    private static String addOne(String binary) {
        StringBuilder result = new StringBuilder();
        boolean carry = true;
        
        for (int i = binary.length() - 1; i >= 0; i--) {
            char bit = binary.charAt(i);
            
            if (carry) {
                if (bit == '0') {
                    bit = '1';
                    carry = false;
                } else {
                    bit = '0';
                }
            }
            
            result.insert(0, bit);
        }
        
        return result.toString();
    }

	public static void main(String[]args) throws Exception {
		
		
	        
		main OS = new main();
		

        
		//Display and read the instructions file
		String filePath = "programs\\program1";
//		OS.instructionMemory =  readInstructionsFromFile(filePath); 
		OS.readInstructionsFromFile(filePath);
        System.out.println("Here are the instructions that will be fetched , decoded and executed...."); 
        int i =0 ;
				for(i=0 ; i< OS.instructionMemory.length && OS.instructionMemory[i]!=null ; i++)
		{
				System.out.println(OS.instructionMemory[i]);
		}
				System.out.println();
				
		int noOfInstr=i;
		
//		OS.dataMem[0] = 2 ;
//		OS.dataMem[1] = 0 ;
//		OS.dataMem[2] = 3 ;
		
		int n = 3 + ((noOfInstr-1)*1);
		OS.maxCycles = n ;
		OS.run(n);
	}
	
}
