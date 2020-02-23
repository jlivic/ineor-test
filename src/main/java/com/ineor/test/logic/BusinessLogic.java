package com.ineor.test.logic;

import com.ineor.test.jsonEntity.Header;
import com.ineor.test.jsonEntity.Result;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for parsing and analyzing data from JSON.
 *
 * @author Janko Livic
 */
public class BusinessLogic {

    // list with data. Object will be reused for both actions
    private static List<Result> data = new ArrayList<>();

    /**
     * Method that will do all needed logic for parsing and analyzing</br>
     * data contained in JSON. Method watches out for valid VAT's and</br>
     * outputs 3 rows of data which are 3 with highest and 3 with lowest VAT.
     *
     * @param header - mapped values from JSON
     */
    public static void doJob(Header header) {
        for (int i = 0; i < header.getRates().size(); i++) {

            //for rows that have only one period, put them in list in order to not to iterate them
            if (header.getRates().get(i).getPeriods().size() == 1) {
                data.add(new Result(header.getRates().get(i).getName(), header.getRates().get(i).getPeriods().get(0).getRate().getStandard(), header.getRates().get(i).getPeriods().get(0).getEffective_from()));

            } else {

                // loop trough data that have more then 1 period
                for (int j = 0; j < header.getRates().get(i).getPeriods().size(); j++) {

                    //take the first effectiveFrom from periods and compare it to others. If latest one exist put it into list
                    if (!header.getRates().get(i).getPeriods().get(0).getEffective_from().isAfter(header.getRates().get(i).getPeriods().get(j).getEffective_from())) {
                        data.add(new Result(header.getRates().get(i).getName(), header.getRates().get(i).getPeriods().get(j).getRate().getStandard(), header.getRates().get(i).getPeriods().get(j).getEffective_from()));
                    }
                }
            }

        }

        System.out.println();// empty row for visibility

        System.out.println("Countries with highest VAT in Europe based on valid last date are: ");
        data.stream().sorted(Comparator.comparing(Result::getStandard).thenComparing(Result::getDate).reversed()).limit(3).forEach(s -> System.out.println(s.getCountryName() + " VAT: " + s.getStandard() + "%. Last valid date: " + s.getDate()));

        System.out.println();// empty row for visibility

        System.out.println("Countries with lowest VAT in Europe based on valid last date are: ");
        data.stream().sorted(Comparator.comparing(Result::getStandard).reversed().thenComparing(Result::getDate).reversed()).limit(3).forEach(s -> System.out.println(s.getCountryName() + " VAT: " + s.getStandard() + "%. Last valid date: " + s.getDate()));

    }
}
