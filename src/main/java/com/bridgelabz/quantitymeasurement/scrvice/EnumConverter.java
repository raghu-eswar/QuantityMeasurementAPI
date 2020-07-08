package com.bridgelabz.quantitymeasurement.scrvice;

import com.bridgelabz.quantitymeasurement.exceptions.EnumConversionFailedException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class EnumConverter implements ConverterFactory<String, Enum > {

   @Override
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToEnum<>(targetType);
	}

	private static class StringToEnum<T extends Enum> implements Converter<String, T> {

		private final Class<T> enumType;

		public StringToEnum(Class<T> enumType) {
			this.enumType = enumType;
		}

		@Override
		public T convert(String source) {
            try {
                return (T) T.valueOf(this.enumType, source.toUpperCase());
            }catch (IllegalArgumentException e){
                throw new EnumConversionFailedException(source+" is not a proper unit");
            }
		}
	}
}
