package com.example.springBootTechlead.service.core2;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BT6 {
    public int maxProfit(List<Integer> prices) {
        int buy = prices.get(0);
        int profit = 0;
        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i) < buy) {
                buy = prices.get(i);
            } else if (prices.get(i) - buy > profit) {
                profit = prices.get(i) - buy;
            }
        }
        return profit;
    }
}
