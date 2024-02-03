import java.util.ArrayList;
public class HugeInteger {
	final static int HUGE_INTEGER_SIZE = 300; 	
	private boolean overflow = false;
	private byte[] hugeInteger; 								
	private enum Sign {PLUS, MINUS};							
	private Sign intSign = Sign.PLUS;									
	
	//******************************* CONSTRUCTOR 1 ****************************************
	// Constructor 1: Creation of HugeInteger based of a given length
	public HugeInteger(int length)
	{
		try
		{
			hugeInteger = new byte[length];
		} catch (Exception e)
		{
			System.out.println("Error: Invalid input/argument. Must only pass integer values or a String representing a positive or negative integer.");
		}
	}
	
	// ******************************* CONSTRUCTOR 2 ****************************************
	// Constructor 2: Creating of HugeInteger based off a given string representing a huge number
	public HugeInteger(String str)
	{
		try
		{
			int length = str.length();
			int i, j;
			
			// Check if a negative number is passed
			if(str.contains("-"))
			{
				i = 1;
				intSign = Sign.MINUS;
				length -= 1;
				
			} else
			{
				i = 0;
			}
			
			// Check if individual characters in str are "equal" to numeric integers
			int trueInteger = 0;
			for(; i < str.length(); i++)
			{
				for(j = 0; j <= 9; j++)
				{
					char intToChar = Character.forDigit(j, 10);
					if(str.charAt(i) == intToChar)
					{
						trueInteger += 1;
					}
				}
			}
			
			// Check if all characters in str are numbers
			// If yes, proceed to populate hugeInteger array with numbers
			// if no, throw error on console
			if(trueInteger == length)
			{
				if(intSign == Sign.MINUS)
				{
					hugeInteger = new byte[length];
					for(i = 1, j = 0; i < length + 1 && j < length; ++i, ++j)
					{
						hugeInteger[j] = (byte) Character.getNumericValue(str.charAt(i));
					}
				}else
				{
					intSign = Sign.PLUS;
					hugeInteger = new byte[length];
					for(i = 0; i < length; i++)
					{
						hugeInteger[i] = (byte) Character.getNumericValue(str.charAt(i));
					}
				}
			} else 
			{
				System.out.println("Error. Invalid input/argument. Must only pass a String of a positive or negative integer or an integer value.");
			}
		} catch(Exception e)
		{
			System.out.println("Invalid input. Must be a string consisting only of positive or negative numbers.");
		}
	}
	
	//******************************* getLength Method ****************************************
	// Method Length: Get length of Huge Integer
	public int getLength() {
		return hugeInteger.length;
	}
	
	//******************************* getDigit Method ****************************************
	// Method Digit: Get a digit of our number given an index
	public byte getDigit(int index)
	{
		try
		{
			return hugeInteger[index];
		} catch (Exception e)
		{
			System.out.println("Error: Invalid input/argument. Can only pass an integer value.");
			return -1;
		}
	}
	//******************************* Equal Method ****************************************
	// Method Equal: Determine if two HugeIntegers are equal
	public boolean equal(HugeInteger i)
	{
		try
		{
			int equalCount = 0;
			//Check the integer signs
			if(this.intSign != i.intSign)
			{
				return false;
			}
			// Check that both integers have same number of digits
			if(this.hugeInteger.length != i.hugeInteger.length)
			{
				return false;
			}
			// Check each digit from the beginning (index = 0)
			for(int a = 0; a < this.hugeInteger.length; a++)
			{
				if(this.hugeInteger[a] == i.hugeInteger[a])
				{
					equalCount += 1;
				}
			}
			if(equalCount == this.hugeInteger.length)
			{
				return true;
			}
			return false;
		} catch(Exception e)
		{
			System.out.println("Invalid input/argument. Can only pass HugeInteger data.");
			return false;
		}
		
	}
	
	//******************************* greaterThan Method ****************************************
	// Method Greater Than: Determine if first integer is greater than second integer (passed argument)
	public boolean greaterThan(HugeInteger i)
	{
		try
		{
			// Check the signs of the integers
			if(this.intSign == Sign.MINUS && i.intSign == Sign.PLUS) {
				return false;
			} else if (this.intSign == Sign.PLUS && i.intSign == Sign.MINUS) {
				return true;
			}
			// Given that the signs are equal (++ OR --), now we check the value of the individual digits
			if(this.hugeInteger.length > i.hugeInteger.length)
			{
				return true;
			} else if(this.hugeInteger.length < i.hugeInteger.length)
			{
				return false;
			}
			// Loop will continue if the values are equal; it will break when a difference in value is found
			for(int a = 0; a < this.hugeInteger.length; a++)
			{
				if(this.hugeInteger[a] > i.hugeInteger[a])
				{
					return true;
				} else if(this.hugeInteger[a] < i.hugeInteger[a])
				{
					return false;
				}
			}
			return false;
		}catch (Exception e)
		{
			System.out.println("Invalid input/argument. Can only pass HugeInteger data.");
			return false;
		}
	}
	
	//******************************* lessThan Method ****************************************
	// Method Equal: Determine if two HugeIntegers are equal
	public boolean lessThan(HugeInteger i)
	{
		try
		{
			// Check the signs of the integers
			if(this.intSign == Sign.PLUS && i.intSign == Sign.MINUS) {
				return false;
			} else if (this.intSign == Sign.MINUS && i.intSign == Sign.PLUS) {
				return true;
			}
			// Given that the signs are equal (++ OR --), now we check the value of the individual digits
			if(this.hugeInteger.length < i.hugeInteger.length)
			{
				return true;
			} else if(this.hugeInteger.length > i.hugeInteger.length)
			{
				return false;
			}
			// Loop will continue if the values are equal; it will break when a difference in value is found
			for(int a = 0; a < this.hugeInteger.length; a++)
			{
				if(this.hugeInteger[a] < i.hugeInteger[a])
				{
					return true;
				} else if(this.hugeInteger[a] > i.hugeInteger[a])
				{
					return false;
				}
			}
			return false;
		} catch (Exception e)
		{
			System.out.println("Invalid input/argument. Can only pass HugeInteger data.");
			return false;
		}
	}
	
	//******************************* Print Method ****************************************
	// Method Print: Print out number, including negative sign if negative
	public void print(HugeInteger i)
	{
		try
		{
			if(intSign.equals(Sign.MINUS))
			{
				System.out.print("-");
			}
			for(int el: hugeInteger)
			{
				System.out.print(el);
			}
		} catch (Exception e)
		{
			System.out.println("Invalid input/argument. Can only pass HugeInteger data.");
		}
	}
	
	//******************************* Assign Method ****************************************
	// Method Assign: Copy value of operand i
	public void assign(HugeInteger i)
	{
		try
		{
			hugeInteger = new byte[i.getLength()];
			intSign = i.intSign;
			for(int j = 0; j < i.getLength(); ++j)
			{
				hugeInteger[j] = i.hugeInteger[j];
			}
		} catch (Exception e)
		{
			System.out.println("Invalid input/argument. Can only pass HugeInteger data.");
		}
	}
	
	//******************************* Add Method ****************************************
	public void add(HugeInteger i)
	{
		try
		{
			// Data Fields
			ArrayList<Byte> tempNew = new ArrayList<Byte>();							// Temporary array holding sum/difference
			ArrayList<Byte> reverseNew = new ArrayList<Byte>();							// Temporary array to reverse order of tempNew
			int remainder = 0;															// Remainder to be propagated
			int thisLength = this.hugeInteger.length;									// Length of original array of bytes
			int iLength = i.hugeInteger.length;											// Length of incoming array of bytes
			int smallerLength = thisLength < iLength ? thisLength : iLength;			// Length of smaller array
			byte[] maxInt = thisLength > iLength ? this.hugeInteger : i.hugeInteger;	// Temporary array to store largest integer
			byte[] minInt = thisLength < iLength ? this.hugeInteger : i.hugeInteger;	// Temporary array to store smallest integer
			byte[] minuend = this.hugeInteger;											// Temporary array to store largest number to subtract from
			byte[] subtrahend = i.hugeInteger;											// Temporary array to store smallest number to be the subtractee
			int maxIndex;																// Index total of largest number
			int minIndex;																// Index total of smallest number
			int remainingIndex;															// Difference between max array length and min array length
			
			// PROCEDURE
			// Check the sign (+ or -) of both integers. If different, subtract
			if(this.intSign != i.intSign)
			{
				// PROCEDURE TO SUBTRACT INTEGERS
				// Check to ensure which of the two integers is the smallest
				boolean isSmaller = false;
				for(int x = 0; x < minuend.length; x++)
				{
					if(minuend[x] < subtrahend[x])
					{
						isSmaller = true;
					} else if(minuend[x] > subtrahend[x])
					{
						isSmaller = false;
						break;
					}
				}
				if(isSmaller)
				{
					this.intSign = i.intSign;
					minuend = i.hugeInteger;
					subtrahend = this.hugeInteger;
				}
				// Subtract individual numbers up to the length of the smallest number
				smallerLength = minuend.length < subtrahend.length ? minuend.length : subtrahend.length;
				for(int a = 1; a <= smallerLength; ++a)
				{
					maxIndex = minuend.length - a;
					minIndex = subtrahend.length - a;
					if(minuend[maxIndex] - subtrahend[minIndex] < 0 && maxIndex - 1 >= 0)
					{
						minuend[maxIndex - 1] -= 1;
						subtrahend[minIndex] += 10;
					}
					byte sub = (byte) (minuend[maxIndex] - subtrahend[minIndex]);
					tempNew.add(sub);
				}
				// If largest number has more digits than smallest, finished adding propagating numbers to sub/difference
				if(minuend.length > subtrahend.length)
				{
					remainingIndex = (minuend.length - subtrahend.length) - 1;
					int b = remainingIndex;
					for(; b >= 0; --b)
					{
						tempNew.add(minuend[remainingIndex]);
						remainingIndex -= 1;
					}
				}
			} else
			{
				// PROCEDURE TO ADD INTEGERS
				// Add individual integers in array; carry over the remainder
				for(int c = 1; c <= smallerLength; ++c)
				{
					int add = this.hugeInteger[thisLength - c] + i.hugeInteger[iLength - c] + remainder;
					if (add > 9) {
						remainder = 1;
						add = add - 10;
					} else
					{
						remainder = 0;
					}
					tempNew.add((byte) add);
				}
				
				// Finish adding remainder and remaining integers
				if(maxInt != null && minInt != null)
				{
					remainingIndex = maxInt.length - minInt.length - 1;
					maxInt[remainingIndex] = (byte) (maxInt[remainingIndex] + remainder);
					tempNew.add(maxInt[remainingIndex]);
					remainingIndex -= 1;
					int d = remainingIndex;
					for(; d >= 0; --d)
					{
						tempNew.add(maxInt[remainingIndex]);
						remainingIndex -= 1;
					}
				}
			}
			
			//Reverse order of tempSum
			int e = tempNew.size() - 1;
			for(; e >= 0; --e)
			{
				reverseNew.add(tempNew.get(e));
			}
			
			// Re-initialize hugeInteger array and populate with sum of two arrays
			this.hugeInteger = new byte[reverseNew.size()];
			for (int f = 0; f < reverseNew.size(); f++)
			{
				this.hugeInteger[f] = reverseNew.get(f);
			}
			
			// Check for overflow (does the sum exceed 300 digits?)
			if(this.hugeInteger.length > HUGE_INTEGER_SIZE)
			{
				overflow = true;
				System.out.println("Error: Sum of integers produced a number consisting of more than 300 digits.");
			}
		} catch(Exception e)
		{
			System.out.println("Invalid input/argument. Can only pass HugeInteger data.");
		}
	}
	
	//******************************* Multiplication Method ****************************************
	// Method Multiply: Multiply two large integers
	public void mult(HugeInteger i)
	{
		try
		{
			byte[] multiplier = this.hugeInteger.length > i.hugeInteger.length ? this.hugeInteger : i.hugeInteger;
			byte[] multiplicand = this.hugeInteger.length < i.hugeInteger.length ? this.hugeInteger : i.hugeInteger;
			ArrayList<ArrayList<Byte>> products = new ArrayList<ArrayList<Byte>>();
			ArrayList<Byte> finalProduct = new ArrayList<Byte>();
			
			
			// Multiply individual digits of integers; store products in the two-dimensional array "products"
			int product;
			int remainder = 0;
			for(int z = 0; z < multiplicand.length; ++z)
			{
				products.add(new ArrayList<Byte>());
				int a = z;
				for(; a > 0; --a)
				{
					products.get(z).add((byte) 0);
				}
				int y = multiplier.length - 1; //4
				int x = (multiplicand.length - 1) - z; //3
				for(; y >= 0 && x >= 0; y--)
				{
					product = (multiplicand[x] * multiplier[y]) + remainder;
					if(y != 0)
					{
						remainder = 0;
					}
					if(product > 9) {
						for(int w = 80, v = 8; w > 0 && v > 0; w -= 10, --v)
						{
							if(product >= w)
							{
								remainder = v;
								product -= w;
							}
						}
					}
					products.get(z).add((byte) product);
					if(y == 0 && remainder != 0)
					{
						products.get(z).add((byte) remainder);
						remainder = 0;
					}
				}
			}
			
			// Get the largest and smallest length of the products array
			int largestProductLength = 0;
			int smallestProductLength = products.get(0).size();
			for(int u = 0; u < products.size(); u++)
			{
				int a = products.get(u).size();
				if(a > largestProductLength)
				{
					largestProductLength = a;
				}
				if(a < smallestProductLength)
				{
					smallestProductLength = a;
				}
			}
			
			// Make the length of all the products array equal
			for(int t = 0; t < products.size(); t++) {
				if(products.get(t).size() != largestProductLength)
				{
					int missingLength = largestProductLength - products.get(t).size();
					for(int s = 1; s <= missingLength; s++)
					{
						products.get(t).add((byte)0);
					}
				}
			}
			
			// Have array "finalProduct" hold sum of products as we loop through products array
			// Set length of finalProduct and set all values equal to 0
			for(int z = 0; z < largestProductLength; z++)
			{
				finalProduct.add((byte)0);
			}
			int remainingSum = 0;
			for(int a = 0; a < products.size(); a++)
			{
				for(int b = 0; b < largestProductLength; b++)
				{
					int sum;
					sum = products.get(a).get(b) + finalProduct.get(b) + remainingSum;
					remainingSum = 0;
					if(sum > 9)
					{
						for(int c = 30, d = 3; c > 0 && d > 0; c -= 10, d--)
						{
							if(sum >= c)
							{
								remainingSum = d;
								sum -= c;
								break;
							}
						}
					}
					finalProduct.set(b, (byte)sum);
					if(b == (largestProductLength -1) && a == (products.size() - 1) && remainingSum != 0)
					{
						finalProduct.add((byte)remainingSum);
						remainingSum = 0;
					}
				}
			}
			
			//Reverse order of finalProduct
			ArrayList<Byte> reverseFinalProduct = new ArrayList<Byte>();
			int e = finalProduct.size() - 1;
			for(; e >= 0; --e)
			{
				reverseFinalProduct.add(finalProduct.get(e));
			}
			
			// Re-initialize hugeInteger array and populate with product of two huge integers
			this.hugeInteger = new byte[reverseFinalProduct.size()];
			for (int f = 0; f < reverseFinalProduct.size(); f++)
			{
				this.hugeInteger[f] = reverseFinalProduct.get(f);
			}
			
			// Check for overflow (does the product exceed 300 digits?)
			if(this.hugeInteger.length > HUGE_INTEGER_SIZE)
			{
				overflow = true;
				System.out.println("Error: Sum of integers produced a number consisting of more than 300 digits.");
			}
		}catch(Exception e)
		{
			System.out.println("Invalid input/argument. Can only pass HugeInteger data.");
		}
	}
	
	//******************************* Division Method ****************************************
	// Method Division: Dividing Huge Integers.
	public void divide(HugeInteger i)
	{
		try
		{
			System.out.println("Coming Soon!");
		}catch (Exception e)
		{
			System.out.println("Invalid input/argument. Can only pass HugeInteger data.");
		}
	}
	
	
	//******************************* toString Method ****************************************
	// Method To String: Transform HugeInteger to a string.
	public String toString()
	{
		int arrayLength = this.hugeInteger.length;
		char[] charArray = new char[arrayLength];
		for(int i = 0; i < arrayLength; ++i)
		{
			charArray[i] = Character.forDigit(this.hugeInteger[i], 10);
		}
		String hugeString = "";
		hugeString = String.copyValueOf(charArray, 0, arrayLength);
		return hugeString;
	}
	
}

