package com.example.reframe.controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reframe.dto.DepositProductContentDTO;
import com.example.reframe.dto.DepositProductDTO;
import com.example.reframe.dto.ProductStatusUpdateRequestDTO;
import com.example.reframe.entity.DepositProduct;
import com.example.reframe.entity.Document;
import com.example.reframe.entity.admin.ApprovalRequest;
import com.example.reframe.entity.admin.ApprovalRequestDetail;
import com.example.reframe.repository.ApprovalRequestRepository;
import com.example.reframe.repository.DepositProductRepository;
import com.example.reframe.service.ApprovalService;
import com.example.reframe.service.ProductService;
import com.example.reframe.util.SessionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/admin")
public class AdminTestController {

	@Autowired
	private DepositProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ApprovalService approvalService;

	@Autowired
	private ApprovalRequestRepository approvalRequestRepository;

	
	/*
	 * 이중 필터 사용 -> filterProducts() 메서드로 통합 
	 * 
	// 모든 상품 데이터 반환 및 필터에 따른 데이터 반환 
	@GetMapping("/products/{value}")
	public List<DepositProductDTO> getProducts(@PathVariable("value") String value) {
        List<DepositProduct> products;

        if ("all".equals(value)) {
            products = productRepository.findByStatus("S"); 
        } else {
            products = productRepository.findByCategoryAndStatus(value, "S");
        }

        List<DepositProductDTO> dtoList = new ArrayList<>();

        for (DepositProduct product : products) {
            DepositProductDTO dto = convertToDTO(product);
            dtoList.add(dto);
        }

        return dtoList;
    }
	
	@GetMapping("/products/status/{status}")
	public List<DepositProductDTO> getProductsByStatus(@PathVariable("status")String status) {
		List<DepositProduct> products = productRepository.findByStatus(status);
		
		List<DepositProductDTO> dtoList = new ArrayList<>();
		for (DepositProduct product : products) {
			dtoList.add(convertToDTO(product));
		}
		return dtoList;
	}
	*/
	
	// 종류 및 상태 이중 필터 따른 데이터 출력 
	@GetMapping("/products/filter")
	public List<DepositProductDTO> filterProducts(@RequestParam("category") String category,
	        									  @RequestParam("status") String status) {
	    
	    List<DepositProduct> result;

	    if ("all".equals(category) && "all".equals(status)) {
	        result = productRepository.findAll();
	    } else if (!"all".equals(category) && "all".equals(status)) {
	        result = productRepository.findByCategory(category);
	    } else if ("all".equals(category)) {
	        result = productRepository.findByStatus(status);
	    } else {
	        result = productRepository.findByCategoryAndStatus(category, status);
	    }

	    return result.stream()
	                 .map(this::convertToDTO)
	                 .collect(Collectors.toList());
	}
	
	// 활성화 및 비활성화 버튼 누르고 상태 데이터 반환
	@PostMapping("/products/toggle")
	public ResponseEntity<Void> toggleProductActive(@RequestBody Map<String, Object> payload) {
//	    
//		List<Integer> ids = (List<Integer>) payload.get("ids");
//	    Boolean active = (Boolean) payload.get("active");
//

	    return ResponseEntity.ok().build();
	}
	
	// 상품 디테일 데이터 반환 
	@GetMapping("/products/detail/{productId}")
	public DepositProductDTO getProductDetail(@PathVariable("productId") Long productId) {
		DepositProduct product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. ID: " + productId));
		
		try {
			// JSON 데이터를 객체로 매핑
			ObjectMapper objectMapper = new ObjectMapper();
			
			List<DepositProductContentDTO> productContentList;
			
			if (product.getDetail() != null && !product.getDetail().isBlank()) {
				productContentList = objectMapper.readValue(
					product.getDetail(),
				    new TypeReference<List<DepositProductContentDTO>>(){}
				);
			} else {
				productContentList = Collections.emptyList(); // 또는 null 처리
			}

			DepositProductDTO result = convertToDTO(product);
			result.setProductContentList(productContentList);

			return result;
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

	// 전체일 경우 "/status" 단독 요청 처리
	@GetMapping("/products/status")
	public List<DepositProductDTO> getAllProducts() {
	    List<DepositProduct> products = productRepository.findAll();

	    List<DepositProductDTO> dtoList = new ArrayList<>();
	    for (DepositProduct product : products) {
	        dtoList.add(convertToDTO(product));
	    }
	    return dtoList;
	}
	
	
	// 상태 일괄 변경 요청 처리
	@PostMapping("/products/status-update")
	public ResponseEntity<Void> updateProductStatuses(@RequestBody ProductStatusUpdateRequestDTO request) {
	    productService.updateStatuses(request.getIds(), request.getStatus());
	    return ResponseEntity.ok().build();
	}
	
	// 상품 등록 
	@PostMapping("/products/create")
	@Transactional
	public ResponseEntity<Void> createProduct(@RequestBody DepositProductDTO dto) {      
		try {
			// 수정된 예적금 상세 설명 저장
		    List<DepositProductContentDTO> insertContents = dto.getProductContentList();
		    
		    ObjectMapper objectMapper = new ObjectMapper();	  
		    
			String json = objectMapper.writeValueAsString(insertContents);	// JSON 형태로 저장
			dto.setDetail(json);
			
			DepositProduct product = DepositProduct.builder()
			        .name(dto.getName())
			        .category(dto.getCategory())
			        .purpose(dto.getPurpose())
			        .summary(dto.getSummary())
			        .detail(dto.getDetail())
			        .modalDetail(dto.getModalDetail())	// 추가
			        .modalRate(dto.getModalRate())		// 추가
			        .term(dto.getTermId() != null ? new Document(dto.getTermId()) : null)		// 추가
			        .manual(dto.getManualId() != null ? new Document(dto.getManualId()) : null)	// 추가
			        .minRate(dto.getMinRate())
			        .maxRate(dto.getMaxRate())
			        .period(dto.getPeriod())
			        .status("S") // 기본값
			        .imageUrl(dto.getImageUrl())
			        .viewCount(0L)
			        .build();

		    productRepository.save(product);
		    
		    return ResponseEntity.ok().build();
		    
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	// 상세보기에서 수정 데이터 받기 
	@PutMapping("/products/update")
	public ResponseEntity<Void> updateProduct(@RequestBody DepositProductDTO dto,
			HttpSession session) {
		/*
		 * 결재 프로세스로 삭제
	    DepositProduct product = productRepository.findById(dto.getProductId())
	        .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않음"));
		
	    product.setName(dto.getName());	
	    product.setCategory(dto.getCategory());
	    product.setPurpose(dto.getPurpose());
	    product.setSummary(dto.getSummary());
	    product.setDetail(dto.getDetail());
	    product.setMinRate(dto.getMinRate());
	    product.setMaxRate(dto.getMaxRate());
	    product.setPeriod(dto.getPeriod());
	    product.setStatus(dto.getStatus());
		 */
		
		/* 상세 설명 수정 */
	    // 수정된 예적금 상세 설명 저장
	    List<DepositProductContentDTO> updateContents = dto.getProductContentList();
	    ObjectMapper objectMapper = new ObjectMapper();

		try {
			String json = objectMapper.writeValueAsString(updateContents);		// JSON 형태로 저장
			dto.setDetail(json);
			
			approvalService.requestApproval(dto, SessionUtil.getLoginUser(session).getUsername());
			
			return ResponseEntity.ok().build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	    
	}
	
	// 결재 승인
	@PostMapping("/approvals/{id}/approve")
	public ResponseEntity<Void> approveRequest(@PathVariable("id") Long id,
											  HttpSession session) {
	    approvalService.approveRequest(id, SessionUtil.getLoginUser(session).getUsername()); // TODO: 로그인 정보로 대체
	    return ResponseEntity.ok().build();
	}
	
	// 결재 반려
	@PostMapping("/approvals/{id}/reject")
	public ResponseEntity<Void> rejectRequest(@PathVariable("id") Long id,
											  @RequestBody Map<String, String> body,
											  HttpSession session) {
	    String reason = body.get("reason");
	    approvalService.rejectRequest(id, SessionUtil.getLoginUser(session).getUsername(), reason); // TODO: 로그인 정보로 대체
	    return ResponseEntity.ok().build();
	}
	
	// 결재 요청 목록 반환
	@GetMapping("/approvals")
	public List<ApprovalRequest> getAllPendingRequests() {
		List<ApprovalRequest> list = approvalRequestRepository.findByStatus("PENDING");
		return list ;
	}
	
	// 결재 요청 상세보기 
	@GetMapping("/approvals/{id}")
	public ApprovalRequest getApprovalDetail(@PathVariable("id")Long id) {
	    return approvalRequestRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("요청 없음"));
	}

	 @GetMapping("/approval/my")
	 public List<ApprovalRequest> getMyRequests(HttpSession session) {
	     String username = SessionUtil.getLoginUser(session).getUsername();
	     List<ApprovalRequest> requests = approvalService.getMyRequests(username);
	     System.out.println("요청 확인 ..........."+requests );
	     return requests;
	 }

	 @GetMapping("/approval/my/{status}")
	 public List<ApprovalRequest> getMyRequestsByStatus(@PathVariable("status") String status,
	                                                                     HttpSession session) {
	     String username = SessionUtil.getLoginUser(session).getUsername();
	     List<ApprovalRequest> requests = approvalService.getMyRequestsByStatus(username, status.toUpperCase());
	     return requests;
	 }	
	 @GetMapping("/approval/details/{requestId}")
	 public List<ApprovalRequestDetail> getRequestDetails(@PathVariable("requestId") Long requestId) {
	     Optional<ApprovalRequest> requestOpt = approvalRequestRepository.findById(requestId);
	     if (requestOpt.isPresent()) {
	         List<ApprovalRequestDetail> details = requestOpt.get().getDetails();
	         return details;
	     } else {
	         return null;
	     }
	 }
	
	
	// DTO로 컨버전
	private DepositProductDTO convertToDTO(DepositProduct product) {
	    return DepositProductDTO.builder()
	            .productId(product.getProductId())
	            .name(product.getName())
	            .category(product.getCategory())
	            .purpose(product.getPurpose())
	            .summary(product.getSummary())
	            .detail(product.getDetail())
	            .modalDetail(product.getModalDetail())	// 추가
	            .modalRate(product.getModalRate())		// 추가
	            .termId(product.getTerm() != null ? product.getTerm().getDocumentId() : null)		// 추가
	            .manualId(product.getManual() != null ? product.getManual().getDocumentId() : null)	// 추가
	            .maxRate(product.getMaxRate())
	            .minRate(product.getMinRate())
	            .period(product.getPeriod())
	            .status(product.getStatus())
	            .createdAt(product.getCreatedAt().toString())
	            .viewCount(product.getViewCount())
	            .imageUrl(product.getImageUrl())
	            .build();
	}
	
}
