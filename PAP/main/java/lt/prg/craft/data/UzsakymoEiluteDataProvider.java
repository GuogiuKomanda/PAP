package lt.prg.craft.data;

import java.util.ArrayList;
import java.util.List;

import lt.prg.craft.db.DBUtils;
import lt.prg.craft.model.UzsakymoEilute;

public class UzsakymoEiluteDataProvider extends AbstractDataProvider<UzsakymoEilute> {

	private Long uzsakymoId;

	public UzsakymoEiluteDataProvider(Long uzsakymoId) {
		this.uzsakymoId = uzsakymoId;
		reset();
	}

	@Override
	protected List<UzsakymoEilute> loadData() {
		List<UzsakymoEilute> list = new ArrayList<UzsakymoEilute>();
		if (uzsakymoId != null) {
			try {
				list = DBUtils.loadUzsakymoEilutes(uzsakymoId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UzsakymoEilute ue = new UzsakymoEilute();
			ue.setUzsakymoId(uzsakymoId);
			list.add(ue);
		}
		return list;
	}
}
