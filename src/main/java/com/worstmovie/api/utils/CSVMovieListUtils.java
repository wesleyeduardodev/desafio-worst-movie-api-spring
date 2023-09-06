package com.worstmovie.api.utils;
import lombok.experimental.UtilityClass;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CSVMovieListUtils {

    public static String HEADER_YEAR = "year";
    public static String HEADER_TITLE = "title";
    public static String HEADER_STUDIOS = "studios";
    public static String HEADER_PRODUCERS = "producers";
    public static String HEADER_WINNER = "winner";
    public static final String[] FILE_HEADER_MAPPING = {HEADER_YEAR, HEADER_TITLE, HEADER_STUDIOS, HEADER_PRODUCERS, HEADER_WINNER};
    public static String WINNER = "yes";

    public static List<String> splitAndCleanRecords(CSVRecord csvRecord, String header) {
        return Arrays.stream(csvRecord.get(header)
                        .toUpperCase()
                        .split(",|\\bAND\\b+|AND\\b+"))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
    }
}
