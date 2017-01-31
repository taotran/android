package vn.com.tvtran.myfootball.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import vn.com.tvtran.myfootball.MyCustomRestTemplate;
import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.Example;

/**
 * Created by tvtran on 1/24/2017.
 */

public class LeagueChooser extends Fragment {
    private static final String URL = "http://football-data.org/v1/soccerseasons";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.league_chooser_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//
//        messageConverters.add(new ByteArrayHttpMessageConverter());
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
//        List<MediaType> supportedMediaTypes = new ArrayList<>();
//        supportedMediaTypes.add(new MediaType("text/plain;charset=UTF-8"));
//        stringHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
//        messageConverters.add(stringHttpMessageConverter);
//        messageConverters.add(new ResourceHttpMessageConverter());
//        messageConverters.add(new FormHttpMessageConverter());
//        messageConverters.add(new SourceHttpMessageConverter());
//
//        messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
//
//
//        messageConverters.add(new MappingJacksonHttpMessageConverter());
//        messageConverters.add(new AtomFeedHttpMessageConverter());
//        messageConverters.add(new RssChannelHttpMessageConverter());


//        RestTemplate restTemplate = new RestTemplate(messageConverters);


//        MyCustomRestTemplate restTemplate = new MyCustomRestTemplate();
//        ResponseEntity<String> result = restTemplate.getForEntity(URL, String.class);
//        String formattedResult = result.getBody().replace("_links", "links");
//        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    }

    @Override
    public void onResume() {
        super.onResume();
        try {
//            List<Example> examples = mapper.readValue(formattedResult, new TypeReference<List<Example>>() {
//            });
//            //Example[] exampleArr = mapper.readValue(formattedResult, Example[].class);
//
//            ListView lv = (ListView) getActivity().findViewById(R.id.league_list_view);
//            ArrayAdapter<Example> exampleArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.league_list_item, examples);
//            lv.setAdapter(exampleArrayAdapter);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

            Future<Response> response = asyncHttpClient.prepareGet(URL).execute();

            Response r = response.get();
            String formattedResult = r.getResponseBody().replace("_links", "links");
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Example> examples = mapper.readValue(formattedResult, new TypeReference<List<Example>>() {
            });
            System.out.println(examples.get(1).getCaption());
            System.out.println(examples.get(1).getNumberOfTeams());
            ListView lv = (ListView) getActivity().findViewById(R.id.league_list_view);
            ArrayAdapter<Example> exampleArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.league_list_item, examples);
            lv.setAdapter(exampleArrayAdapter);
//            System.out.println(r.getResponseBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
