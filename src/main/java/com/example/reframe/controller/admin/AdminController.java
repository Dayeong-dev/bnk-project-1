package com.example.reframe.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
//	@Autowired
//	private AccessLogService accessLogService;

	@GetMapping("/product-main")
	public String root() {
		return "admin/product-main";
	}

	@GetMapping("/sidebar")
	public String sidebar() {
		return "admin/fragments/sidebar";
	}

	@GetMapping("/header")
	public String header() {
		return "admin/fragments/header";
	}

	@GetMapping("/layout")
	public String layout() {
		return "admin/fragments/layout";
	}

	@GetMapping("/card-main")
	public String cardMain() {
		return "admin/card-main";
	}
	
	@GetMapping("/user-main")
	public String userMain() {
		return "admin/user-main";
	}
	
	@GetMapping("/signin-form")
	public String getSignin() {
		return "admin/signin-form";
	}
	
	@GetMapping("/approval-box")
	public String approvalBox() {
		return "admin/approval-box";
	}
	@GetMapping("/card-approval-box")
	public String cardApprovalBox() {
		return "admin/card-approval-box";
	}
	
//	@PostMapping("/login")
//	public String postSignin() {
//		return "admin/signin-form";
//	}
	
	
	/*
	 * 차트 4개를 한 페이지로 통합  -> AdminChartController로 이동 
	 * 
	@GetMapping("/chart-main")
	public String chartMain(Model model){

		List<DailyVisitDTO> list = accessLogService.getDailyStats();

		List<String> labels = new ArrayList<>();
		List<Long> counts = new ArrayList<>();

		for (DailyVisitDTO dto : list) {
			labels.add(dto.getDate());
			counts.add(dto.getCount());
		}

		model.addAttribute("labels", labels);
		model.addAttribute("counts", counts);
		return "admin/chart/chart-main";
	}
	*/

}
