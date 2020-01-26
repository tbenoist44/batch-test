/**
 * 
 */
package fr.batch.data;

import fr.batch.data.exception.ReferenceException;
import lombok.Data;

/**
 * @author Thierry
 *
 */
@Data
public class Reference {

	private String numReference;
	private int size;
	private double price;
	private ReferenceType type;

	public static Reference fromString(String data) throws ReferenceException {
		String[] datas = data.split(";");
		if (datas.length < 4) {
			throw new ReferenceException("Nombre de paramètre insuffisant");
		}
		Reference reference = new Reference();
		reference.setNumReference(datas[0]);

		try {
			reference.setType(ReferenceType.valueOf(datas[1]));
		} catch (IllegalArgumentException e) {
			throw new ReferenceException("Incorrect value for color");
		}

		try {
			reference.setPrice(Double.parseDouble(datas[2]));
		} catch (NumberFormatException e) {
			throw new ReferenceException("price : format incorrecte");
		}

		try {
			reference.setSize(Integer.parseInt(datas[3]));
		} catch (NumberFormatException e) {
			throw new ReferenceException("size : format incorrecte");
		}

		return reference;
	}
}
