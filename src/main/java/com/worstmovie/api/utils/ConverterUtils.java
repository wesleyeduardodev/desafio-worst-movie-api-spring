package com.worstmovie.api.utils;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ConverterUtils {

    public static List<Integer> convertConcatenatedNumbersIntoListOfIntegersBySeparator(String stringIntegers, String separator) {
        List<Integer> integers = new ArrayList<>();
        if (StringUtils.isNotEmpty(stringIntegers)) {
            Arrays.stream(stringIntegers.split(separator)).toList().forEach(stringInteger -> integers.add(Integer.valueOf(stringInteger)));
        }
        return integers;
    }
}
