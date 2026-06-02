package com.skillverse.enroll.client;

import com.skillverse.enroll.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @GetMapping("/users/internal/{userId}")
    UserInfo getUserById(@PathVariable Long userId,
                         @RequestHeader("X-Internal-Call") String internal);
}
