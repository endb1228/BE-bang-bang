package com.bangbang.heritage.controller;

import com.bangbang.heritage.domain.Heritage;
import com.bangbang.heritage.dto.HeritageResponse;
import com.bangbang.heritage.service.HeritageService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heritage")
public class HeritageController {

    private final HeritageService heritageService;

    public HeritageController(HeritageService heritageService) {
        this.heritageService = heritageService;
    }

    @ApiResponse(responseCode = "200", description = "문화유산 목록 조회 성공")
    @GetMapping
    public List<HeritageResponse> getHeritageList() {
        List<Heritage> heritageList = heritageService.getHeritageList();
        return heritageList.stream().map(HeritageResponse::from).collect(Collectors.toList());
    }
}
