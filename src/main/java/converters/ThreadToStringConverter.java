
package converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Thread;
@Component
@Transactional
public class ThreadToStringConverter implements Converter<Thread, String> {
@Override
public String convert(Thread arg0) {
return String.valueOf(arg0.getId());
}
}
