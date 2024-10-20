package com.comu.comunity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comu.comunity.auth.JwtTokenProvider;
import com.comu.comunity.auth.TokenInfo;
import com.comu.comunity.model.dto.UserRequestDto;
import com.comu.comunity.model.dto.UserResponseDto;
import com.comu.comunity.model.entity.Member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final JwtTokenProvider jwtTokenProvider;

	@PostMapping("/auth/member")
	public UserResponseDto register(@RequestBody @Valid UserRequestDto userRequestDto) {

		log.info("=====================================");
		log.info("현재 reqest를 요청한 eamil : {}", jwtTokenProvider.getEmail());
		log.info("MemberController.register 호출 성공");
		log.info("=====================================");

		return UserResponseDto.builder()
			.build();
	}

	@GetMapping("/api/test")
	public UserResponseDto test() {
		log.info("=====================================");
		log.info("현재 reqest를 요청한 eamil : {}", jwtTokenProvider.getEmail());
		log.info("MemberController.register 호출 성공");
		log.info("=====================================");

		return UserResponseDto.builder()
			.message("SUCCESS")
			.build();
	}

	@PostMapping("/auth/login")
	public TokenInfo login(@RequestBody UserRequestDto userRequestDto) {

		log.info("=====================================");
		log.info("현재 reqest를 요청한 eamil : {}", userRequestDto.getEmail());
		log.info("MemberController.register 호출 성공");
		log.info("=====================================");

		return TokenInfo.builder()
			.email(userRequestDto.getEmail())
			.token(jwtTokenProvider.createToken(Member.builder()
				.email(userRequestDto.getEmail()).build()))
			.build();

	}

}
