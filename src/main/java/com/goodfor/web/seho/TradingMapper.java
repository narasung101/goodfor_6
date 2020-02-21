package com.goodfor.web.seho;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.goodfor.web.customer.Customer;

@Repository
public interface TradingMapper {
	public Trading search(String stockname);
	public void updateTrading(Trading param);
	public void updateBizInfo(BizInfo param);
	public void updateTempStock(TempStock param);
	public void updateMyStock(MyStock param);
	public void insertInterest(Interest param);
	public ArrayList<String> searchInterest(String cid);
	public String searchSeqInterest(@Param("seq") int seq, @Param("cid") String cid);
	public Trading searchStockname(String stockname);
	public void insertBuyStock(BuyStock param);
	public void insertMyStock(MyStock param);
	public void insertTempStock(TempStock param);
	public void insertSellStock(SellStock param);
	public void deleteMyStock(String seq);
	public void deleteTempStock(int seq);
	public TempStock searchTempStock(TempStock param);
	public void insertTrading(Trading param);
	public void insertAccount(CAccount param);
	public int searchAccount(String cid);
	public int searchAccountnum(String accountnum);
	public String searchAnum(String cid);
	public ArrayList<String> searchseq(String cid);
	public int searchcount(String cid);
	public MyStock searchMyStock(String stockcode);
	public ArrayList<MyStock> vueMyStock(String cid);
	public MyStock vueMytest(@Param("cid") String cid, @Param("seq") int seq);
	public Trading vueData(String stockname);
	public int searchInSeq(String stockname);
	public void deleteInterest(int seq);
	public int inCount(String cid);
	public void updateCaccount(CAccount param);
	public CAccount searchCaccount(String cid);
	public Customer searchCus(String cid);
	public int buystockCount(String cid);
	public int sellstockCount(String cid);
	public BuyStock vueBuystock(@Param("cid") String cid, @Param("seq") int seq);
	public SellStock vueSellstock(@Param("cid") String cid, @Param("seq") int seq);
	public ArrayList<String> buySeq(String cid);
	public ArrayList<String> sellSeq(String cid);
	
	public ArrayList<BuyStock> vueBuystockAll(@Param("cid") String cid);
	public ArrayList<SellStock> vueSellstockAll(@Param("cid") String cid);
}
