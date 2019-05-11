package com.joyance.basedemo.mybatis.parser;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joyance.basedemo.mybatis.model.BoundSql;
import com.joyance.basedemo.mybatis.model.Configuration;
import com.joyance.basedemo.mybatis.model.JDBCConfig;
import com.joyance.basedemo.mybatis.model.MyStatement;

public class JDBCParser {

	private Configuration configuration;
	
	private Connection connection;
	
	public JDBCParser(Configuration configuration){
		this.configuration = configuration;
		getJDBCConnection();
	}
	
	public void getJDBCConnection(){
		JDBCConfig jdbcConfig = configuration.getJdbc();
        try {
        	//加载数据库驱动
			Class.forName(jdbcConfig.getDriver());
			 //通过驱动管理类获取数据库链接
	        connection =  DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object execute(MyStatement myStatement,Object[] params,Class<?> returnType) throws Exception{
		BoundSql boundSql = myStatement.getBoundSql();
		try {
			String sql = boundSql.getSql();
			List<String> paramNames = boundSql.getParamNames();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// 设置参数，第一个参数为 sql 语句中参数的序号（从 1 开始），第二个参数为设置的
			Class<?> paramClaz = myStatement.getParameterType();
			// 如果有参数
			/* TODO 参数这里需要优化，支持@Param注解 */
			if(paramNames!=null && paramNames.size()>0){
				int paramSize = paramNames.size();
				//目前只支持一个参数，如果参数个数为1，说明不是对象，是int或string直接可传递的
				if(params.length == 1){
					Object param = params[0];
					if(isSimpleType(param)){
						setParamValue(preparedStatement,1, param);
					}else{
						for(int i=0;i<paramSize;i++){
							Field field = paramClaz.getDeclaredField(paramNames.get(i));
							field.setAccessible(true);
							Object value = field.get(param);
							setParamValue(preparedStatement,i + 1, value);
						}
					}
				}
			}
			// 向数据库发出 sql 执行查询，查询出结果集
			ResultSet rs = preparedStatement.executeQuery();
			Class<?> resultTypeClass = myStatement.getResultType();
			//如果是返回数组
			if(returnType == List.class){
				List<Object> results = new ArrayList<Object>();
				while(rs.next()){
					results.add(getLineData(resultTypeClass,rs));
				}
				return results;
			}else{
				if(rs.next()){
					return getLineData(resultTypeClass,rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setParamValue(PreparedStatement preparedStatement,int index,Object param) throws SQLException{
		if(param != null){
			if(param.getClass() == Integer.class){
				preparedStatement.setInt(index, (Integer) param);
			}else if(param.getClass() == Long.class){
				preparedStatement.setLong(index, (Long)param);
			}else if(param.getClass() == Double.class){
				preparedStatement.setDouble(index, (Double)param);
			}else if(param.getClass() == Float.class){
				preparedStatement.setFloat(index, (Float)param);
			}else if(param.getClass() == String.class){
				preparedStatement.setString(index, (String)param);
			}else if(param.getClass() == Boolean.class){
				preparedStatement.setBoolean(index, (Boolean)param);
			}
		}
	}
	
	public boolean isSimpleType(Object obj){
		Class<?> className =obj.getClass();
		if(className.equals("com.joyance.basedemo")){
			return false;
		}
		return true;
	}
	
	public Object getLineData(Class<?> resultTypeClass,ResultSet rs) throws Exception{
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		Object resultObj = resultTypeClass.newInstance();
		for (int i = 1; i <= columnCount; i++) {
			String columnName = metaData.getColumnName(i);
			// 前提是列名和属性名称要一周
			Field declaredField = resultTypeClass.getDeclaredField(columnName);
			// 暴力访问
			declaredField.setAccessible(true);
			// rs的下标是从1开始的
			declaredField.set(resultObj, rs.getObject(i));
		}
		return resultObj;
	}
	
}
