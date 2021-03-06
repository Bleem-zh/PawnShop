package com.pawnshop.dao;

import java.util.List;

import com.pawnshop.po.Bag;
import com.pawnshop.po.Diamond;
import com.pawnshop.po.Jewellery;
import com.pawnshop.po.Metal;
import com.pawnshop.po.Stone;
import com.pawnshop.po.Watch;

public interface ContinueDao {

	public List<Jewellery> getJewelleryList(int uid);
	
	public int updateJewelleryState(int jid);
	
	public List<Watch> getWatchList(int uid);
	
	public void updateWatchState(int wid);

	public List<Bag> getBagList(int uid);
	
	public void updateBagState(int bid);

	public List<Diamond> getDiamondList(int uid);

	public void updateDiamondState(int did);

	public List<Metal> getMetalList(int uid);

	public void updateMetalState(int mid);

	public List<Stone> getStoneList(int uid);

	public void updateStoneState(int sid);
}
