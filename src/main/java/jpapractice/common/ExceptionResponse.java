package jpapractice.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExceptionResponse {
	private String code;
	private String message;

	@Builder
	private ExceptionResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ExceptionResponse of(String code, String message) {
		return ExceptionResponse.builder()
			.code(code)
			.message(message)
			.build();
	}
}
