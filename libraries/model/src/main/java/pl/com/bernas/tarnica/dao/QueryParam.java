package pl.com.bernas.tarnica.dao;

import java.io.Serializable;

public class QueryParam<T> implements Serializable {

	private static final long serialVersionUID = 6419858787183423677L;

	public final String name;
	public final T value;

    public QueryParam(String name, T value){
        this.name = name;
        this.value = value;
    }
}
