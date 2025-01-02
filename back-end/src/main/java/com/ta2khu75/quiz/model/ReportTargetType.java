package com.ta2khu75.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReportTargetType {
	MISLEADING_INFORMATION("Thông tin sai lệch hoặc gây hiểu lầm"), HATE_SPEECH("Nội dung kích động thù hận"),
	VIOLENT_CONTENT("Nội dung bạo lực hoặc gây sốc"), INAPPROPRIATE_CONTENT("Nội dung khiêu dâm hoặc không phù hợp"),
	SPAM("Nội dung spam hoặc quảng cáo sai lệch"), COPYRIGHT_INFRINGEMENT("Vi phạm bản quyền"),
	FRAUDULENT_ACTIVITY("Hoạt động lừa đảo hoặc gây nguy hại"),
	ILLEGAL_ACTIVITY("Nội dung liên quan đến chất cấm hoặc hoạt động bất hợp pháp"),
	PRIVACY_VIOLATION("Vi phạm quyền riêng tư"), HARASSMENT("Xúc phạm hoặc quấy rối");

	private final String description;
}
