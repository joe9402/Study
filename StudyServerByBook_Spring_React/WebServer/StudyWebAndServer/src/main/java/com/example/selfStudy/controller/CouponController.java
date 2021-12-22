package com.example.selfStudy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    /**
     * 5. 쿠폰 다운로드
     */
    @PostMapping("/{code}/download")
    public ResponseEntity<?> downloadCoupons(@PathVariable("code") String couponeCode) {
        return null;
    }

    /**
     * 6. 쿠폰 사용
     */
    @PostMapping("/{code}/use")
    public ResponseEntity<?> useCoupons(@PathVariable("code") String couponeCode) {
        return null;
    }
    /**
     * 7. 쿠폰 목록 조회
     */
    @GetMapping
    public ResponseEntity<?> findCoupons(@RequestParam long page, @RequestParam int size){
        return null;
    }

}
