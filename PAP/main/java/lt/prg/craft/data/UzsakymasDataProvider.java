package lt.prg.craft.data;

import java.util.ArrayList;
import java.util.List;

import lt.prg.craft.db.DBUtils;
import lt.prg.craft.model.Uzsakymas;

public class UzsakymasDataProvider extends AbstractDataProvider<Uzsakymas> {

	@Override
	protected List<Uzsakymas> loadData() {
		List<Uzsakymas> list = new ArrayList<Uzsakymas>();
		try {
			list = DBUtils.loadUzsakymai();
		} catch (Exception e) {
			e.printStackTrace();
		}
		list.add(new Uzsakymas());
		return list;
	}

}
