package lt.prg.craft.data;

import java.util.ArrayList;
import java.util.List;

import lt.prg.craft.db.DBUtils;
import lt.prg.craft.model.Preke;

public class PrekeDataProvider extends AbstractDataProvider<Preke> {

	@Override
	protected List<Preke> loadData() {
		List<Preke> list = new ArrayList<Preke>();
		try {
			list = DBUtils.getPrekes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
