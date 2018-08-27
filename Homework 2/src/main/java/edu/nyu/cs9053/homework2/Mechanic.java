package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.DiagnosticTroubleCode;

/**
 * User: blangel
 */
public class Mechanic {

	/**
	 * @see {@literal https://en.wikipedia.org/wiki/OBD-II_PIDs#Mode_3_(no_PID_required)}
	 * @implNote For simplification of this homework, contrary to the Wikipedia
	 *           article {@code data} is not in the ISO 15765-2 protocol. It is
	 *           simply an array of data where the length should be equal to
	 *           {@code expectedAmount} times 2.
	 * @implNote If {code data} is null, empty or not equal to
	 *           {@code expectedAmount} times 2 then throw an
	 *           {@linkplain IllegalArgumentException}; i.e.
	 *           {@code throw new IllegalArgumentException}
	 * @param data
	 *            to parse
	 * @param expectedAmount
	 *            of {@linkplain DiagnosticTroubleCode} to decode
	 * @return the decoded {@linkplain DiagnosticTroubleCode} objects see
	 *         {@linkplain DiagnosticTroubleCode#construct(String)} to create the
	 *         object.
	 */
	public static DiagnosticTroubleCode[] decode(byte[] data, int expectedAmount) {
		if (data == null || data.length <= 0 || expectedAmount <= 0 || data.length != (expectedAmount * 2)) {
			throw new IllegalArgumentException();
		}
		String diagnosticTroubleCodeBits = "";
		String[] diagnosticTroubleCodeCharacters = new String[expectedAmount];
		DiagnosticTroubleCode[] decodedCode = new DiagnosticTroubleCode[expectedAmount];
		for (byte codeData : data) {
			diagnosticTroubleCodeBits += String.format("%8s", Integer.toBinaryString(codeData & 0xFF)).replace(' ',
					'0');
		}
		int dtcNumber = 0;
		for (int dtcBitPos = 0; dtcBitPos < expectedAmount * 2 * 8; dtcBitPos += 16) {
			diagnosticTroubleCodeCharacters[dtcNumber] = diagnosticTroubleCodeFirstCharacter(
					diagnosticTroubleCodeBits.substring(dtcBitPos, dtcBitPos + 2))
					+ diagnosticTroubleCodeOtherCharacter(
							diagnosticTroubleCodeBits.substring(dtcBitPos + 2, dtcBitPos + 4))
					+ diagnosticTroubleCodeOtherCharacter(
							diagnosticTroubleCodeBits.substring(dtcBitPos + 4, dtcBitPos + 8))
					+ diagnosticTroubleCodeOtherCharacter(
							diagnosticTroubleCodeBits.substring(dtcBitPos + 8, dtcBitPos + 12))
					+ diagnosticTroubleCodeOtherCharacter(
							diagnosticTroubleCodeBits.substring(dtcBitPos + 12, dtcBitPos + 16));
			if (dtcNumber < expectedAmount) {
				dtcNumber++;
			}
		}
		for (dtcNumber = 0; dtcNumber < expectedAmount; dtcNumber++) {
			decodedCode[dtcNumber] = DiagnosticTroubleCode.construct(diagnosticTroubleCodeCharacters[dtcNumber]);
		}
		return decodedCode;
	}

	/**
	 * @param bitsOfDtc
	 *            first two bits of every DTC
	 * @return decoded first character of DTC
	 */
	public static String diagnosticTroubleCodeFirstCharacter(String bitsOfDtc) {
		switch (bitsOfDtc) {
		case "00":
			return "P";
		case "01":
			return "C";
		case "10":
			return "B";
		case "11":
			return "U";
		}
		return null;
	}

	/**
	 * @param bitsOfDtc
	 *            two bits of DTC if the second character is decoded otherwise four
	 *            bits of DTC
	 * @return decoded second/third/fourth/fifth character of DTC
	 */
	public static String diagnosticTroubleCodeOtherCharacter(String bitsOfDtc) {
		int decimalRepresentationOfDtc = Integer.parseInt(bitsOfDtc, 2);
		String diagnosticTroubleCodeOtherCharacters = Integer.toString(decimalRepresentationOfDtc, 16);
		return diagnosticTroubleCodeOtherCharacters;
	}

}
