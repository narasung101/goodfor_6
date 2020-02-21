package com.goodfor.web.seho;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodfor.web.customer.Customer;

@Service
public class TradingServiceImpl implements TradingService {
	@Autowired TradingMapper mapper;

	@Override
	public void updateTrading(Trading param) {
		mapper.updateTrading(param);
	}

	@Override
	public void updateBizInfo(BizInfo param) {
		mapper.updateBizInfo(param);
	}

	@Override
	public void insertInterest(Interest param) {
		 mapper.insertInterest(param);
	}

	@Override
	public ArrayList<String> searchInterest(String cid) {
		return mapper.searchInterest(cid);
	}

	@Override
	public Trading searchStockname(String stockname) {
		return mapper.searchStockname(stockname);
	}
	@Override
	public int searchAccountnum(String accountnum) {
		return mapper.searchAccountnum(accountnum);
	}

	@Override
	public void insertBuyStock(BuyStock param) {
		mapper.insertBuyStock(param);
	}

	@Override
	public void insertMyStock(MyStock param) {
		mapper.insertMyStock(param);
	}

	@Override
	public void insertTempStock(TempStock param) {
		mapper.insertTempStock(param);
	}

	@Override
	public void insertSellStock(SellStock param) {
		mapper.insertSellStock(param);
	}

	@Override
	public MyStock searchMyStock(String stockcode) {
		return mapper.searchMyStock(stockcode);
	}

	@Override
	public void deleteMyStock(String seq) {
		mapper.deleteMyStock(seq);
	}

	@Override
	public void updateTempStock(TempStock param) {
		mapper.updateTempStock(param);
	}

	@Override
	public TempStock searchTempStock(TempStock param) {
		return mapper.searchTempStock(param);
	}

	@Override
	public Trading search(String stockname) {
		return mapper.search(stockname);
	}

	@Override
	public void deleteTempStock(int seq) {
		mapper.deleteTempStock(seq);
	}

	@Override
	public void updateMyStock(MyStock param) {
		mapper.updateMyStock(param);
	}

	@Override
	public String searchSeqInterest(@Param("seq") int seq, @Param("cid") String cid) {
		return mapper.searchSeqInterest(seq, cid);
	}

	@Override
	public void insertAccount(CAccount param) {
		mapper.insertAccount(param);
	}

	@Override
	public int searchAccount(String cid) {
		return mapper.searchAccount(cid);
	}

	@Override
	public String searchAnum(String cid) {
		return mapper.searchAnum(cid);
	}

	@Override
	public ArrayList<String> searchseq(String cid) {
		return mapper.searchseq(cid);
	}

	@Override
	public ArrayList<MyStock> vueMyStock(String cid) {
		return mapper.vueMyStock(cid);
	}

	@Override
	public int searchcount(String cid) {
		return mapper.searchcount(cid);
	}

	@Override
	public Trading vueData(String stockname) {
		return mapper.vueData(stockname);
	}

	@Override
	public int searchInSeq(String stockname) {
		return mapper.searchInSeq(stockname);
	}

	@Override
	public void deleteInterest(int seq) {
		mapper.deleteInterest(seq);
	}

	@Override
	public int inCount(String cid) {
		return mapper.inCount(cid);
	}

	@Override
	public void updateCaccount(CAccount param) {
		mapper.updateCaccount(param);
	}

	@Override
	public CAccount searchCaccount(String cid) {
		return mapper.searchCaccount(cid);
	}

	@Override
	public Customer searchCus(String cid) {
		return mapper.searchCus(cid);
	}

	@Override
	public int buystockCount(String cid) {
		return mapper.buystockCount(cid);
	}

	@Override
	public int sellstockCount(String cid) {
		return mapper.sellstockCount(cid);
	}

	@Override
	public BuyStock vueBuystock(@Param("cid") String cid, @Param("seq") int seq) {
		return mapper.vueBuystock(cid, seq);
	}

	@Override
	public SellStock vueSellstock(@Param("cid") String cid, @Param("seq") int seq) {
		return mapper.vueSellstock(cid, seq);
	}

	@Override
	public ArrayList<String> buySeq(String cid) {
		return mapper.buySeq(cid);
	}

	@Override
	public ArrayList<String> sellSeq(String cid) {
		return mapper.sellSeq(cid);
	}

	@Override
	public ArrayList<BuyStock> vueBuystockAll(String cid) {
		return mapper.vueBuystockAll(cid);
	}

	@Override
	public ArrayList<SellStock> vueSellstockAll(String cid) {
		return mapper.vueSellstockAll(cid);
	}

}
