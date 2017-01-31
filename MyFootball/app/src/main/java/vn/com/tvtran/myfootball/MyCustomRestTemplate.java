package vn.com.tvtran.myfootball;

import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tvtran on 1/25/2017.
 */

public class MyCustomRestTemplate extends RestTemplate {

    private List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

    public MyCustomRestTemplate() {

        this.messageConverters.add(new ByteArrayHttpMessageConverter());
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.getSupportedMediaTypes().clear();
//        List<MediaType> supportedMediaTypes = new ArrayList<>();
        stringHttpMessageConverter.getSupportedMediaTypes().add(new MediaType("text/plain;charset=UTF-8"));
//        stringHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        messageConverters.add(stringHttpMessageConverter);
        this.messageConverters.add(new ResourceHttpMessageConverter());
        this.messageConverters.add(new FormHttpMessageConverter());
        this.messageConverters.add(new SourceHttpMessageConverter());
//        if (jaxb2Present) {
        this.messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
//        }
//        if (jacksonPresent) {
        this.messageConverters.add(new MappingJacksonHttpMessageConverter());
//        }
//        if (romePresent) {
        this.messageConverters.add(new AtomFeedHttpMessageConverter());
        this.messageConverters.add(new RssChannelHttpMessageConverter());
//        }
    }

    @Override
    public List<HttpMessageConverter<?>> getMessageConverters() {
        return this.messageConverters;
    }
}
