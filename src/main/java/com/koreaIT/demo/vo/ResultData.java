package com.koreaIT.demo.vo;

import lombok.Data;

@Data
public class ResultData<DT> {
	private String resultCode;
	private String msg;
	private String data1Name; // 보여줄려는 데이터들의 이름
	private DT data1; // 객체 데이터 타입

	public static <DT> ResultData<DT> from(String resultCode, String msg) {
		return from(resultCode, null, msg, null);
	}

	public static <DT> ResultData<DT> from(String resultCode, String data1Name, String msg, DT data1) {
		ResultData<DT> rd = new ResultData<>();
		rd.resultCode = resultCode;
		rd.data1Name = data1Name;
		rd.msg = msg;
		rd.data1 = data1;

		return rd;
	}

	public boolean isSuccess() {
		return this.resultCode.startsWith("S-");
	}

	public boolean isFail() {
		return isSuccess() == false;
	}
}
