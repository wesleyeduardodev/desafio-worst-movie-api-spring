package com.worstmovie.api.utils;
import com.worstmovie.api.dto.LinkDTO;
import lombok.experimental.UtilityClass;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class LinksUtils {
    public List<LinkDTO> generateLinks(String path, Long entityId) {
        String href;
        if (Objects.nonNull(entityId)) {
            href = path.concat("/").concat(String.valueOf(entityId));
        } else {
            href = path;
        }
        LinkDTO linkSelf = LinkDTO
                .builder()
                .rel("self")
                .href(href)
                .build();
        LinkDTO linkUpdate = LinkDTO
                .builder()
                .rel("update")
                .href(href)
                .build();
        LinkDTO linkDelete = LinkDTO
                .builder()
                .rel("delete")
                .href(href)
                .build();
        return Arrays.asList(linkSelf, linkUpdate, linkDelete);
    }
}
