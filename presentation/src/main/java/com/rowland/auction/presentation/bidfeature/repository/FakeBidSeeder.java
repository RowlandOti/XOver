package com.rowland.auction.presentation.bidfeature.repository;

import com.rowland.auction.presentation.bidfeature.model.BidModel;

import java.util.ArrayList;
import java.util.List;

public class FakeBidSeeder {

    public static List<BidModel> seedDatabase() {

        List<BidModel> bidModelList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            BidModel bid = new BidModel(i);
            bid.setTitle("Ancient Gold Chest");
            bid.setDescription("Ancient Gold Chest christened with gold labellings");
            bid.setStatus(true);
            bid.save();

            bidModelList.add(bid);
        }
        return bidModelList;
    }
}
