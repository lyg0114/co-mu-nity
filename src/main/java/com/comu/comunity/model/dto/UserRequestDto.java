package com.comu.comunity.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : iyeong-gyo
 * @package : com.comu.comunity.model.dto
 * @since : 19.10.24
 */

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

	private String email;

	private String password;

	private LocalDate birthDaty;
}
