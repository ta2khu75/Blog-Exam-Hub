package com.ta2khu75.quiz.util;

import com.ta2khu75.quiz.enviroment.EmailTemplateEnv;

public class EmailTemplateUtil {
	private EmailTemplateUtil() {
		throw new IllegalStateException("Utility class");
    }
	public static String getVerify(String code) {
		StringBuilder builder=new StringBuilder(EmailTemplateEnv.EMAIL_VERIFICATION_HEADER);
		builder.append(code);
		builder.append("\" ");
		builder.append(EmailTemplateEnv.EMAIL_VERIFICATION_FOOTER);
		return builder.toString();
	}

}
