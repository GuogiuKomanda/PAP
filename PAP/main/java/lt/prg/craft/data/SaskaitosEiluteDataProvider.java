package lt.prg.craft.data;

import java.util.ArrayList;
import java.util.List;

import lt.prg.craft.db.DBUtils;
import lt.prg.craft.model.SaskaitosEilute;

public class SaskaitosEiluteDataProvider extends AbstractDataProvider<SaskaitosEilute> {
	private Long saskaitosId;

	public SaskaitosEiluteDataProvider(Long saskaitosId) {
		super();
		this.saskaitosId = saskaitosId;
		reset();
	}

	@Override
	protected List<SaskaitosEilute> loadData() {
		List<SaskaitosEilute> list = new ArrayList<SaskaitosEilute>();
		if (saskaitosId != null) {
			try {
				list = DBUtils.loadSaskaitosEilutes(saskaitosId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			SaskaitosEilute se = new SaskaitosEilute();
			se.setSaskaitosId(saskaitosId);
			list.add(se);
		}
		return list;
	}
}
