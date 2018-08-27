package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.*;

/**
 * User: blangel
 *
 * @see {@literal https://www.json.org/}
 * @see {@literal https://en.wikipedia.org/wiki/JSON}
 */

public class JsonParser {

	/**
	 * @param alert
	 *            to serialize into {@literal JSON}
	 * @implNote a null value should be an {@linkplain IllegalArgumentException};
	 *           i.e. {@code throw new IllegalArgumentException}
	 * @return the serialized {@literal JSON} representation of {@code alert}
	 */
	public static String toJson(EngineLightAlert alert) {
		String jsonString = "";
		if (alert == null) {
			throw new IllegalArgumentException();
		}
		jsonString += "{";
		if (alert.getVehicleId() != null && !alert.getVehicleId().startsWith("\"")
				&& !alert.getVehicleId().endsWith("\"") && alert.getDateTime() == 0L && alert.getCodes() == null) {
			jsonString += "\"vehicleId\":" + "\"" + alert.getVehicleId() + "\"";
		}
		if (alert.getVehicleId() != null && alert.getVehicleId().startsWith("\"") && alert.getVehicleId().endsWith("\"")
				&& alert.getDateTime() == 0L && alert.getCodes() == null) {
			jsonString += "\"vehicleId\":" + "\"\\\"" + alert.getVehicleId().replace("\"", "") + "\\\"\"";
		}
		if (alert.getVehicleId() == null && alert.getDateTime() != 0L && alert.getCodes() == null) {
			jsonString += "\"dateTime\":" + alert.getDateTime();
		}
		if (alert.getVehicleId() == null && alert.getDateTime() == 0L && alert.getCodes() != null) {
			if (alert.getCodes().length > 0) {
				jsonString += "[";
				for (int diagnosticTroubleCode = 0; diagnosticTroubleCode < alert
						.getCodes().length; diagnosticTroubleCode++) {
					if (alert.getCodes()[diagnosticTroubleCode] != null) {
						jsonString += "{";
						if (alert.getCodes()[diagnosticTroubleCode].getCode() != null) {
							jsonString += "\"code\":\"" + alert.getCodes()[diagnosticTroubleCode].getCode() + "\"";
						}
						jsonString += "}";
						if ((diagnosticTroubleCode + 1) < alert.getCodes().length
								&& alert.getCodes()[diagnosticTroubleCode + 1] != null) {
							jsonString += ",";
						}
					}
				}
				jsonString += "]";
			}
		}
		if (alert.getVehicleId() != null && !alert.getVehicleId().startsWith("\"")
				&& !alert.getVehicleId().endsWith("\"") && alert.getDateTime() != 0L && alert.getCodes() == null) {
			jsonString += "\"vehicleId\":" + "\"" + alert.getVehicleId() + "\"" + "," + "\"dateTime\":"
					+ alert.getDateTime();
		}
		if (alert.getVehicleId() != null && alert.getVehicleId().startsWith("\"") && alert.getVehicleId().endsWith("\"")
				&& alert.getDateTime() != 0L && alert.getCodes() == null) {
			jsonString += "\"vehicleId\":" + "\"\\\"" + alert.getVehicleId().replace("\"", "") + "\\\"\"" + ","
					+ "\"dateTime\":" + alert.getDateTime();
		}

		if (alert.getVehicleId() == null && alert.getDateTime() != 0L && alert.getCodes() != null) {
			jsonString += "\"dateTime\":" + alert.getDateTime();
			if (alert.getCodes().length > 0) {
				jsonString += "," + "[";
				for (int diagnosticTroubleCode = 0; diagnosticTroubleCode < alert
						.getCodes().length; diagnosticTroubleCode++) {
					if (alert.getCodes()[diagnosticTroubleCode] != null) {
						jsonString += "{";
						if (alert.getCodes()[diagnosticTroubleCode].getCode() != null) {
							jsonString += "\"code\":\"" + alert.getCodes()[diagnosticTroubleCode].getCode() + "\"";
						}
						jsonString += "}";
						if ((diagnosticTroubleCode + 1) < alert.getCodes().length
								&& alert.getCodes()[diagnosticTroubleCode + 1] != null) {
							jsonString += ",";
						}
					}
				}
				jsonString += "]";
			}
		}
		if (alert.getVehicleId() != null && !alert.getVehicleId().startsWith("\"")
				&& !alert.getVehicleId().endsWith("\"") && alert.getDateTime() == 0L && alert.getCodes() != null) {
			jsonString += "\"vehicleId\":" + "\"" + alert.getVehicleId() + "\"";
			if (alert.getCodes().length > 0) {
				jsonString += "," + "[";
				for (int diagnosticTroubleCode = 0; diagnosticTroubleCode < alert
						.getCodes().length; diagnosticTroubleCode++) {
					if (alert.getCodes()[diagnosticTroubleCode] != null) {
						jsonString += "{";
						if (alert.getCodes()[diagnosticTroubleCode].getCode() != null) {
							jsonString += "\"code\":\"" + alert.getCodes()[diagnosticTroubleCode].getCode() + "\"";
						}
						jsonString += "}";
						if ((diagnosticTroubleCode + 1) < alert.getCodes().length
								&& alert.getCodes()[diagnosticTroubleCode + 1] != null) {
							jsonString += ",";
						}
					}
				}
				jsonString += "]";
			}
		}
		if (alert.getVehicleId() != null && alert.getVehicleId().startsWith("\"") && alert.getVehicleId().endsWith("\"")
				&& alert.getDateTime() == 0L && alert.getCodes() != null) {
			jsonString += "\"vehicleId\":" + "\"\\\"" + alert.getVehicleId().replace("\"", "") + "\\\"\"";
			if (alert.getCodes().length > 0) {
				jsonString += "," + "[";
				for (int diagnosticTroubleCode = 0; diagnosticTroubleCode < alert
						.getCodes().length; diagnosticTroubleCode++) {
					if (alert.getCodes()[diagnosticTroubleCode] != null) {
						jsonString += "{";
						if (alert.getCodes()[diagnosticTroubleCode].getCode() != null) {
							jsonString += "\"code\":\"" + alert.getCodes()[diagnosticTroubleCode].getCode() + "\"";
						}
						jsonString += "}";
						if ((diagnosticTroubleCode + 1) < alert.getCodes().length
								&& alert.getCodes()[diagnosticTroubleCode + 1] != null) {
							jsonString += ",";
						}
					}
				}
				jsonString += "]";
			}
		}
		if (alert.getVehicleId() != null && !alert.getVehicleId().startsWith("\"")
				&& !alert.getVehicleId().endsWith("\"") && alert.getDateTime() != 0L && alert.getCodes() != null) {
			jsonString += "\"vehicleId\":" + "\"" + alert.getVehicleId() + "\"" + "," + "\"dateTime\":"
					+ alert.getDateTime();
			if (alert.getCodes().length > 0) {
				jsonString += "," + "[";
				for (int diagnosticTroubleCode = 0; diagnosticTroubleCode < alert
						.getCodes().length; diagnosticTroubleCode++) {
					if (alert.getCodes()[diagnosticTroubleCode] != null) {
						jsonString += "{";
						if (alert.getCodes()[diagnosticTroubleCode].getCode() != null) {
							jsonString += "\"code\":\"" + alert.getCodes()[diagnosticTroubleCode].getCode() + "\"";
						}
						jsonString += "}";
						if ((diagnosticTroubleCode + 1) < alert.getCodes().length
								&& alert.getCodes()[diagnosticTroubleCode + 1] != null) {
							jsonString += ",";
						}
					}
				}
				jsonString += "]";
			}
		}
		if (alert.getVehicleId() != null && alert.getVehicleId().startsWith("\"") && alert.getVehicleId().endsWith("\"")
				&& alert.getDateTime() != 0L && alert.getCodes() != null) {
			jsonString += "\"vehicleId\":" + "\"\\\"" + alert.getVehicleId().replace("\"", "") + "\\\"\"" + ","
					+ "\"dateTime\":" + alert.getDateTime();
			if (alert.getCodes().length > 0) {
				jsonString += "," + "[";
				for (int diagnosticTroubleCode = 0; diagnosticTroubleCode < alert
						.getCodes().length; diagnosticTroubleCode++) {
					if (alert.getCodes()[diagnosticTroubleCode] != null) {
						jsonString += "{";
						if (alert.getCodes()[diagnosticTroubleCode].getCode() != null) {
							jsonString += "\"code\":\"" + alert.getCodes()[diagnosticTroubleCode].getCode() + "\"";
						}
						jsonString += "}";
						if ((diagnosticTroubleCode + 1) < alert.getCodes().length
								&& alert.getCodes()[diagnosticTroubleCode + 1] != null) {
							jsonString += ",";
						}
					}
				}
				jsonString += "]";
			}
		}
		jsonString += "}";
		return jsonString;
	}
}
