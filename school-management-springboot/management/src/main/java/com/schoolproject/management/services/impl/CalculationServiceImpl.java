package com.schoolproject.management.services.impl;

import com.schoolproject.management.services.CalculationService;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

    @Override
    public int addNumbers(int... nums) {
        int tot = 0;
        for (int num: nums) {
            tot += num;
        }

        return tot;
    }


}
