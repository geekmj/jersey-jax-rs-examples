package in.geekmj.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import in.geekmj.model.DateParamModel;

@Provider
public class DateParamModelConvertorProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

		if (rawType.getName().equals(DateParamModel.class.getName())) {
			return new ParamConverter<T>() {

				@SuppressWarnings("unchecked")
				@Override
				public T fromString(String value) {
					DateParamModel dateParamModel = new DateParamModel();
					dateParamModel.setDateAsString(value);
					return (T) dateParamModel;
				}

				@Override
				public String toString(T bean) {
					return ((DateParamModel) bean).getDateAsString();
				}

			};
		}

		return null;
	}

}
