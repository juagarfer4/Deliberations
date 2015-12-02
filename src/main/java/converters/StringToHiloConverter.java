
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Hilo;
import services.HiloService;

@Component
@Transactional
public class StringToHiloConverter implements Converter<String, Hilo> {
	
	@Autowired
	private HiloService hiloService;

	@Override
	public Hilo convert(String arg0) {
		return hiloService.findOne(Integer.valueOf(arg0));
	}
}
