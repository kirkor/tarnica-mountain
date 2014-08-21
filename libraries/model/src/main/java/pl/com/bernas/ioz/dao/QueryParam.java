package pl.com.bernas.ioz.dao;

import java.io.Serializable;

public class QueryParam implements Serializable {

	private static final long serialVersionUID = 6419858787183423677L;

	public final String name;
	public final Object value;

    public QueryParam(String name, Object value){
        this.name = name;
        this.value = value;
    }
}
