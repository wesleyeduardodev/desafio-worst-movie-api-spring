package com.worstmovie.api.utils;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ConverterUtils {

    public static List<Integer> convertConcatenatedNumbersIntoListOfIntegersBySeparator(String stringIntegers, String separator) {
        List<Integer> integers = new ArrayList<>();
        if (StringUtils.isNotEmpty(stringIntegers)) {
            Arrays.stream(stringIntegers.split(separator)).collect(Collectors.toList()).forEach(stringInteger -> integers.add(Integer.valueOf(stringInteger)));
        }
        return integers;
    }
}
