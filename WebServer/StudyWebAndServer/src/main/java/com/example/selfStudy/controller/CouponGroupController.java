package com.example.selfStudy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon-groups")
public class CouponGroupController {

    /**
     * 1. 쿠폰 그룹 생성
     */
    @PostMapping("/{code}")
    public ResponseEntity<?> createGroup(@PathVariable("code") String couponeCode) {
        return null;
    }

    /**
     * 2. 쿠폰 그룹 수정
     */
    @PutMapping("/{code}")
    public ResponseEntity<?> changeGroup(@PathVariable("code") String couponeCode) {
        return null;
    }
    /**
     * 3. 쿠폰 그룹 발행
     */
    @PostMapping("/{code}/publish")
    public ResponseEntity<?> publishGroup(@PathVariable("code") String couponeCode) {
        return null;
    }

    /**
     * 4. 쿠폰 그룹 비활성화
     */
    @PostMapping("/{code}/disalbe")
    public ResponseEntity<?> disableGroup(@PathVariable("code") String couponeCode) {
        return null;
    }

}
