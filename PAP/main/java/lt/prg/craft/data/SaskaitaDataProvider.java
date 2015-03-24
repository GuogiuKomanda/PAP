package lt.prg.craft.data;

import java.util.ArrayList;
import java.util.List;

import lt.prg.craft.db.DBUtils;
import lt.prg.craft.model.Saskaita;

public class SaskaitaDataProvider extends AbstractDataProvider<Saskaita> {

	@Override
	protected List<Saskaita> loadData() {
		List<Saskaita> list = new ArrayList<Saskaita>();
		try {
			list = DBUtils.loadSaskaitos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		list.add(new Saskaita());
		return list;
	}

}
