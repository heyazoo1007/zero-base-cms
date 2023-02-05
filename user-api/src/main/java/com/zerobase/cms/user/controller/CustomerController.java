package com.zerobase.cms.user.controller;

import com.zerobase.cms.domain.common.UserVo;
import com.zerobase.cms.domain.config.JwtAuthenticationProvider;
import com.zerobase.cms.user.domain.customer.ChangeBalanceForm;
import com.zerobase.cms.user.domain.customer.CustomerDto;
import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.domain.model.CustomerBalanceHistory;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import com.zerobase.cms.user.service.customer.CustomerBalanceService;
import com.zerobase.cms.user.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;
    private final CustomerBalanceService customerBalanceService;

    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH_TOKEN") String token) {
        UserVo userVo = provider.getUserVo(token);
        Customer customer = customerService.findByIdAndEmail(userVo.getId(), userVo.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );
        return ResponseEntity.ok(CustomerDto.from(customer));
    }

    @PostMapping("/balance")
    public ResponseEntity<Integer> changeBalance(
            @RequestHeader(name = "X-AUTH_TOKEN") String token,
            @RequestBody ChangeBalanceForm form
    ) {
        UserVo userVo = provider.getUserVo(token);

        return ResponseEntity.ok(customerBalanceService.changeBalance(userVo.getId(), form).getCurrentMoney());
    }
}
