package lt.prg.craft.data;

import java.util.List;

import lt.prg.craft.model.DbObjektas;

public abstract class AbstractDataProvider<T extends DbObjektas> {

	private List<T> lines;

	public AbstractDataProvider() {
		reset();
	}

	public T getItemByKey(Object key) {
		for (T c : lines) {
			if (key.equals(getKey(c))) {
				return c;
			}
		}
		return null;
	}

	public List<T> getItemsByRange(int firstRow, int endRow) {
		return lines.subList(firstRow, endRow);
	}

	public Integer getKey(T item) {
		return item.getKey();
	}

	public int getRowCount() {
		return lines.size();
	}
	
	public void add(T item){
		lines.add(item);
	}
	
	public void remove(Integer key){
		for(T line : lines){
			if(line.getKey().equals(key)){
				lines.remove(line);
				break;
			}
		}
	}
	
	public void put(Integer key, T item){
		for (int i = 0; i < lines.size(); i++ ){
			if(lines.get(i).getKey().equals(key)){
				lines.set(i, item);
			}
		}
	}
	
	public void reset(){
		lines = loadData();
	}
	
	protected abstract List<T> loadData();
	
}
