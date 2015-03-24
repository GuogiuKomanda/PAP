package lt.prg.craft.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import lt.prg.craft.model.DbObjektas;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;

public class DataModel<T extends DbObjektas> extends ExtendedDataModel<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3901107212108062023L;
	
	private Integer currentKey; // current row in the model
	private Map<Object, T> cachedResults = new HashMap<Object, T>(); // a local cache of search/pagination results
	private List<Object> cachedRowKeys; // a local cache of key values for cached items
	private int rowCount = -1;

	private AbstractDataProvider<T> dataProvider;

	public DataModel(AbstractDataProvider<T> dataProvider){
		this.dataProvider = dataProvider;
	}
	
	public void setRowKey(Object item) {
		this.currentKey = (Integer)item;

	}

	public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) {
//		int firstRow = ((SequenceRange) range).getFirstRow();
//		int numberOfRows = ((SequenceRange) range).getRows();
		cachedRowKeys = new ArrayList<Object>();
		for (T result : dataProvider.getItemsByRange(0, getRowCount())) {
			cachedRowKeys.add(result.getKey());
			cachedResults.put(result.getKey(), result); // populate cache. This is strongly advised as you'll see later.
			visitor.process(context, result.getKey(), argument);
		}
	}

	public T getRowData() {
		if (currentKey == null) {
			return null;
		} else {
			T selectedRowObject = cachedResults.get(currentKey); // return result from internal cache without making the trip to the database or other underlying datasource
			if (selectedRowObject == null) { // if the desired row is not within the range of the cache

				selectedRowObject = dataProvider.getItemByKey(currentKey);
				cachedResults.put(currentKey, selectedRowObject);
				return selectedRowObject;
			} else {
				return selectedRowObject;
			}
		}
	}

	public int getRowCount() {
		if (rowCount == -1) {
			rowCount = dataProvider.getRowCount(); // cache row count
			return rowCount;
		}
		return rowCount;

	}
	
	public void remove(Integer key){
		dataProvider.remove(key);
		rowCount = -1;
	}
	
	public void edit(Integer key, T item) {
		item.setKey(key);
		dataProvider.put(key, item);
	}
	
	public void add(T item){
		dataProvider.add(item);
		rowCount = -1;
	}
	
	public void reset(){
		dataProvider.reset();
		rowCount = -1;
	}

	@Override
	public Object getRowKey() {
		return currentKey;
	}

	@Override
	public int getRowIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getWrappedData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowAvailable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setRowIndex(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWrappedData(Object arg0) {
		// TODO Auto-generated method stub

	}
}
