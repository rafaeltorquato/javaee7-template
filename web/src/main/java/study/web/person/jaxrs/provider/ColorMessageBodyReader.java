package study.web.person.jaxrs.provider;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.awt.*;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

//TODO Implement this with real use case of the domain
@Consumes(MediaType.TEXT_PLAIN)
@Provider
public class ColorMessageBodyReader implements MessageBodyReader<Color> {
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return mediaType == MediaType.TEXT_PLAIN_TYPE;
    }

    @Override
    public Color readFrom(Class<Color> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        InputStreamReader isr = new InputStreamReader(entityStream);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String part;
        while ((part = br.readLine()) != null) {
            sb.append(part);
        }
        return Color.getColor(sb.toString());
    }
}
