package guru.springframework.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VisitService;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

	@Override
	public Set<Visit> findAll() {
		return super.findAll();
	}
	
	@Override
	public Visit findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Visit save(Visit object) {
		if (object.getPet() == null || object.getPet().getId() == null || object.getPet().getOwner() == null ||
				object.getPet().getOwner().getId() == null) {
			throw new RuntimeException("Invalid visit:" + object);
		}
		return super.save(object);
	}

	@Override
	public void delete(Visit object) {
		super.delete(object);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
}
