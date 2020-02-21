package com.goodfor.web.seho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.goodfor.web.customer.CustomerMapper;
import com.goodfor.web.customer.CustomerServiceImpl;
import com.goodfor.web.pxy.Box;
import com.goodfor.web.pxy.CrawlingProxy;
import com.goodfor.web.pxy.Trunk;

@RestController
@RequestMapping("/tradings")
public class TradingCtrl {
	@Autowired CrawlingProxy crawler;
	@Autowired Map<String, Object> map;
	@Autowired Box<List<String>> box;
	@Autowired TradingServiceImpl tservice;
	@Autowired Trading trading;
	@Autowired CustomerServiceImpl cservice;
	
	@GetMapping("/ingcrawl")
	public ArrayList<HashMap<String, String>> ingcrawl() {
		return crawler.ingCrawl();
	}
	
	@GetMapping("/insert/tradingtable")
	public Map<?,?> insertTrading(){
		HashMap<String, String> paramMap = new HashMap<>();
		crawler.ingCrawl2();
		paramMap.clear();
		paramMap.put("msg", "SUCCESS");
		return paramMap;
	}

	@GetMapping("/bizinfo")
	public ArrayList<HashMap<String, String>> bizinfo() {
		return crawler.bizinfoCrawl();
	}

	@PostMapping("/detail/{stockname}")
	public Map<?, ?> detail(@PathVariable String stockname) {
		tservice.searchStockname(stockname);
		map.clear();
		map.put("msg", "success");
		map.put("trading", tservice.search(stockname));
		return map;
	}

	@GetMapping("/msstock")
	public Map<?, ?> msstock() {
		return map;
	}

	@GetMapping("/mdstock")
	public Map<?, ?> mdstock() {
		return map;
	}

	 @PostMapping("/addstock") 
	 public Map<?, ?> addstock(@RequestBody Interest param){
	 tservice.insertInterest(param);
	 map.clear(); 
	 map.put("msg","success");
	 return map; 
	 }
	 
	 @GetMapping("/addstockinfo/{cid}")
	 public Map<?, ?> addstockinfo(@PathVariable String cid){
		 ArrayList<Object> al = new ArrayList<Object>();
		 map.clear();
		 if(tservice.inCount(cid) != 0) {
			 for(int i = 0; i < tservice.inCount(cid); i++) {
					al.add(tservice.searchStockname(tservice.searchSeqInterest(Integer.parseInt(tservice.searchInterest(cid).get(i)), cid)));
				}
			 map.put("addlist", al);
			 map.put("msg","success");
			 map.put("size",tservice.inCount(cid));
		 }
		 return map;
	 }
	 
	 @GetMapping("/delstock/{stockname}")
	 public Map<?,?> delstock(@PathVariable String stockname){
		 System.out.println();
		 map.clear();
		 tservice.deleteInterest(tservice.searchInSeq(stockname));
		 map.put("msg", "success");
		 return map;
	 }
	 	 
	 @PostMapping("/msmarket")
	 public Map<?, ?> msmarket(@RequestBody BuyStock param){
		 String conprice = String.valueOf((Integer.parseInt(param.getOrderprice())*Integer.parseInt(param.getOrdernum())));
		 if(tservice.searchMyStock(param.getStockcode()) == null) {
			 if(param.getOrdertype().equals("1")) {
				 param.setAccountnum(tservice.searchAnum(param.getCid()));
				 param.setConprice(conprice);
				 param.setOrdertype("시장가");
				 tservice.insertBuyStock(param);
			 }
			 MyStock mystock = new MyStock();
			 mystock.setCid(param.getCid());
			 mystock.setQuantitynum(param.getOrdernum());
			 mystock.setStockcode(param.getStockcode());
			 mystock.setAccountnum(param.getAccountnum());
			 mystock.setStockname(param.getStockname());
			 mystock.setOrderprice(param.getOrderprice());
			 mystock.setConprice(conprice);
			 tservice.insertMyStock(mystock);
			 tservice.searchCaccount(param.getCid());
			 int nbalance = tservice.searchCaccount(param.getCid()).getBalance();
			 int ubalance = nbalance - (Integer.parseInt(param.getOrdernum())*Integer.parseInt(param.getOrderprice()));
			 CAccount ca = new CAccount();
			 ca.setBalance(ubalance);
			 ca.setCid(param.getCid());
			 tservice.updateCaccount(ca);
			 map.clear();
			 map.put("msg","success");
		 }else if(tservice.searchMyStock(param.getStockcode()).getStockcode().equals(param.getStockcode())){
			 param.setAccountnum(tservice.searchAnum(param.getCid()));
			 param.setConprice(conprice);
			 tservice.insertBuyStock(param);
			 int qnum = Integer.parseInt(tservice.searchMyStock(param.getStockcode()).getQuantitynum());
			 int onum = Integer.parseInt(param.getOrdernum());
			 String quantitynum = String.valueOf((qnum+onum));
			 String uconprice = String.valueOf((Integer.parseInt(param.getOrderprice())*Integer.parseInt(param.getOrdernum())) +
			 Integer.parseInt(tservice.searchMyStock(param.getStockcode()).getConprice()));
			 MyStock mystock = new MyStock();
			 mystock.setQuantitynum(quantitynum);
			 mystock.setCid(param.getCid());
			 mystock.setStockname(param.getStockname());
			 mystock.setConprice(uconprice);
			 tservice.updateMyStock(mystock);
			 map.clear();
			 map.put("msg","success");
		 }
		 
		 return map;
	 }
	 
	 @PostMapping("/mscheck")
	 public Map<?, ?> mscheck(@RequestBody TempStock param){
		 String conprice = String.valueOf((Integer.parseInt(param.getOrdernum())*Integer.parseInt(param.getOrderprice())));
		 param.setAccountnum(tservice.searchAnum(param.getCid()));
		 param.setTradetype("매수");
		 tservice.searchStockname(param.getStockname());
		 String nprice = tservice.searchStockname(param.getStockname()).getNprice();
		 if(nprice.equals(param.getOrderprice())) {
			 param.setConwhether("체결");
			 param.setConprice(conprice);
			 tservice.insertTempStock(param);
		 }else {
			 param.setConwhether("미체결");
			 param.setConprice("");
			 tservice.insertTempStock(param);
		 }
		 map.clear();
		 map.put("msg","success");
		 return map;
	 }
	 
	 @PostMapping("/mdmarket")
	 public Map<?, ?> mdmarket(@RequestBody SellStock param){
		 String conprice = String.valueOf(Integer.parseInt(param.getOrdernum())*Integer.parseInt(param.getOrderprice()));
		 int mynum = Integer.parseInt(tservice.searchMyStock(param.getStockcode()).getQuantitynum());
		 int ordernum = Integer.parseInt(param.getOrdernum());
		 String seq = String.valueOf(tservice.searchMyStock(param.getStockcode()).getSeq());
		 if(mynum > ordernum) {
			 if(param.getOrdertype().equals("1")) {
				 param.setOrdertype("시장가");
				 param.setAccountnum(tservice.searchAnum(param.getCid()));
				 param.setConprice(conprice);
				 tservice.insertSellStock(param);
			 }
			 String quantitynum = String.valueOf((mynum-ordernum));
			 String uconprice = String.valueOf((Integer.parseInt(param.getOrderprice())*Integer.parseInt(param.getOrdernum())) -
					 Integer.parseInt(tservice.searchMyStock(param.getStockcode()).getConprice()));
			 MyStock mystock = new MyStock();
			 mystock.setConprice(uconprice);
			 mystock.setQuantitynum(quantitynum);
			 mystock.setStockname(param.getStockname());
			 mystock.setCid(param.getCid());
			 tservice.updateMyStock(mystock);
			 tservice.searchCaccount(param.getCid());
			 int nbalance = tservice.searchCaccount(param.getCid()).getBalance();
			 int ubalance = nbalance + (Integer.parseInt(param.getOrdernum())*Integer.parseInt(param.getOrderprice()));
			 CAccount ca = new CAccount();
			 ca.setBalance(ubalance);
			 ca.setCid(param.getCid());
			 tservice.updateCaccount(ca);
			 map.clear();
			 map.put("msg","success");
		 }else if(mynum == ordernum) {
			 param.setAccountnum(tservice.searchAnum(param.getCid()));
			 param.setConprice(conprice);
			 tservice.insertSellStock(param);
			 tservice.deleteMyStock(seq);
			 map.clear();
			 map.put("msg","success");
		 }else {
			 map.put("msg","check");
		 }
		 return map;
	 }
	 	 
	 @PostMapping("/mdcheck")
	 public Map<?, ?> mdcheck(@RequestBody TempStock param){
		 int mynum = Integer.parseInt(tservice.searchMyStock(param.getStockcode()).getQuantitynum());
		 int ordernum = Integer.parseInt(param.getOrdernum());
		 String conprice = String.valueOf(ordernum*Integer.parseInt(param.getOrderprice()));
		 param.setAccountnum(tservice.searchAnum(param.getCid()));
		 param.setTradetype("매도");
		 String nprice = tservice.searchStockname(param.getStockname()).getNprice();
		 if(mynum >= ordernum) {
			 if(nprice.equals(param.getOrderprice())) {
				 param.setConwhether("체결");
				 param.setConprice(conprice);
				 tservice.insertTempStock(param);
				 map.clear();
				 map.put("msg","success");
			 }else {
				 param.setConwhether("미체결");
				 param.setConprice("");
				 tservice.insertTempStock(param);
				 map.clear();
				 map.put("msg","success");
			 }
		 }else {
			 map.clear();
			 map.put("msg","check");
		 }
		 return map;
	}

	@PostMapping("/temprefreshms")
	public Map<?, ?> temprefreshms(@RequestBody TempStock param) {
		if(!(tservice.searchTempStock(param) == null)) {
			param.setConwhether("체결");
			int price = Integer.parseInt(tservice.searchTempStock(param).getOrderprice());
			int num = Integer.parseInt(tservice.searchTempStock(param).getOrdernum());
			String conprice = String.valueOf((price*num));
			String stockname = tservice.searchStockname(tservice.searchTempStock(param).getStockname()).getStockname();
			int nprice = Integer.parseInt(tservice.searchStockname(tservice.searchTempStock(param).getStockname()).getNprice().replace(",", ""));
			if(stockname.equals(tservice.searchTempStock(param).getStockname()) &&
					nprice <= Integer.parseInt(tservice.searchTempStock(param).getOrderprice())) {
				BuyStock buy = new BuyStock();
				buy.setAccountnum(tservice.searchTempStock(param).getAccountnum());
				buy.setCid(tservice.searchTempStock(param).getCid());
				buy.setCondate(tservice.searchTempStock(param).getCondate());
				buy.setConprice(conprice);
				buy.setOrdernum(tservice.searchTempStock(param).getOrdernum());
				buy.setOrderprice(tservice.searchTempStock(param).getOrderprice());
				buy.setOrdertype(tservice.searchTempStock(param).getOrdertype());
				buy.setStockcode(tservice.searchTempStock(param).getStockcode());
				buy.setStockname(tservice.searchTempStock(param).getStockname());
				tservice.insertBuyStock(buy);
				int ordernum = Integer.valueOf(tservice.searchTempStock(param).getOrdernum());
				int mynum = Integer.valueOf(tservice.searchMyStock(tservice.searchTempStock(param).getStockcode()).getQuantitynum());
				String quantitynum = String.valueOf(ordernum+mynum);
				if(tservice.searchMyStock(tservice.searchTempStock(param).getStockcode()).equals(tservice.searchTempStock(param).getStockcode())) {
					MyStock my = new MyStock();
					my.setCid(param.getCid());
					my.setStockname(tservice.searchTempStock(param).getStockname());
					my.setQuantitynum(quantitynum);
					tservice.updateMyStock(my);
				}else {
					MyStock my = new MyStock();
					my.setCid(param.getCid());
					my.setQuantitynum(quantitynum);
					my.setStockcode(tservice.searchTempStock(param).getStockcode());
					my.setAccountnum(tservice.searchTempStock(param).getAccountnum());
					my.setStockname(tservice.searchTempStock(param).getStockname());
					tservice.insertMyStock(my);
				}
				int tempseq = tservice.searchTempStock(param).getSeq();
				tservice.deleteTempStock(tempseq);
				map.clear();
				map.put("msg","success");
			}else {
				map.clear();
				map.put("msg","check");
			}
		}
		return map;
	}
	
	@PostMapping("/temprefreshmd")
	public Map<?, ?> temprefreshmd(@RequestBody TempStock param) {
		if(!(tservice.searchTempStock(param) == null)) {
			int price = Integer.parseInt(tservice.searchTempStock(param).getOrderprice());
			int num = Integer.parseInt(tservice.searchTempStock(param).getOrdernum());
			String conprice = String.valueOf((price*num));
			String stockname = tservice.searchStockname(tservice.searchTempStock(param).getStockname()).getStockname();
			int nprice = Integer.parseInt(tservice.searchStockname(tservice.searchTempStock(param).getStockname()).getNprice().replace(",", ""));
			if(stockname.equals(tservice.searchTempStock(param).getStockname()) &&
					nprice >= Integer.parseInt(tservice.searchTempStock(param).getOrderprice())) {
				param.setConwhether("체결");
				param.setConprice(conprice);
				param.setStockname(stockname);
				tservice.updateTempStock(param);
				SellStock sell = new SellStock();
				sell.setAccountnum(tservice.searchTempStock(param).getAccountnum());
				sell.setCid(tservice.searchTempStock(param).getCid());
				sell.setCondate(tservice.searchTempStock(param).getCondate());
				sell.setConprice(tservice.searchTempStock(param).getConprice());
				sell.setOrdernum(tservice.searchTempStock(param).getOrdernum());
				sell.setOrderprice(tservice.searchTempStock(param).getOrderprice());
				sell.setOrdertype(tservice.searchTempStock(param).getOrdertype());
				sell.setStockcode(tservice.searchTempStock(param).getStockcode());
				sell.setStockname(tservice.searchTempStock(param).getStockname());
				tservice.insertSellStock(sell);
				int ordernum = Integer.parseInt(tservice.searchTempStock(param).getOrdernum());
				int mynum = Integer.parseInt(tservice.searchMyStock(tservice.searchTempStock(param).getStockcode()).getQuantitynum());
				if(mynum > ordernum) {
					String quantitynum = String.valueOf(mynum-ordernum);
					MyStock mystock = new MyStock();
					mystock.setQuantitynum(quantitynum);
					mystock.setStockname(param.getStockname());
					mystock.setCid(param.getCid());
					tservice.updateMyStock(mystock);
					map.clear();
					map.put("msg","success");
				}else if(mynum == ordernum) {
					int tempseq = tservice.searchTempStock(param).getSeq();
					tservice.deleteTempStock(tempseq);
					map.clear();
					map.put("msg","success");
				}
			}else {
				map.clear();
				map.put("msg","check");
			}
		}
		return map;
	}
	
	@GetMapping("/vuemystock/{cid}")
	public HashMap<?,?> vuemystock(@PathVariable String cid){
		HashMap<String, Object> hmap = new HashMap();
		ArrayList<Object> al = new ArrayList<Object>();
		hmap.clear();
		if(tservice.searchcount(cid) != 0) {
			for(int i = 0; i < tservice.searchcount(cid); i++) {
				al.add(tservice.vueData(tservice.vueMyStock(cid).get(i).getStockname()));
			}
			hmap.put("mylistData", al);
			hmap.put("mylistAll",tservice.vueMyStock(cid));
			hmap.put("msg", "success");
			hmap.put("size", tservice.searchcount(cid));
		}
		return hmap;
	}
	
	@PostMapping("/caccount")
	public Map<?,?> caacount(@RequestBody CAccount param) {
		String cid = param.getCid();
		if(tservice.searchAccount(cid) == 0) {
				List<String> accountNum = Arrays.asList(
						"100100001","100100002","100100003","100100004","100100005",
						"100100006","100100007","100100008","100100009","100100010");
				Collections.shuffle(accountNum);
				if(tservice.searchAccountnum(accountNum.get(0)) == 0) {
					param.setAccountnum(accountNum.get(0));
					tservice.insertAccount(param);
					map.clear();
					map.put("msg", "success");
				}
		}
		return map;
	}
	
	@GetMapping("/mypage/{cid}")
	public Map<?,?> mypage(@PathVariable String cid){
		map.clear();
		map.put("buystockAll", tservice.vueBuystockAll(cid));
		map.put("sellstockAll", tservice.vueSellstockAll(cid));
		map.put("caccount", tservice.searchCaccount(cid));
		map.put("customer", tservice.searchCus(cid));
		return map;
	}
		
}
