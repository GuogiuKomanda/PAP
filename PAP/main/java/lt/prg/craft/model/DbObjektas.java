package lt.prg.craft.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class DbObjektas implements Serializable {
	
	private static AtomicInteger generator = new AtomicInteger();

	private static final long serialVersionUID = -649786161590597945L;
	
	private Integer key;
	protected Long id;
	
	public DbObjektas(){
		key = generator.getAndIncrement();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
}
