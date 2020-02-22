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

            String countryName;
            Double standard;
            LocalDate effectiveFrom;

            //for rows that have only one period, put them in list in order to not to iterate them
            if (header.getRates().get(i).getPeriods().size() == 1) {

                countryName = header.getRates().get(i).getName();
                standard = header.getRates().get(i).getPeriods().get(0).getRate().getStandard();
                effectiveFrom = header.getRates().get(i).getPeriods().get(0).getEffective_from();

                data.add(new Result(countryName, standard, effectiveFrom));

            } else {

                // loop trough data that have more then 1 period
                for (int j = 0; j < header.getRates().get(i).getPeriods().size(); j++) {
                    countryName = header.getRates().get(i).getName();
                    standard = header.getRates().get(i).getPeriods().get(j).getRate().getStandard();

                    // for every iteration save first period which is the latest in json
                    effectiveFrom = header.getRates().get(i).getPeriods().get(0).getEffective_from();

                    //check if one country have more rates, if does take the latest one and save him into the map
                    if (!effectiveFrom.isAfter(header.getRates().get(i).getPeriods().get(j).getEffective_from())) {

                        effectiveFrom = header.getRates().get(i).getPeriods().get(j).getEffective_from();
                        standard = header.getRates().get(i).getPeriods().get(j).getPeriodRateStandard();
                        data.add(new Result(countryName, standard, effectiveFrom));
                    }
                }
            }

        }

        System.out.println();// empty row for visibility

        System.out.println("Countries with highest VAT in Europe based on valid last date are: ");
        Comparator<Result> comparatorHighest = Comparator.comparing(Result::getStandard).thenComparing(Result::getDate).reversed();
        data.sort(comparatorHighest);
        data.stream().limit(3).forEach(s -> System.out.println(s.getCountryName() + " VAT: " + s.getStandard() + "%. Last valid date: " + s.getDate()));

        System.out.println();// empty row for visibility

        System.out.println("Countries with lowest VAT in Europe based on valid last date are: ");
        Comparator<Result> comparatorLowest = Comparator.comparing(Result::getStandard).reversed().thenComparing(Result::getDate).reversed();
        data.sort(comparatorLowest);
        data.stream().limit(3).forEach(s -> System.out.println(s.getCountryName() + " VAT: " + s.getStandard() + "%. Last valid date: " + s.getDate()));

    }
}
