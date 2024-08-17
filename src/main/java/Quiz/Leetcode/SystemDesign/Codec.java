package Quiz.Leetcode.SystemDesign;

import java.util.HashMap;
import java.util.Map;

/* TinyURL is a URL shortening service where you enter a 
 * URL such as https://leetcode.com/problems/design-tinyurl and 
 * it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service.
There is no restriction on how your encode/decode algorithm should work.
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.*/

// source https://leetcode.com/problems/encode-and-decode-tinyurl/discuss/237091/Java-Solution-85-Percentile

public class Codec {

	private final String B63_MAP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";
    private final String SHORT_URL_PREFIX = "http://tinyurl.com/";
    private IdGenerator idGen;
    private Map<Integer, String> idToUrlMap;
    
    Codec() {
        idGen = new IdGenerator();
        idToUrlMap = new HashMap<>();
    }
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int id = idGen.next();
        idToUrlMap.put(id, longUrl);
        return SHORT_URL_PREFIX + idToShortUrl(id);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return idToUrlMap.get(shortUrlToId(shortUrl.substring(19)));
    }
    
    String idToShortUrl(int id) {
        StringBuilder url = new StringBuilder();
        while (id > 0) {
            url.insert(0, B63_MAP.charAt(id % 63));
            id /= 63;
        }
        return url.toString();
    }
    
    int shortUrlToId(String url) {
        int id = 0;
        for (char c : url.toCharArray()) {
            id *= 63;
            if (c >= 'a' && c <= 'z') {
                id += (c - 'a');
            } else if (c >= 'A' && c <= 'Z') {
                id += (c - 'A') + 26;
            } else if (c >= '0' && c <= '9') {
                id += (c - '0') + 52;
            } else {
                id += 62;
            }
        }
        return id;
    }
}

class IdGenerator {
    private int id;
    
    IdGenerator() {
        id = 0;
    }
    
    int next() {
        return id++;
    }
}
