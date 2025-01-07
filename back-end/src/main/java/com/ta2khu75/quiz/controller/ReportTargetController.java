package com.ta2khu75.quiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.request.ReportTargetRequest;
import com.ta2khu75.quiz.model.response.ReportTargetResponse;
import com.ta2khu75.quiz.service.ReportTargetService;

@Controller
@RequestMapping("${app.api-prefix}/report-targets")
public class ReportTargetController extends BaseController<ReportTargetService> {

	public ReportTargetController(ReportTargetService service) {
		super(service);
	}

	@PostMapping
	@EndpointMapping(name = "Create report")
	public ResponseEntity<ReportTargetResponse> create(@ModelAttribute ReportTargetRequest request) {
		return ResponseEntity.ok(service.create(request));
	}

	@DeleteMapping("{id}")
	@EndpointMapping(name = "Delete report")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
