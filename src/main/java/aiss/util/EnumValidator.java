package aiss.util;

public class EnumValidator {
	
	public static <E extends Enum<E>> boolean isValidEnum (Class<E> enumClass, String toCheck) {
		
		for (E value : enumClass.getEnumConstants()) {
			if(value.toString().equals(toCheck))
				return true;
		}
		
		return false;
	}
	
}
