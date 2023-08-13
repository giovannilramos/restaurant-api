package br.com.sinuqueiros.restaurant.controllers.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UriHelper {
    public static URI getUri(final UriComponentsBuilder uriComponentsBuilder, final String path, final Long id) {
        return uriComponentsBuilder.path(path).buildAndExpand(id).toUri();
    }
}
