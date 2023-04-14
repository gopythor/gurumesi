package com.zerobase.gurumesi.book.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakeBookingForm {
    @Schema(example = "1")
    private Long restaurantId;

    @Schema(example = "1")
    private Long customerId;

    @Schema(example = "2023")
    @Min(value= 2023, message ="2023년부터 이용 가능합니다.")
    private int year;

    @Schema(example = "4")
    @Min(value = 1, message = "1월부터 입력하세요.")
    @Max(value = 12, message = "12월까지 가능합니다.")
    private int month;

    @Schema(example = "14")
    @Min(value= 1, message = "1일부터 가능합니다.")
    @Max(value = 31, message = "31일을 초과할 수 없습니다.")
    private int day;

    @Schema(example = "14")
    @Min(value= 0, message = "0시부터 이용 가능합니다.")
    @Max(value = 24, message = "24시까지 이용 가능합니다.")
    private int hour;

    @Schema(example = "10")
    @Min(value= 0, message = "0분부터 이용 가능합니다.")
    @Max(value = 50, message = "0-59 사이 숫자로 입력해주세요.")
    private int minute;

}
