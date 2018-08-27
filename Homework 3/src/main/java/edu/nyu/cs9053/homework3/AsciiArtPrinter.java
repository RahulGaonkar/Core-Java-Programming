package edu.nyu.cs9053.homework3;

/**
 * User: blangel
 */
public class AsciiArtPrinter {

	/**
	* @implNote should only print values within {@code asciiArt} and nothing else
	*           within this method
	* @param asciiArt
	*            to print
	*/
	public void print(char[][] asciiArt) {
		char[][] intermediateAsciiArtCharacterArray = new char[asciiArt.length][asciiArt[0].length];
		int intermediateAsciiArtCharacterArrayXPosition = 0;
		for (int asciiArtCharacterXPosition = asciiArt.length
				- 1; asciiArtCharacterXPosition >= 0; asciiArtCharacterXPosition--) {
			for (int asciiArtCharacterYPosition = 0; asciiArtCharacterYPosition < asciiArt[asciiArtCharacterXPosition].length; asciiArtCharacterYPosition++) {
				intermediateAsciiArtCharacterArray[intermediateAsciiArtCharacterArrayXPosition][asciiArtCharacterYPosition] = asciiArt[asciiArtCharacterXPosition][asciiArtCharacterYPosition];
			}
			intermediateAsciiArtCharacterArrayXPosition++;
		}
		transposePrint(intermediateAsciiArtCharacterArray);
	}

	/**
	* @param intermediateAsciiArtCharacterArray
	*            Used to align the output in a desired way
	* @implNote method is used to display the transpose of
	*           {@code intermediateAsciiArtCharacterArray} to get the desired
	*           output
	*/
	public void transposePrint(char[][] intermediateAsciiArtCharacterArray) {
		StringBuilder asciiArt = new StringBuilder();
		for (int intermediateAsciiArtCharacterArrayYPosition = 0; intermediateAsciiArtCharacterArrayYPosition < intermediateAsciiArtCharacterArray[0].length; intermediateAsciiArtCharacterArrayYPosition++) {
			for (int intermediateAsciiArtCharacterArrayXPosition = intermediateAsciiArtCharacterArray.length
					- 1; intermediateAsciiArtCharacterArrayXPosition >= 0; intermediateAsciiArtCharacterArrayXPosition--) {
				asciiArt.append(
						intermediateAsciiArtCharacterArray[intermediateAsciiArtCharacterArrayXPosition][intermediateAsciiArtCharacterArrayYPosition]);

			}
			asciiArt.append('\n');
		}
		String asciiArtImage = asciiArt.toString();
		System.out.format("%s", asciiArtImage);
	}

	protected void clearScreen() {
		System.out.printf("\u001B[51;1H");
	}

}


