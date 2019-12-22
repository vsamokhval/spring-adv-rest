package vss.springrest.converters;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import vss.springrest.model.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PdfMessageConverter implements HttpMessageConverter<List<User>> {

    private static final Logger LOG = LoggerFactory.getLogger(PdfMessageConverter.class);

    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.APPLICATION_PDF);
    }

    @Override
    public List<User> read(Class<? extends List<User>> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(List<User> users, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, out);
            writer.setViewerPreferences(2053);
            document.open();

            Table table = new Table(2);
            table.addCell("User name");
            table.addCell("User email");

            for(User user : users) {
                table.addCell(user.getFirstName() + " "  +  user.getLastName());
                table.addCell(user.getEmail());
            }

            document.add(table);
            document.close();
            writer.flush();

            httpOutputMessage.getHeaders().setContentType(MediaType.APPLICATION_PDF);
            httpOutputMessage.getHeaders().setContentLength(out.size());
            out.writeTo(httpOutputMessage.getBody());
            httpOutputMessage.getBody().flush();
        } catch (DocumentException e) {
            LOG.error("Exception has been thrown while pdf creating.", e);
        }
    }
}
