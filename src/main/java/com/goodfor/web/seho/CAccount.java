package com.goodfor.web.seho;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class CAccount {
	private String accountnum, cpw, cid, cdate, udate;
	private int balance;

}
