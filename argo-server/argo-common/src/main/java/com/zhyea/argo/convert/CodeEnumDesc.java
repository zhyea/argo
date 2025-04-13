package com.zhyea.argo.convert;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.chobit.commons.contract.CodeDescEnum;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举类转换注解
 *
 * @author robin
 * @since 2025/4/13 22:53
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = CodeEnumDesc.CodeDescEnumSerializer.class)
@JacksonAnnotationsInside
public @interface CodeEnumDesc {


	Class<? extends Enum<? extends CodeDescEnum>> value();


	class CodeDescEnumSerializer extends JsonSerializer<Integer> implements ContextualSerializer {


		private Class<? extends Enum<? extends CodeDescEnum>> enumClass;


		public CodeDescEnumSerializer(Class<? extends Enum<? extends CodeDescEnum>> enumClass) {
			this.enumClass = enumClass;
		}


		@Override
		public void serialize(Integer code, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException {

			CodeDescEnum codeDescEnum = enumOf(code, enumClass);
			if (null == codeDescEnum) {
				return;
			}

			jsonGenerator.writeString(codeDescEnum.getDesc());
		}


		CodeDescEnum enumOf(Integer code, Class<? extends Enum<? extends CodeDescEnum>> clazz) {
			if (null == code) {
				return null;
			}

			if (!CodeDescEnum.class.isAssignableFrom(clazz)) {
				return null;
			}

			Enum<?>[] values = clazz.getEnumConstants();
			for (Enum<?> e : values) {
				CodeDescEnum val = (CodeDescEnum) e;
				if (code.equals(val.getCode())) {
					return val;
				}
			}

			return null;
		}

		@Override
		public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property)
				throws JsonMappingException {

			CodeEnumDesc annotation = property.getAnnotation(CodeEnumDesc.class);
			if (null != annotation) {
				this.enumClass = annotation.value();
				return new CodeDescEnumSerializer(enumClass);
			}

			return provider.findValueSerializer(property.getType(), property);
		}
	}
}
